/**
 * Copyright Elango Chanderasekar, Inc. All rights reserved. Used by permission.
 * Miss using this code is illegal :-).
 */
package com.egen.rules;

import org.bson.types.ObjectId;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;

import com.egen.entity.Alerts;
import com.egen.service.MongoService;

@Rule(name="Weight Drop", description="Action on 10% decrease in weight")
public class WeightDropRule 
{
	@Autowired
	MongoService mongoService;
	
	@Condition
	public boolean when(@Fact("base-weight") int base, @Fact("current-weight") int current)
	{
		boolean result = false;
		if(current < (base - base*(10.0/100)))
		{
			result = true;
		}
		
		return result;
	}
	
	@Action
	public void sendWeightDropAlert(Facts facts)
	{
		String message = "Weight decreased more than 10%";
		mongoService.saveAlerts(new Alerts((ObjectId)facts.get("id"), System.currentTimeMillis(), (Integer)facts.get("current-weight"), message));
	}
}
