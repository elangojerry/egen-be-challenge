/**
 * Copyright Elango Chanderasekar, Inc. All rights reserved. Used by permission.
 * Miss using this code is illegal :-).
 */
package com.egen.controllers;

import java.util.List;

import org.jeasy.rules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.egen.entity.Alerts;
import com.egen.rules.WeightDropRule;
import com.egen.rules.WeightShootRule;
import com.egen.service.MongoService;

@RestController
@RequestMapping(value="/alert")
public class AlertController 
{
	@Autowired
	MongoService mongoService;
	
	@Autowired
	RulesEngineBuilder rulesEngineBuilder;
	
	@Autowired
	WeightDropRule dropRule;
	
	@Autowired
	WeightShootRule shootRule;
	
	@RequestMapping(value="/create", method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
	public boolean create(@RequestBody Alerts alerts)
	{
		return mongoService.saveAlerts(alerts);
	}
	
	@RequestMapping(value="/read", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public List<Alerts> readAll()
	{
		return mongoService.getAllAlerts();
	}
	
	@RequestMapping(value="/read/range", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public List<Alerts> readByTime(@RequestParam Long startDate, @RequestParam Long endDate)
	{
		return mongoService.getAllAlerts(startDate, endDate);
	}
	
//	@RequestMapping(value="/rule/test", method=RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public void testRule(@RequestParam int base, @RequestParam int current)
//	{
//		Rules rules = new Rules();
//		rules.register(shootRule);
//		rules.register(dropRule);
//		RulesEngine rulesEngine = rulesEngineBuilder.build();
//		
//		Facts facts = new Facts();
//		facts.put("base-weight", base);
//		facts.put("current-weight", current);
//		
//		rulesEngine.fire(rules, facts);
//	}
	
	@RequestMapping(value="/reset", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public String reset()
	{
		mongoService.resetAlerts();
		return "Alert mongo reset complete!!!";
	}
}
