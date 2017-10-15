/**
 * Copyright Elango Chanderasekar, Inc. All rights reserved. Used by permission.
 * Miss using this code is illegal :-).
 */
package com.egen.service;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;

import com.egen.dao.AlertsDAO;
import com.egen.dao.AppConfigDAO;
import com.egen.dao.MetricsDAO;
import com.egen.entity.Alerts;
import com.egen.entity.AppConfig;
import com.egen.entity.Metrics;
import com.mongodb.MongoClient;

/**
 * This can be structured by DAO vice. But no time :-)
 *  
 * @author Elango
 */
public class MongoService 
{
	private Morphia morphia;
	private Datastore datastore;
	private MongoClient client;
	private String dbName;
	
	public void configure()
	{
		this.datastore = morphia.createDatastore(client, dbName);
	}

	public Morphia getMorphia() {
		return morphia;
	}

	public void setMorphia(Morphia morphia) {
		this.morphia = morphia;
	}

	public Datastore getDatastore() {
		return datastore;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}
	
	public MongoClient getClient() {
		return client;
	}

	public void setClient(MongoClient client) {
		this.client = client;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public Object saveMetric(Metrics metrics)
	{
		MetricsDAO dao = new MetricsDAO(getClient(), getMorphia(), dbName);
		Key<Metrics> key = dao.save(metrics);

		return key.getId();
	}
	
	public List<Metrics> getAllMetric()
	{
		List<Metrics> metrics = new ArrayList<Metrics>();
		Query<Metrics> query = getDatastore().createQuery(Metrics.class);
		
		MetricsDAO dao = new MetricsDAO(getClient(), getMorphia(), dbName);
		QueryResults<Metrics> metricsResult = dao.find(query);
		
		for(Metrics metric : metricsResult)
		{
			metrics.add(metric);
		}
		
		return metrics;
	}
	
	public List<Metrics> getAllMetric(Long startTime, Long endTime)
	{
		List<Metrics> metrics = new ArrayList<Metrics>();
		Query<Metrics> query = getDatastore().createQuery(Metrics.class);
		query.and(query.criteria("time").greaterThanOrEq(startTime),
				query.criteria("time").lessThanOrEq(endTime));
		
		MetricsDAO dao = new MetricsDAO(getClient(), getMorphia(), dbName);
		QueryResults<Metrics> metricsResult = dao.find(query);
		
		for(Metrics metric : metricsResult)
		{
			metrics.add(metric);
		}
		
		return metrics;
	}
	
	public void resetMetrics()
	{
		Query<Metrics> query = getDatastore().createQuery(Metrics.class);
		MetricsDAO dao = new MetricsDAO(getClient(), getMorphia(), dbName);
		
		dao.deleteByQuery(query);
	}
	
	public boolean saveAlerts(Alerts alerts)
	{
		AlertsDAO dao = new AlertsDAO(getClient(), getMorphia(), dbName);
		dao.save(alerts);
		
		return true;
	}
	
	public List<Alerts> getAllAlerts()
	{
		List<Alerts> alerts = new ArrayList<Alerts>();
		Query<Alerts> query = getDatastore().createQuery(Alerts.class);
		
		AlertsDAO dao = new AlertsDAO(getClient(), getMorphia(), dbName);
		QueryResults<Alerts> alertsResult = dao.find(query);
		
		for(Alerts alert : alertsResult)
		{
			alerts.add(alert);
		}
		
		return alerts;
	}
	
	public List<Alerts> getAllAlerts(Long startTime, Long endTime)
	{
		List<Alerts> alerts = new ArrayList<Alerts>();
		Query<Alerts> query = getDatastore().createQuery(Alerts.class);
		query.and(query.criteria("time").greaterThanOrEq(startTime),
				query.criteria("time").lessThanOrEq(endTime));
		
		AlertsDAO dao = new AlertsDAO(getClient(), getMorphia(), dbName);
		QueryResults<Alerts> alertsResult = dao.find(query);
		
		for(Alerts alert : alertsResult)
		{
			alerts.add(alert);
		}
		
		return alerts;
	}
	
	public void resetAlerts()
	{
		Query<Alerts> query = getDatastore().createQuery(Alerts.class);
		AlertsDAO dao = new AlertsDAO(getClient(), getMorphia(), dbName);
		
		dao.deleteByQuery(query);
	}
	
	public String getAppConfig(String configName)
	{
		Query<AppConfig> query = getDatastore().createQuery(AppConfig.class);
		query.and(query.criteria("configName").equalIgnoreCase(configName));
		
		AppConfigDAO dao = new AppConfigDAO(getClient(), getMorphia(), dbName);
		QueryResults<AppConfig> appConfig = dao.find(query);
		
		return appConfig.get().getValue();
	}
	
	public String saveAppConfig(AppConfig appConfig)
	{
		AppConfigDAO dao = new AppConfigDAO(getClient(), getMorphia(), dbName);
		Key<AppConfig> key = dao.save(appConfig);

		return key.getId().toString();
	}
}
