/**
 ********************************************************************
 * This script contains publish functions for camera data 
 * These can be subscribed to by ddp clients
 *
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */

/**
 * Publication which subscribes to all camera data
 * 
 * User must be an admin
 * 
 * @return subscription to camera data or null
 */
Meteor.publish('allCameraData', function() {
   console.log("Received an all camera data subscription request");
   if(Meteor.accountHelpers.isAdmin(this.userId)) {
        console.log("User is an admin, subscribing to all camera data");
        return Camera_Data.find();
   } else {
       console.log("User is not an admin, return null");
       return null;
   }
});

/**
 * Publication which subscribes to the camera data collection for the given device
 * 
 * User must be logged in
 * 
 * @return subscription to camera data for the given device or null
 */
Meteor.publish('cameraDataForDevice', function (deviceId) {
	console.log("Received a camera subscription request for device: " + deviceId);
    check(deviceId, String);
    if(this.userId) {
        console.log("User is logged in, returning camera data for the given device");
        return Camera_Data.find({device_id: deviceId});
    } else {
        console.log("User is not logged in, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to the real time camera data for the given device
 * 
 * User must be logged in
 * 
 * @return subscription to real time camera data for the given device or null
 */
Meteor.publish('realTimeCameraForDevice', function(deviceId) {
    console.log("Received a real-time camera data subscription request for device: " + deviceId);
    check(deviceId, String);
    if(this.userId) {
        console.log("User is logged in, returning real time camera data subscription");
        return Camera_Data.find({device_id: deviceId}, {sort: {createdAt: -1}, limit: 1});
    } else {
        console.log("User is not logged in, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to a specific camera data object for the given device
 * 
 * User must be logged in
 * 
 * @return subscription to a specific camera data object for the given device or null
 */
Meteor.publish('specificCameraData', function (deviceId, cameraDataId) {
	console.log("Received a specific camera subscription request for device: " + deviceId);
    check(deviceId, String);
    check(cameraDataId, String);
    if(this.userId) {
        console.log("User is logged in, returning request camera data");
        return Camera_Data.findOne({device_id: deviceId, _id: cameraDataId});
    } else {
        console.log("User is not logged in, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to camera data for the given recording
 * 
 * User must be logged in
 * 
 * @return subscription to the camera data for the given recording id, or null
 */
Meteor.publish('cameraDataForRecording', function(recordingId) {
    console.log("Received a camera data request for the given recording id: " + recordingId);
    check(recordingId, String);
    if(this.userId) {
        console.log("User is logged in, returning collection of camera data");
        return Camera_Data.find({recording_id: recordingId});
    } else {
        console.log("User is not logged in, returning null");
        return null;
    }
});


Meteor.publish('allRecordingData', function() {
    console.log("Received an all recording data subscription request");
    if(Meteor.accountHelpers.isAdmin(this.userId)) {
        return Recording_Data.find();
    }
});

/**
 * Publication which subscribes to the camera settings for the given device
 * 
 * @return subscription to camera settings for the given device or null
 */
Meteor.publish('cameraSettingsForDevice', function (deviceId) {
	console.log("Received a camera settings subscription request for device: " + deviceId);
    check(deviceId, String);
    return Camera_Setting.find({device_id: deviceId});
});

Meteor.publish('allCameraSettings', function() {
    console.log("Received an all camera settings subscription request");
    if(Meteor.accountHelpers.isAdmin(this.userId)) {
        return Camera_Setting.find();
    }
});

/**
 * Publication which subscribes to the recording data for the given device
 * 
 * @return subscription to recording data for the given device or null
 */
Meteor.publish('recordingDataForDevice', function (deviceId) {
	console.log("Received a recording data subscription request for device: " + deviceId);
    check(deviceId, String);
    if(this.userId) {
        console.log("User logged in, returning recording data");
        return Recording_Data.find({device_id: deviceId});
    } else {
        console.log("User is not logged in, returning null");
        return null;
    }
});

