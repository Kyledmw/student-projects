/**
 ********************************************************************
 * This script contains ddp push functions for sensors to be used by ddp clients
 *
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
Meteor.methods({

    /**
     * Sends temperature data to the server for a given device
     * 
     * @param deviceId, id of the device sending the data
     * @param temperatureData, temperature data model from the device
     * @return success or error
     */
    'pushTemperatureData': function (deviceId, temperatureData) {
        console.log("Received temperature data from device: " + deviceId);
        check(deviceId, String);
        check(temperatureData.data, {
            type: String,
            timestamp: Number,
            value: Number
        });
        var type = Measurement_Type.findOne({ type: temperatureData.data.type });
        if (type) {
            console.log("Creating Temperature Data");
            Meteor.modelFactories.createTemperatureData(deviceId, temperatureData.data.timestamp, temperatureData.data.value, type._id);
        } else {
            console.log("Couldnt find measurement type");
            return "error";
        }
    },

    /**
     * Sends atmospheric pressure data to the server for a given device
     * 
     * @param deviceId, id of the device sending the data
     * @param pressureData, pressure data model from the device
     * @return success or error
     */
    'pushAtmoPressureData': function (deviceId, pressureData) {
        console.log("Received atmospheric pressure data from device: " + deviceId);
        check(deviceId, String);
        check(pressureData.data, {
            timestamp: Number,
            type: String,
            value: Number
        });
        var type = Measurement_Type.findOne({ type: pressureData.data.type });
        if(type) {
            console.log("Creating Atmospheric Pressure Data");
            Meteor.modelFactories.createAtmosphericPressureData(deviceId, pressureData.data.timestamp, pressureData.data.value, type._id)
        } else {
            console.log("Couldnt find measurement type");
            return "error";
        }
       },

    /**
     * Sends altitude data to the server for a given device
     * 
     * @param deviceId, id of the device sending the data
     * @param altitudeData, altitude data model from the device
     * @return success or error
     */
    'pushAltitudeData': function (deviceId, altitudeData) {
        console.log("Received altitude data from device: " + deviceId);
        check(deviceId, String);
        check(altitudeData.data, {
            timestamp: Number,
            type: String,
            value: Number
        });
        var type = Measurement_Type.findOne({ type: altitudeData.data.type });
        if(type) {
            console.log("Creating Altitude Data");
            Meteor.modelFactories.createAltitudeData(deviceId, altitudeData.data.timestamp, altitudeData.data.value, type._id);
        } else {
            console.log("Couldnt find measurement type");
            return "error";
        }
     },

    /**
     * Sends motion detected data to the server for a given device
     * 
     * @param deviceId, id of the device sending the data
     * @param detectionData, motion detected data model from the device
     * @return success or error
     */
    'pushMotionDetected': function (deviceId, detectionData) {
        console.log("Received that motion was detected from device: " + deviceId);
        check(deviceId, String);
        check(detectionData.data, {
            timestamp: Number
        });
        var camera = Camera_Data.findOne({ device_id: deviceId, timestamp: detectionData.data.timestamp });
        var camId = '';
        if(camera) {
            console.log("Camera data found");
            camId = camera._id;
        }
        Meteor.modelFactories.createMotionDetectionData(deviceId, detectionData.data.timestamp, camId);
    },

    /**
     * Sends device moved data to the server for a given device
     * 
     * @param deviceId, id of the device sending the data
     * @param data, device moved data model from the device
     * @return success or error
     */
    'pushDeviceMoved': function (deviceId, data) {
        console.log("Received device moved notification from device: " + deviceId);
        check(deviceId, String);
        check(data.data, {
            timestamp: Number
        });
        Meteor.modelFactories.createDeviceMovedData(deviceId, data.data.timestamp);
    },

    /**
     * Sends sensor settings to the server for a given device
     * 
     * User must be logged in
     * 
     * @param deviceId, id of the device sending the data
     * @param settingsData, sensor settings data for the device
     * @return success or error
     */
    'pushSensorSettings': function (deviceId, settingsData) {
        if(typeof settingsData == "string") {
            settingsData = JSON.parse(settingsData);
        }
        console.log("Received sensor settings data for device: " + deviceId);
        check(deviceId, String);
	if(settingsData._id) {

        check(settingsData, {
		_id: String,
            power_switch: Number
        });
	} else {

        check(settingsData, {
            power_switch: Number
        });
	}
        if (this.userId) {
            var sensorSettings = Sensor_Setting.findOne({ device_id: deviceId });
            if(!sensorSettings) {
                sensorSettings = Meteor.modelFactories.createSensorSetting(deviceId, 1);
            }
            Sensor_Setting.update(sensorSettings._id, { $set: {power_switch: settingsData.power_switch }}, {}, function(err, res) {
                if(err) {
                    console.log("Error updating settings");
                } else {
                    console.log("Successfully updated settings");
                }
            });
        } else {
            console.log("User not logged in");
            return "error";
        }
    },

    'measurementTypeForId': function(typeId) {
        check(typeId, String);
        if(typeId) {
	    console.log("Received Measurement for Type Id Push: " + typeId);
            return Measurement_Type.findOne({_id: typeId});
        } else {
            return null;
        }
    }

});
