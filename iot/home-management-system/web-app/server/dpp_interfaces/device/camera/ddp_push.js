/**
 ********************************************************************
 * This script contains ddp push functions for devices with cameras 
 * These can be used by ddp clients
 *
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */

Meteor.methods({
    
    /**
     * Sends camera data to the server from a given device
     * 
     * @param deviceId, id of the device sending the data
     * @param cameraData, camera data model from device, contains image and timestamp
     * @return success or error
     */
	'pushCameraData': function(deviceId, cameraData) {
		console.log("Camera data received from device: " + deviceId);
        check(deviceId, String);
        var cameraSettings = Camera_Setting.findOne({device_id: deviceId});
        var recording = '-1';
        if(cameraSettings.recording) {
            console.log("Recording found");
            recording = Recording_Data.findOne({device_id: deviceId, end_time: -1})._id;
        }
        Meteor.modelFactories.createCameraData(deviceId, cameraData.data.image, cameraData.data.timestamp, recording);
        return "success";
	},
	
    /**
     * Sends camera settings to the server for a given device
     * 
     * User must be logged in
     * 
     * @param deviceId, id of the device sending the data
     * @param settingsData, camera settings data
     * @return success or error
     */
	'pushCameraSettings': function(deviceId, settingsData) {
        if(typeof settingsData == "string") {
            settingsData = JSON.parse(settingsData);
        }
		console.log("Camera settings received for device: " + deviceId);
        check(deviceId, String);

		check(settingsData, {
			_id: String,
		    power_switch: Number,
		    recording: Number
		});
		
        
	
	if(Meteor.userId()) {
            var cameraSettings = Camera_Setting.findOne({device_id: deviceId});
            if(!cameraSettings) {
                console.log("No camera settings found, creating settings");
                cameraSettings = Meteor.modelFactories.createCameraSetting(deviceId, 1, 0);
            }
            if(cameraSettings.recording && !settingsData.recording) {
                console.log("Searching for recording")
                var recording = Recording_Data.findOne({device_id: deviceId, end_time: -1});
                if(recording) {
                    console.log("Ending recording");
                    Recording_Data.update(recording._id, {$set: {end_time: Date.now()}});
                }
            } else if(!cameraSettings.recording && settingsData.recording) {
                console.log("Starting Recording");
                Meteor.modelFactories.createRecordingData(deviceId, Date.now(), -1);
            }
            Camera_Setting.update(cameraSettings._id, {$set: {power_switch: settingsData.power_switch, recording: settingsData.recording}}, {}, function(err, res) {
                if(err) {
                    console.log("Error updating camera settings for device: " + deviceId);
                } else {
                    console.log("Updated camera settings for device: " + deviceId);
                }
            });
        } else {
            console.log("User not logged in");
            return "error";
        }
	},

    'cameraDataForId': function(cameraDataId) {
        console.log("Received camera data for id request for id: " + cameraDataId);
        check(cameraDataId, String);

        if(Meteor.userId() && cameraDataId){
            return Camera_Data.find(cameraDataId);
        }
    },

    'cameraDataForRecordingId' : function(recordingId) {
        console.log("Received camera data for recording id for id: " + recordingId);
        check(recordingId, String);

        if(Meteor.userId() && recordingId) {
            return Camera_Data.find({record_id: recordingId});
        }
    }
});
