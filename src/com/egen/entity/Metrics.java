/**
 * Copyright Elango Chanderasekar, Inc. All rights reserved. Used by permission.
 * Miss using this code is illegal :-).
 */
package com.egen.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Metrics 
{
	@Id
	private ObjectId objectId;
	private long time;
	private int bodyWeight;
	
	public Metrics()
	{
		
	}
	
	public Metrics(long time, int bodyWeight)
	{
		this.time = time;
		this.bodyWeight = bodyWeight;
	}
	
	public ObjectId getObjectId() {
		return objectId;
	}
	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
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
}
