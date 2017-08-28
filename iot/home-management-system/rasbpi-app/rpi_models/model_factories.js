function ModelFactories() {
	
	var tempModel = require('rpi_models/temperature_model.js');
	var atmoPressureModel = require('rpi_models/atmo_pressure_model.js');
	var altitudeModel = require('rpi_models/altitude_model.js');
	var alarmSettingsModel = require('rpi_models/alarm_settings_model.js');
	var cameraModel = require('rpi_models/camera_model.js');
	var cameraSettingsModel = require('rpi_models/camera_settings_model.js');
	var dataTriggerModel = require('rpi_models/data_trigger_model.js');
	var deviceMovedModel = require('rpi_models/device_moved_model.js');
	var motionModel = require('rpi_models/motion_model.js');
	var sensorSettingsModel = require('rpi_models/sensor_settings_model.js');
    var powerSocketModel = require('rpi_models/power_sockets_model.js');
	
	function createTemperatureData(timestamp, value, type) {
		var tempData = new tempModel({timestamp: timestamp, value: value, type: type});
		return tempData;
	}
	
	this.createTemperatureData = createTemperatureData;
	
	function createAtmosphereData(timestamp, value, type) {
		var atmoPressureData = new atmoPressureModel({timestamp: timestamp, value: value, type: type});
		return atmoPressureData;
	}
	
	this.createAtmosphereData = createAtmosphereData;
	
	function createAltitudeData(timestamp, value, type) {
		var altitudeData = new altitudeModel({timestamp: timestamp, value: value, type: type});
		return altitudeData;
	}
	
	this.createAltitudeData = createAltitudeData;
	
	function createMotionData(timestamp) {
		var motionDetected = new motionModel({timestamp: timestamp});
		return motionDetected;
	}
	
	this.createMotionData = createMotionData;
	
	function createCameraData(timestamp, image) {
		var cameraData = new cameraModel({timestamp: timestamp, image: image});
		return cameraData;
	}
	
	this.createCameraData = createCameraData;
	
	function createCameraSettingsData(powerSwitch) {
		var cameraSettingsData = new cameraSettingsModel({power_switch: powerSwitch});
		return cameraSettingsData;
	}
	
	this.createCameraSettingsData = createCameraSettingsData;
	
	function createSensorSettingsData(powerSwitch) {
		var sensorSetting = new sensorSettingsModel({power_switch: powerSwitch});
		return sensorSetting;
	}
	
	this.createSensorSettingsData = createSensorSettingsData;
	
	function createAlarmSettingsData(powerSwitch, triggerSwitch, duration) {
		var alarmSettings = new alarmSettingsModel({power_switch: powerSwitch, trigger_switch: triggerSwitch, duration: duration});
		return alarmSettings;
	}
	
	this.createAlarmSettingsData = createAlarmSettingsData;
	
	function createDataTriggerData(type, limit, alarmEnabled) {
		var dataTrigger = new dataTriggerModel({type: type, limit: limit, alarm_enabled: alarmEnabled});
		return dataTrigger;
	}
	
	this.createDataTriggerData = createDataTriggerData;
	
	function createDeviceMovedData(timestamp) {
		var devMoved = new deviceMovedModel({timestamp: timestamp});
		return devMoved;
	}
	
	this.createDeviceMovedData = createDeviceMovedData;
    
    function createPowerSocketData(socketNumber, powerSwitch) {
        var powerSocket = new powerSocketModel({socket_number: socketNumber, power_switch: powerSwitch});
        return powerSocket;
    }
    
    this.createPowerSocketData = createPowerSocketData;
}


module.exports = new ModelFactories();