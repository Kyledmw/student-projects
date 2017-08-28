/**
 ********************************************************************
 * This script contains collection definitions
 *
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */

Account_Level = new Mongo.Collection('account_level');
Monitoring_Level = new Mongo.Collection('monitoring_level');
Device = new Mongo.Collection('device');
DeviceFeature = new Mongo.Collection('device_feature');
DeviceFeature_Implementation = new Mongo.Collection('device_feature_implementation');
Temperature_Data = new Mongo.Collection('temperature_data');
Measurement_Type = new Mongo.Collection('measurement_type');
Atmospheric_Pressure_Data = new Mongo.Collection('atmospheric_pressure_data');
Altitude_Data  = new Mongo.Collection('altitude_data');
Camera_Data = new Mongo.Collection('camera_data');
Motion_Detection_Data = new Mongo.Collection('motion_detection_data');
Device_Moved_Data = new Mongo.Collection('device_moved_data');
Camera_Setting = new Mongo.Collection('camera_setting');
Recording_Data = new Mongo.Collection('recording_data');
Sensor_Setting = new Mongo.Collection('sensor_setting');
Alarm_Setting = new Mongo.Collection('alarm_setting');
Data_Trigger = new Mongo.Collection('data_trigger');
Power_Socket = new Mongo.Collection('power_socket');