/**
 * 
 */
package com.intuit.cg.backendtechassessment.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.cg.backendtechassessment.domain.Bid;
import com.intuit.cg.backendtechassessment.exception.BidNotFoundException;
import com.intuit.cg.backendtechassessment.repository.BidRepository;

/**
 * @author RAM
 *
 */
// to enable cross origin requests
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/backendtechassessment")
public class ProjectBIDController {

	@Autowired
	BidRepository<Bid> bidrepo;

	public static final String BIDS = "/bids";

	@RequestMapping(method = RequestMethod.GET, value = BIDS, produces = "application/json")
	public List<Bid> findAllBIDS() {
		List<Bid> resultMap = bidrepo.findAllBIDS();
		if (resultMap == null) {
			throw new BidNotFoundException("Bids not found");
		}
		return resultMap;
	}

	@RequestMapping(method = RequestMethod.GET, value = BIDS + "/{bid_ID}", produces = "application/json")
	public Bid findBidById(@PathVariable long bid_ID) {
		Bid bid = new Bid();
		bid = bidrepo.findBIDById(bid_ID);
		if (bid == null) {
			throw new BidNotFoundException(bid_ID, "Bids not found");
		}
		return bid;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = BIDS)
	public int addBid(@RequestBody Bid bid) throws SQLException {
		int result = 0;

		result = bidrepo.insert(bid);
		if (result != 1) {
			throw new BidNotFoundException("Unable to insert, check values");
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "autoBid/{pid}/{buyId}")
	public int autoBid(@PathVariable long pid, @PathVariable long buyId) throws SQLException {
		int result = bidrepo.autoBid(pid, buyId);

		if (result != 1) {
			throw new BidNotFoundException("Unable to insert, check values");
		}
		return result;
	}

}
