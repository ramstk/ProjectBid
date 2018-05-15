/**
 * 
 */
package com.intuit.cg.backendtechassessment.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.cg.backendtechassessment.domain.Seller;
import com.intuit.cg.backendtechassessment.exception.SellerNotFoundException;
import com.intuit.cg.backendtechassessment.repository.SellerRepository;

/**
 * @author RAM
 *
 */
// to enable cross origin requests
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/sellers")
public class SellerController {
	@Autowired
	SellerRepository srepo;

	// public static final String SELLERS = "/sellers";

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Seller>> findAllSellers() throws SQLException {
		List<Seller> resultMap = srepo.findAll();
		if (resultMap == null) {
			throw new SellerNotFoundException("Sellers not found");
		}

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{sID}", produces = "application/json")
	public Seller findSellerById(@PathVariable long sID) throws SQLException {
		Seller sell = new Seller();
		sell = srepo.findById(sID);
		if (sell == null) {
			throw new SellerNotFoundException(sID, "Seller not found");
		}
		return sell;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public String addSeller(@RequestBody Seller seller) {
		int result = 0;
		String sellerAdd = " Added new Seller";
		result = srepo.insert(seller);
		if (result != 1) {
			throw new SellerNotFoundException("Unable to insert, check values");
		}
		return sellerAdd;
	}

}
