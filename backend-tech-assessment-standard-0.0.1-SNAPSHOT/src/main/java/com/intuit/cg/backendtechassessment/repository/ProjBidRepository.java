/**
 * 
 */
package com.intuit.cg.backendtechassessment.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.intuit.cg.backendtechassessment.domain.Bid;
import com.intuit.cg.backendtechassessment.domain.Identifiable;
import com.intuit.cg.backendtechassessment.domain.ProjBid;
import com.intuit.cg.backendtechassessment.domain.Project;

/**
 * @author RAM
 *
 */
@Repository
public class ProjBidRepository<T extends Identifiable> {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private UniIDGen uniIDGenerator;

	class ProjectRowMapper implements RowMapper<Project> {

		public Project mapRow(ResultSet proj, int rowNum) throws SQLException {

			Project project = new Project();
			project.setID(proj.getLong("PID"));
			project.setpName(proj.getString("PNAME"));
			project.setpDesc(proj.getString("PDESC"));
			project.setMaxBudget(proj.getDouble("MAX_BUDGET"));
			project.setpTimeLimit(proj.getString("TIME_LIMIT"));
			project.setpStatus(proj.getString("PSTATUS"));
			project.setsID(proj.getLong("SID"));

			return project;
		}

	}

	class ProjRowMapper implements RowMapper<ProjBid> {

		public ProjBid mapRow(ResultSet proj, int rowNum) throws SQLException {

			ProjBid projbid = new ProjBid();
			projbid.setID(proj.getLong("PID"));
			projbid.setpName(proj.getString("PNAME"));
			projbid.setpDesc(proj.getString("PDESC"));
			projbid.setMaxBudget(proj.getDouble("MAX_BUDGET"));
			projbid.setpTimeLimit(proj.getString("TIME_LIMIT"));
			projbid.setpStatus(proj.getString("PSTATUS"));
			projbid.setsID(proj.getLong("SID"));
			projbid.setbName(proj.getString("BNAME"));
			projbid.setbAmount(proj.getDouble("BID_AMOUNT"));

			return projbid;
		}

	}

	public List<Project> findAllProjects() {
		return jdbcTemplate.query("select * from project", new ProjectRowMapper());
	}

	public ProjBid findProjectById(long pid) {
		return jdbcTemplate.queryForObject(
				"select proj.PID, proj.PNAME, proj.PDESC,proj.MAX_BUDGET,proj.TIME_LIMIT,proj.SID, proj.PSTATUS, buy.BNAME, MIN(bid.bid_amount) As BID_AMOUNT from project proj, buyer buy, bids bid where proj.pid=? and proj.pid = bid.pid and bid.bid = buy.bid group by proj.pid",
				new Object[] { pid }, new ProjRowMapper());
	}

	public int insert(Project project) throws SQLException {
		LocalDate cDate = LocalDate.now();
		project.setID(uniIDGenerator.getNextId());
		String pstatus = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (cDate.isBefore(LocalDate.parse(project.getpTimeLimit(), formatter))) {
			pstatus = "Active";
		} else if (cDate.isAfter(LocalDate.parse(project.getpTimeLimit(), formatter))) {
			pstatus = "Closed";
		}
		return jdbcTemplate.update(
				"insert into project (PID, PNAME, PDESC, MAX_BUDGET, TIME_LIMIT, PSTATUS, SID) "
						+ "values(?, ?, ?, ?, ?, ? , ?)",
				new Object[] { project.getId(), project.getpName(), project.getpDesc(), project.getMaxBudget(),
						project.getpTimeLimit(), pstatus, project.getsID() });
	}

	class BidRowMapper implements RowMapper<Bid> {

		@Override
		public Bid mapRow(ResultSet bids, int rowNum) throws SQLException {

			Bid bid = new Bid();
			bid.setID(bids.getLong("BID_ID"));
			bid.setbAmount(bids.getDouble("BID_AMOUNT"));
			bid.setBuyID(bids.getLong("BID"));
			bid.setProjID(bids.getLong("PID"));

			return bid;
		}

	}

	public List<Bid> findAllBIDS() {
		return jdbcTemplate.query("select * from BIDS", new BidRowMapper());
	}

	public Bid findBIDById(long bid_id) {

		return jdbcTemplate.queryForObject("select * from bids where bid_id=?", new Object[] { bid_id },
				new BidRowMapper());
	}

	public String getProjectStatus(long pid) throws SQLException {
		String result = null;
		Project proj = jdbcTemplate.queryForObject("select * from project where pid=?", new Object[] { pid },
				new ProjectRowMapper());
		result = proj.getpStatus();
		return result;

	}

	public int insert(Bid bid) throws SQLException {
		bid.setID(uniIDGenerator.getNextId());
		long projID = (long) bid.getProjID();

		String status = getProjectStatus(projID);

		if (status == "Active") {
			return jdbcTemplate.update("insert into BIDS (BID_ID, BID_AMOUNT, BID, PID) " + "values(?, ?, ?, ?)",
					new Object[] { bid.getId(), bid.getbAmount(), bid.getBuyID(), bid.getProjID() });
		} else
			return 0;
	}

	public Double getBidAmount(long pid) throws SQLException {
		Double result = null;
		Bid bid = jdbcTemplate.queryForObject("select * from  bids  where pid=?", new Object[] { pid },
				new BidRowMapper());
		result = bid.getbAmount();
		return result;

	}

	public int autoBid(long pid, long buyId) throws SQLException {
		Bid bid = new Bid();
		Double bid_amount = null;
		Double bamt = null;
		bid.setID(uniIDGenerator.getNextId());
		bamt = getBidAmount(pid);
		if (bamt != null) {
			bid_amount = bamt / 2 + (Math.random() * (bamt - bamt / 2));
		} else {
			bid_amount = 100 / 2 + (Math.random() * (100 - 1000 / 2));
		}
		return jdbcTemplate.update("insert into BIDS (BID_ID, BID_AMOUNT, BID, PID) " + "values(?, ?, ?, ?)",
				new Object[] { uniIDGenerator.getNextId(), bid_amount, buyId, pid });

	}

}
