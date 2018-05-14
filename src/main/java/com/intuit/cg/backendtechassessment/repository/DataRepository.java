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

import com.intuit.cg.backendtechassessment.domain.Identifiable;
import com.intuit.cg.backendtechassessment.domain.Project;

/**
 * @author RAM
 *
 */
@Repository
public abstract class DataRepository<T extends Identifiable> {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
//	@Autowired
//	private UniIDGen uniIDGenerator;
	class ProjectRowMapper implements RowMapper<Project> {

		@Override
		public Project mapRow(ResultSet proj, int rowNum) throws SQLException {
		
			Project project = new Project();
			project.setID(proj.getLong("PID"));
			project.setpName(proj.getString("PNAME"));
			project.setpDesc(proj.getString("PDESC"));
			project.setMaxBudget(proj.getDouble("MAX_BUDGET"));
			//project.setpTimeLimit(proj.getDate("TIME_LIMIT").toLocalDate());
			//project.setPstatus(proj.getString("STATUS"));
			project.setsID(proj.getLong("SID"));
			
			return project;
		}

	}

	public List<Project> findAllProjects() {
		return jdbcTemplate.query("select * from project", new ProjectRowMapper());
	}

	public Project findProjectById(long pid) {
		//@TODO get the lowest bid for the project
		return jdbcTemplate.queryForObject("select proj.PID, proj.PNAME, proj.PDESC, proj.MAX_BUDGET, proj.TIME_LIMIT, proj.STATUS, buy.BNAME, MIN(bid.bid_amount) from project proj, buyer buy, bids bid where proj.pid=? and proj.pid = bid.pid and bid.bid = buy.bid groupby bid.bid_id", new Object[] { pid },
				new ProjectRowMapper());
	}

	public boolean sellerExists(long sid) throws SQLException {
		ResultSet sqlID = (ResultSet) jdbcTemplate.query("select SID from seller", new ProjectRowMapper());
		Integer qID = ((Integer) sqlID.getObject(1)).intValue();
		if (qID == sid)
			return true;
		else
		return false;
	}
//	public int insert(Project project) throws Exception, SQLException {
//		project.setID(uniIDGenerator.getNextId());
//		if(sellerExists(project.getsID()))
//		//return jdbcTemplate.update("insert into project (PID, PNAME, PDESC, MAX_BUDGET, TIME_LIMIT, STATUS, SID) " + "values(?, ?, ?, ?, ?, ? , ?)",
//			//	new Object[] { project.getId(), project.getpName(), project.getpDesc(), project.getMaxBudget(), project.getpTimeLimit(),project.pstatus(),project.getsID() });
//		//else
//			return 0;
//	}



}
