/**
 * 
 */
package com.intuit.cg.backendtechassessment.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.intuit.cg.backendtechassessment.domain.Bid;
import com.intuit.cg.backendtechassessment.domain.Identifiable;
import com.intuit.cg.backendtechassessment.domain.Project;

/**
 * @author RAM
 *
 */
@Repository
public class BidRepository<T extends Identifiable> {
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
		Bid bid = jdbcTemplate.queryForObject(
				"select top 1 * from BIDS  where  BID_AMOUNT  = (select min(BID_AMOUNT ) from BIDS ) and PID = ?",
				new Object[] { pid }, new BidRowMapper());
		if (bid.getbAmount() != null)
			result = bid.getbAmount();
		else
			result = null;
		if (result != null)
			return result;
		else
			return null;

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
				new Object[] { bid.getId(), bid_amount, buyId, pid });

	}

}
