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
import com.intuit.cg.backendtechassessment.domain.Buyer;
import com.intuit.cg.backendtechassessment.domain.Project;
import com.intuit.cg.backendtechassessment.domain.Seller;
import com.intuit.cg.backendtechassessment.repository.ProjBidRepository;







/**
 * @author RAM
 *
 */
//to enable cross origin requests
@CrossOrigin(origins = "*")
@RestController
//@ExposesResourceFor(Project.class)
@RequestMapping(value = "/backendtechassessment", produces = "application/json")
public class ProjectBIDController {
@Autowired
ProjBidRepository<Seller> srepo;
@Autowired
ProjBidRepository<Buyer> brepo;
@Autowired
ProjBidRepository<Bid> bidrepo;
@Autowired
ProjBidRepository<Project> prepo;

public static final String PROJECTS = "/projects";
public static final String SELLERS = "/sellers";
public static final String BUYERS = "/buyers";
public static final String BIDS = "/bids";



@RequestMapping (SELLERS)
public List<Seller> findAllSellers(){ 
	return srepo.findAllSellers();
}
@RequestMapping (SELLERS + "/{sID}")
public Seller findSellerById(@PathVariable long sID) {
	
	return srepo.findSellerById(sID);
}
@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = SELLERS)
public int addSeller(@RequestBody Seller seller) {
	return srepo.insert(seller);
}

@RequestMapping (BUYERS)
public List<Buyer> findAllBuyers(){
	return brepo.findAll();
}
@RequestMapping (BUYERS + "/{bID}")
public Buyer findBuyerById(@PathVariable long bID) {
	
	return brepo.findById(bID);
}
@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = BUYERS)
public int addBuyer(@RequestBody Buyer buyer) {
	return brepo.insert(buyer);
}
@RequestMapping (PROJECTS)
public List<Project> findAllProjects(){
	return prepo.findAllProjects();
}
@RequestMapping (PROJECTS + "/{pID}")
public Project findProjectById(@PathVariable long pID) {
	
	return prepo.findProjectById(pID);
}
@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = PROJECTS)
public int addProject(@RequestBody Project project) throws Exception, SQLException{
	return prepo.insert(project);
}

@RequestMapping (BIDS)
public List<Bid> findAllBIDS(){
	return bidrepo.findAllBIDS();
}
@RequestMapping (BIDS + "/{bid_ID}")
public Bid findBidById(@PathVariable long bid_ID) {
	
	return bidrepo.findBIDById(bid_ID);
}
@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = BIDS)
public int addBid(@RequestBody Bid bid) {
	return bidrepo.insert(bid);
}

}
