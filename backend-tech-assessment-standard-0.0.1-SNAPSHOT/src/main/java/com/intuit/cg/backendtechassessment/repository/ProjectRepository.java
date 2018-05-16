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

import com.intuit.cg.backendtechassessment.domain.ProjBid;
import com.intuit.cg.backendtechassessment.domain.Project;
@Repository
public class ProjectRepository implements DataRepository<Project> {
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

	public ProjBid findProjectById(long pid) {
		return jdbcTemplate.queryForObject(
				"select proj.PID, proj.PNAME, proj.PDESC,proj.MAX_BUDGET,proj.TIME_LIMIT,proj.SID, proj.PSTATUS, buy.BNAME, MIN(bid.bid_amount) As BID_AMOUNT from project proj, buyer buy, bids bid where proj.pid=? and proj.pid = bid.pid and bid.bid = buy.bid group by proj.pid",
				new Object[] { pid }, new ProjRowMapper());
	}

	@Override
	public List<Project> findAll() {
		return jdbcTemplate.query("select * from project", new ProjectRowMapper());
	}

	@Override
	public Project findById(long id) {
		return jdbcTemplate.queryForObject(
				"select proj.PID, proj.PNAME, proj.PDESC,proj.MAX_BUDGET,proj.TIME_LIMIT,proj.SID, proj.PSTATUS from project  where pid=?",
				new Object[] { id }, new ProjectRowMapper());
	}

	@Override
	public int insert(Project item) {
		LocalDate cDate = LocalDate.now();
		item.setID(uniIDGenerator.getNextId());
		String pstatus = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (cDate.isBefore(LocalDate.parse(item.getpTimeLimit(), formatter))) {
			pstatus = "Active";
		} else if (cDate.isAfter(LocalDate.parse(item.getpTimeLimit(), formatter))) {
			pstatus = "Closed";
		}
		return jdbcTemplate.update(
				"insert into project (PID, PNAME, PDESC, MAX_BUDGET, TIME_LIMIT, PSTATUS, SID) "
						+ "values(?, ?, ?, ?, ?, ? , ?)",
				new Object[] { item.getId(), item.getpName(), item.getpDesc(), item.getMaxBudget(),
						item.getpTimeLimit(), pstatus, item.getsID() });
	}

}
