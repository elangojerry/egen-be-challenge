/**
 * Copyright Elango Chanderasekar, Inc. All rights reserved. Used by permission.
 * Miss using this code is illegal :-).
 */
package com.egen.dao;

import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.egen.entity.AppConfig;
import com.mongodb.MongoClient;

public class AppConfigDAO extends BasicDAO<AppConfig, Long> 
{

	public AppConfigDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
	}

}
