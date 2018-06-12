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

import com.intuit.cg.backendtechassessment.domain.Seller;

/**
 * @author RAM
 *
 */
@Repository
public class SellerRepository implements DataRepository<Seller> {
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

	@Override
	public List<Seller> findAll() {

		return jdbcTemplate.query("select * from Seller", new SellerRowMapper());
	}

	@Override
	public Seller findById(long sid) {
		return jdbcTemplate.queryForObject("select * from seller where sid=?", new Object[] { sid },
				new SellerRowMapper());
	}

	@Override
	public int insert(Seller item) {
		item.setID(uniIDGenerator.getNextId());
		return jdbcTemplate.update("insert into seller (SID, SNAME) " + "values(?, ?)",
				new Object[] { item.getId(), item.getsName() });
	}

}
