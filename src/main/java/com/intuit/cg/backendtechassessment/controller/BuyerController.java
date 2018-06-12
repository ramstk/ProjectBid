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

import com.intuit.cg.backendtechassessment.domain.Buyer;
import com.intuit.cg.backendtechassessment.exception.BuyerNotFoundException;
import com.intuit.cg.backendtechassessment.repository.BuyerRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/buyers")
public class BuyerController {
	// public static final String BUYERS = "/buyers";
	@Autowired
	BuyerRepository brepo;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Buyer>> findAllBuyers() throws SQLException {

		List<Buyer> resultMap = brepo.findAll();
		if (resultMap == null) {
			throw new BuyerNotFoundException("Buyers not found");
		}

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{bID}", produces = "application/json")
	public Buyer findBuyerById(@PathVariable long bID) throws SQLException {
		Buyer buy = new Buyer();
		buy = brepo.findById(bID);
		if (buy == null) {
			throw new BuyerNotFoundException(bID, "Buyer not found");
		}

		return buy;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public String addBuyer(@RequestBody Buyer buyer) {
		int result = 0;
		String buyerAdd = "Buyer has been added";
		result = brepo.insert(buyer);
		if (result != 1) {
			throw new BuyerNotFoundException("Unable to insert, check values");
		}
		return buyerAdd;
	}
}
