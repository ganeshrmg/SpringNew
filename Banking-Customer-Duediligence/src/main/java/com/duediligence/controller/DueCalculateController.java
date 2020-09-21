package com.duediligence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duediligence.model.Duediligence;
import com.duediligence.service.DueService;

@RestController
public class DueCalculateController {
	
	@Autowired
	DueService dueService;
	
	@RequestMapping(method=RequestMethod.GET,value="/getDuediligenceStatus/{customerid}")
	public Duediligence getDuediligenceStatus(@PathVariable(name="customerid",required=true) Long id) {
		Duediligence getStatus = dueService.getDueStatus(id);
	return getStatus;
	}

}
