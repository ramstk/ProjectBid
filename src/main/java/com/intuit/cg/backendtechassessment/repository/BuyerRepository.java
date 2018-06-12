package com.intuit.cg.backendtechassessment.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.intuit.cg.backendtechassessment.domain.Buyer;

@Repository
public class BuyerRepository implements DataRepository<Buyer> {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private UniIDGen uniIDGenerator;

	class BuyerRowMapper implements RowMapper<Buyer> {

		@Override
		public Buyer mapRow(ResultSet buyer, int rowNum) throws SQLException {

			Buyer buy = new Buyer();
			buy.setID(buyer.getLong("BID"));
			buy.setbName(buyer.getString("BNAME"));

			return buy;
		}

	}

	@Override
	public List<Buyer> findAll() {
		return jdbcTemplate.query("select * from Buyer", new BuyerRowMapper());
	}

	@Override
	public Buyer findById(long id) {
		return jdbcTemplate.queryForObject("select * from Buyer where bid=?", new Object[] { id },
				new BuyerRowMapper());
	}

	@Override
	public int insert(Buyer item) {
		item.setID(uniIDGenerator.getNextId());
		return jdbcTemplate.update("insert into buyer (BID, BNAME) " + "values(?, ?)",
				new Object[] { item.getId(), item.getbName() });
	}

}
