/**
 * Copyright Elango Chanderasekar, Inc. All rights reserved. Used by permission.
 * Miss using this code is illegal :-).
 */
package com.egen.vo;

/**
 * I would love to create the VO and mapper for each entity. But left it simple because the app is simple.
 * 
 * @author Elango
 */
public class MetricInputVO 
{
	private String timeStamp;
	private String value;
	
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
