/**
 * Copyright Elango Chanderasekar, Inc. All rights reserved. Used by permission.
 * Miss using this code is illegal :-).
 */
package com.egen.dao;

import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.egen.entity.Alerts;
import com.mongodb.MongoClient;

public class AlertsDAO extends BasicDAO<Alerts, Long> 
{

	public AlertsDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
	}

}
