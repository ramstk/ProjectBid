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
import com.intuit.cg.backendtechassessment.domain.ProjBid;
import com.intuit.cg.backendtechassessment.domain.Project;
import com.intuit.cg.backendtechassessment.exception.BidNotFoundException;
import com.intuit.cg.backendtechassessment.exception.ProjectNotFoundException;
import com.intuit.cg.backendtechassessment.repository.ProjBidRepository;

/**
 * @author RAM
 *
 */
// to enable cross origin requests
@CrossOrigin(origins = "*")
@RestController
// @ExposesResourceFor(Project.class)
@RequestMapping(value = "/backendtechassessment", produces = "application/json")
public class ProjectBIDController {

	@Autowired
	ProjBidRepository<Bid> bidrepo;
	@Autowired
	ProjBidRepository<Project> prepo;
	@Autowired
	ProjBidRepository<ProjBid> pbrepo;

	public static final String PROJECTS = "/projects";
	public static final String BIDS = "/bids";

	@RequestMapping(method = RequestMethod.GET, value = PROJECTS)
	public List<Project> findAllProjects() throws SQLException {
		List<Project> resultMap = prepo.findAllProjects();
		if (resultMap == null) {
			throw new ProjectNotFoundException("Projects not found");
		}
		return resultMap;
	}

	@RequestMapping(method = RequestMethod.GET, value = PROJECTS + "/{pID}")
	public ProjBid findProjectById(@PathVariable long pID) {
		ProjBid proj = new ProjBid();
		proj = pbrepo.findProjectById(pID);
		if (proj == null) {
			throw new ProjectNotFoundException(pID, "Project not found");
		}
		return proj;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = PROJECTS)
	public int addProject(@RequestBody Project project) throws SQLException {
		int result = 0;

		result = prepo.insert(project);
		if (result != 1) {
			throw new ProjectNotFoundException("Unable to insert, check values");
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = BIDS)
	public List<Bid> findAllBIDS() {
		List<Bid> resultMap = bidrepo.findAllBIDS();
		if (resultMap == null) {
			throw new BidNotFoundException("Bids not found");
		}
		return resultMap;
	}

	@RequestMapping(method = RequestMethod.GET, value = BIDS + "/{bid_ID}")
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
