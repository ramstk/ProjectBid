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

import com.intuit.cg.backendtechassessment.domain.ProjBid;
import com.intuit.cg.backendtechassessment.domain.Project;
import com.intuit.cg.backendtechassessment.exception.ProjectNotFoundException;
import com.intuit.cg.backendtechassessment.repository.ProjectRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/projects")

public class ProjectController {
	@Autowired
	ProjectRepository prepo;
	@Autowired
	ProjectRepository pbrepo;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Project> findAllProjects() throws SQLException {
		List<Project> resultMap = prepo.findAll();
		if (resultMap == null) {
			throw new ProjectNotFoundException("Projects not found");
		}
		return resultMap;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{pID}", produces = "application/json")
	public ProjBid findProjectById(@PathVariable long pID) {
		ProjBid proj = new ProjBid();
		proj = pbrepo.findProjectById(pID);
		if (proj == null) {
			throw new ProjectNotFoundException(pID, "Project not found");
		}
		return proj;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public int addProject(@RequestBody Project project) throws SQLException {
		int result = 0;

		result = prepo.insert(project);
		if (result != 1) {
			throw new ProjectNotFoundException("Unable to insert, check values");
		}
		return result;
	}

}
