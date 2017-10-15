/**
 * Copyright Elango Chanderasekar, Inc. All rights reserved. Used by permission.
 * Miss using this code is illegal :-).
 */
package com.egen.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egen.entity.AppConfig;
import com.egen.entity.Metrics;
import com.egen.rules.WeightDropRule;
import com.egen.rules.WeightShootRule;
import com.egen.service.MongoService;
import com.egen.vo.MetricInputVO;

@RestController
@RequestMapping(value="/metric")
public class MetricController 
{
	@Autowired
	MongoService mongoService;
	
	@Autowired
	RulesEngineBuilder rulesEngineBuilder;
	
	@Autowired
	WeightDropRule dropRule;
	
	@Autowired
	WeightShootRule shootRule;
	
	/**
	 * For Anomally
	 * java -jar -Dbase.value=120 -Dapi.url=http://localhost:8080/egen-be-challenge/metric/create sensor-emulator-0.0.1-SNAPSHOT.jar
	 * 
	 * @param metrics
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST ,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ObjectId create(@RequestBody MetricInputVO metricVO)
	{
		ObjectId objectId = (ObjectId)mongoService
				.saveMetric(new Metrics(new Long(metricVO.getTimeStamp()), new Integer( metricVO.getValue())));
		Rules rules = new Rules();
		rules.register(shootRule);
		rules.register(dropRule);
		RulesEngine rulesEngine = rulesEngineBuilder.build();
		
		Facts facts = new Facts();
		facts.put("base-weight", new Integer(getBaseWeight("baseWeight")));
		facts.put("current-weight", new Integer( metricVO.getValue()));
		facts.put("id", objectId);
		
		rulesEngine.fire(rules, facts);
		
		return objectId;
	}
	
	@RequestMapping(value="/read", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public List<Metrics> readAll()
	{
		return mongoService.getAllMetric();
	}
	
	@RequestMapping(value="/read/range", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Metrics> readByTime(@RequestParam long startTime, @RequestParam long endTime)
	{
		return mongoService.getAllMetric(startTime, endTime);
	}
	
	@RequestMapping(value="/reset", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public String reset()
	{
		mongoService.resetMetrics();
		return "Metrics mongo reset complete!!!";
	}
	
	@RequestMapping(value="/set/base-weight", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public String setBaseWeight(@RequestBody AppConfig appConfig)
	{
		mongoService.saveAppConfig(appConfig);
		return appConfig.getConfigName() + " set to " + appConfig.getValue();
	}
	
	@RequestMapping(value="/get/base-weight/{appConfigName}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getBaseWeight(@PathVariable String appConfigName)
	{
		return mongoService.getAppConfig(appConfigName);
	}
}
