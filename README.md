# egen-be-challenge
This is cool little app to check the anomaly in your weight.

# API - Description
# /egen-be-challenge/metric/create
Used to create metrics, execute rules to create alerts and create alerts
{  
   "timeStamp":"1507864793409",
   "value":"107"
}

# /egen-be-challenge/metric/read
Used to read all data in metrics collection

# /egen-be-challenge/metric/read/range?startTime=1508031747580&endTime=1508031762739
Used to read selective data from metrics collection

# /egen-be-challenge/alert/create
Used to create alert
{  
   "time":1001,
   "bodyWeight":200,
   "message":"The body weight increased"
}

# /egen-be-challenge/alert/read
Used to read all data in alerts collection

# /egen-be-challenge/alert/read/range?startDate=1508031676949&endDate=1508031808199
Used to read selective data from alerts collection

# /egen-be-challenge/metric/reset
Used to delete all data in metrics collection

# /egen-be-challenge/alert/reset
Used to delete all data in alerts collection

# /egen-be-challenge/metric/set/base-weight
Used to set base weight in app-configs collection.
{  
   "configName":"baseWeight",
   "value":"120"
}

# /egen-be-challenge/metric/get/base-weight/baseWeight
Used to get base weight from app-configs collection. This will be used by "metrics/create" to pass it as rule facts inorder to create alerts.

# Sequence of steps to run the app
1. Set the base weight in the app
2. Create anomaly by executing below sensor emulator command
  a) java -jar -Dbase.value=120 -Dapi.url=http://localhost:8080/egen-be-challenge/metric/create sensor-emulator-0.0.1-SNAPSHOT.jar
3. Use the read service to view all metrics and alerts
4. Metrics and Alerts collections are connected through the BSON ObjectId.
5. Use the reset api's to clean the collections
6. Start from step 1 to track new weight

