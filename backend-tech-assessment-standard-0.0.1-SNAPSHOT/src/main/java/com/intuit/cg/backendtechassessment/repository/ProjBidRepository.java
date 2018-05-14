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
import com.intuit.cg.backendtechassessment.domain.Buyer;
import com.intuit.cg.backendtechassessment.domain.Identifiable;
import com.intuit.cg.backendtechassessment.domain.Project;
import com.intuit.cg.backendtechassessment.domain.Seller;





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
	class SellerRowMapper implements RowMapper<Seller> {

		@Override
		public Seller mapRow(ResultSet seller, int rowNum) throws SQLException {
		
			Seller sell = new Seller();
			
			sell.setID(seller.getLong("SID"));
			sell.setsName(seller.getString("SNAME"));
			
			
			return sell;
		}

	}

	public List<Seller> findAllSellers() {
		return jdbcTemplate.query("select * from Seller", new SellerRowMapper());
	}
	
	public Seller findSellerById(long sid) {
		
		return jdbcTemplate.queryForObject("select * from seller where sid=?", new Object[] {sid},
				new SellerRowMapper());
	}


	public int insert(Seller seller) {
		seller.setID(uniIDGenerator.getNextId());
		return jdbcTemplate.update("insert into seller (SID, SNAME) " + "values(?, ?)",
				new Object[] { seller.getId(), seller.getsName() });
	}
	class BuyerRowMapper implements RowMapper<Buyer> {

		@Override
		public Buyer mapRow(ResultSet buyer, int rowNum) throws SQLException {
		
			Buyer buy = new Buyer();
			buy.setID(buyer.getLong("BID"));
			buy.setbName(buyer.getString("BNAME"));
			
			
			return buy;
		}

	}

	public List<Buyer> findAll() {
		return jdbcTemplate.query("select * from Buyer", new BuyerRowMapper());
	}

	public Buyer findBuyerById(long bid) {
		
		return jdbcTemplate.queryForObject("select * from Buyer where bid=?", new Object[] {bid},
				new BuyerRowMapper());
	}


	public int insert(Buyer buyer) {
		
		buyer.setID(uniIDGenerator.getNextId());
		
		return jdbcTemplate.update("insert into buyer (BID, BNAME) " + "values(?, ?)",
				new Object[] { buyer.getId(), buyer.getbName() });
	}

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

	public List<Project> findAllProjects() {
		return jdbcTemplate.query("select * from project", new ProjectRowMapper());
	}
	
	public Project findProjectById(long pid) {
		return jdbcTemplate.queryForObject("select proj.PID, proj.PNAME,  proj.MAX_BUDGET, proj.PSTATUS, buy.BNAME, MIN(bid.bid_amount) As BidAmount from project proj, buyer buy, bids bid where proj.pid=? and proj.pid = bid.pid and bid.bid = buy.bid  group by proj.PID;", new Object[] {pid},
				new ProjectRowMapper());
	}

	public boolean sellerExists(long sid) throws SQLException {
		ResultSet sqlID = (ResultSet) jdbcTemplate.query("select SID from seller", new SellerRowMapper());
		Integer qID = ((Integer) sqlID.getObject("SID")).intValue();
		if (qID == sid)
			return true;
		else
		return false;
	}
	public int insert(Project project) throws Exception, SQLException {
//		if(sellerExists(project.getsID())) {
		LocalDate cDate = LocalDate.now();
		project.setID(uniIDGenerator.getNextId());
		String pstatus = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (cDate.isBefore(LocalDate.parse(project.getpTimeLimit(),formatter)))
		{ 
			pstatus = "Active";
		}
		else if (cDate.isAfter(LocalDate.parse(project.getpTimeLimit(),formatter)))
		{ 
			pstatus = "Closed";
		}
		return jdbcTemplate.update("insert into project (PID, PNAME, PDESC, MAX_BUDGET, TIME_LIMIT, PSTATUS, SID) " + "values(?, ?, ?, ?, ?, ? , ?)",
				new Object[] { project.getId(), project.getpName(), project.getpDesc(), project.getMaxBudget(), project.getpTimeLimit(),pstatus,project.getsID() });
//		}
//		else
//			return 0;
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

		return jdbcTemplate.queryForObject("select * from bids where bid_id=?", new Object[] {bid_id},
				new BidRowMapper());
	}


	public int insert(Bid bid) {
		bid.setID(uniIDGenerator.getNextId());
		return jdbcTemplate.update("insert into BIDS (BID_ID, BID_AMOUNT, BID, PID) " + "values(?, ?, ?, ?)",
				new Object[] { bid.getId(), bid.getbAmount(), bid.getBuyID(), bid.getProjID() });
	}

}
