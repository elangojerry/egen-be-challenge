/**
 * Copyright Elango Chanderasekar, Inc. All rights reserved. Used by permission.
 * Miss using this code is illegal :-).
 */
package com.egen.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Alerts 
{
	@Id
	private ObjectId id;
	private long time;
	private int bodyWeight;
	private String message;
	
	public Alerts()
	{
		
	}
	
	public Alerts(ObjectId id, long time, int bodyWeight, String message)
	{
		this.id = id;
		this.time = time;
		this.bodyWeight = bodyWeight;
		this.message = message;
	}
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getBodyWeight() {
		return bodyWeight;
	}
	public void setBodyWeight(int bodyWeight) {
		this.bodyWeight = bodyWeight;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
