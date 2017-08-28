/**
 ********************************************************************
 * This script contains publish functions for sensor data #
 * These can be subscribed to by ddp clients
 *
 * Some subscriptions require a user to be loggedin and or an admin
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */

/**
 * Publication which subscribes to the entire temperature collection
 * 
 * User must be an admin
 * 
 * @return subscription to the temperature collection or null
 */
Meteor.publish('allTemperature', function() {
    console.log("Received a Temperature subscription request");
    if(Meteor.accountHelpers.isAdmin(this.userId)) {
        console.log("User is an admin, returning temperature collection");
        return Temperature_Data.find();
    } else {
        console.log("User is not an admin, returning null");
        return null;
    }
});


/**
 * Publication which subscribes to the temperature data for a given device
 * 
 * User must be logged in
 * 
 * @return subscription to the temperature collection for the given device or null
 */
Meteor.publish('temperatureForDevice', function (deviceId) {
    console.log("Received a temperature subscription request for device: " + deviceId);
    check(deviceId, String);
    if (this.userId) {
        console.log("User is logged in, returning temperature collection for the given device");
        return Temperature_Data.find({ device_id: deviceId });
    } else{
        console.log("No user logged in, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to the latest temperature data for a given device
 * 
 * User must be logged in
 * 
 * @return subscription to latest temperature object for the given device or null
 */
Meteor.publish('realtimeTemperatureForDevice', function(deviceId) {
   console.log("Received a real time temperature subscription request for device: " + deviceId);
   check(deviceId, String);
   if(this.userId) {
       console.log("User is logged in, returning real time temperature data for the given device");
       return Temperature_Data.findOne({device_id: deviceId}, {sort: {createdAt: -1}});
   } else {
       console.log("No user logged in, returning null");
       return null;
   }
});

/**
 * Publication which subscribes to the entire altitude collection
 * 
 * User must be an admin
 * 
 * @return subscription to the altitude collection or null
 */
Meteor.publish('allAltitude', function() {
    console.log("Received an Altitude subscription request");
    if(Meteor.accountHelpers.isAdmin(this.userId)) {
        console.log("User is an admin, returning altitude collection");
        return Altitude_Data.find();
    } else {
        console.log("User is not an admin, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to the altitude collection for the given device
 * 
 * User must be logged in
 * 
 * @return subscription to altitude data for the given device or null
 */
Meteor.publish('altitudeForDevice', function (deviceId) {
    console.log("Received an altitude subscription request for device: " + deviceId);
    check(deviceId, String);
    if (this.userId) {
        console.log("User is logged in, returning altitude collection for the given device");
        return Altitude_Data.find({ device_id: deviceId });
    } else {
        console.log("User is not logged in, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to the real time altitude data for the given device
 * 
 * User must be logged in
 * 
 * @return subscription to real time altitude data for the given device or null
 */
Meteor.publish('realtimeAltitudeForDevice', function(deviceId) {
    console.log("Received a real-time altitude subscription request for device: " + deviceId);
    check(deviceId, String);
    if(this.userId) {
        console.log("user is logged in, returning real time altitude data for the given device: " + deviceId);
        return Altitude_Data.findOne({device_id: deviceId}, {sort: {createdAt: -1}});
    } else {
        console.log("User is not logged in, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to the entire atmospheric pressure collection
 * 
 * User must be an admin
 * 
 * @return subscription to the atmospheric pressure collection or null
 */
Meteor.publish('allAtmoPressure', function() {
    console.log("Received an Atmosphieric Pressure subscriptiopn request");
    
    if(Meteor.accountHelpers.isAdmin(this.userId)) {
        console.log("User is an admin, returning atmosperic pressure collection");
        return Atmospheric_Pressure_Data.find();
    } else {
        console.log("User is not an admin, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to the atmospheric pressure collection for the given device
 * 
 * User must be logged in
 * 
 * @return subscription to the atmospheric pressure collection for the given device or null
 */
Meteor.publish('atmoPressureForDevice', function (deviceId) {
    console.log("Received an atmospheric pressure subscription request for device: " + deviceId);
    if (this.userId) {
        console.log("User is logged in, returning atmosphericm pressure data for the given device");
        return Atmospheric_Pressure_Data.find({ device_id: deviceId })
    } else {
        console.log("No user logged in, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to the real time atmospheric pressure data for the given device
 * 
 * User must be logged in
 * 
 * @return subscription to real time atmospheric pressure data for the given device or null
 */
Meteor.publish('realtimeAtmoPressureForDevice', function(deviceId) {
    console.log("Received a real-time atmospheric pressure subscription request for device: " + deviceId);
    check(deviceId, String);
    if(this.userId) {
        console.log("user is logged in, returning real time atmospheric pressure data for the given device: " + deviceId);
        return Atmospheric_Pressure_Data.findOne({device_id: deviceId}, {sort: {createdAt: -1}});
    } else {
        console.log("User is not logged in, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to all motion detection data
 * 
 * User must be an admin
 * 
 * @return collection of motion detection data or null
 */
Meteor.publish('allMotionDetection', function() {
   console.log("Received collection request for motion data");
   if(Meteor.accountHelpers.isAdmin(this.userId)) {
       console.log("User is admin, returning collection of all motion detection data");
       Motion_Detection_Data.find();
   } else {
       console.log("user is not an admin, returning null");
   }
});

/**
 * Publication which subscribes to motion detection data for the given device
 * 
 * User must be logged in
 * 
 * @return subscription to motion detection data for the given device or null
 */
Meteor.publish('motionDetectionForDevice', function (deviceId) {
    console.log("Received a motion data subscription request for device: " + deviceId);
    check(deviceId, String);
    if (this.userId) {
        console.log("User is logged in, returning motion detection data for the given device");
        return Motion_Detection_Data.find({ device_id: deviceId });
    } else {
        console.log("User is not logged in, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to real time motion detection data for the given device
 * 
 * User must be logged in
 * 
 * @return subscription to real time motion detection data for the given device or null
 */
Meteor.publish('realtimeMotionDetectionForDevice', function(deviceId) {
    console.log("Received a real time motion detection subscription request for device: " + deviceId);
    check(deviceId, String);
    if(this.userId) {
        console.log("User is logged in, returning real time motion detection data for the given device");
        return Motion_Detection_Data.findOne({device_id: deviceId}, {sort: {createdAt: -1}});
    } else {
        console.log("user is not logged in, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to all device movement data
 * 
 * User must be an admin
 * 
 * @return subscription to device movement data or null
 */
Meteor.publish('allDeviceMovement', function() {
   console.log("Received an all device movement subscription request");
   if(Meteor.accountHelpers.isAdmin(this.userId)) {
        console.log("User is an admin, subscribing to all device movement data");
        return Device_Moved_Data.find();
   } else {
       console.log("User is not an admin, return null");
       return null;
   }
});

/**
 * Publication which subscribes to device movement data for the given device
 * 
 * User must be logged in
 * 
 * @return subscription to device movement data for the given device or null
 */
Meteor.publish('deviceMovementForDevice', function (deviceId) {
    console.log("Received a device movement subscription request for device: " + deviceId);
    check(deviceId, String);
    if (this.userId) {
        console.log("User is loggedin, returning collection data for device moved");
        return Device_Moved_Data.find({ device_id: deviceId });
    } else {
        console.log("User is not logged in, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to real time device movement data for the given device
 * 
 * User must be logged in
 * 
 * @return subscription to real time device movement for the given device or null
 */
Meteor.publish('realtimeDeviceMovementForDevice', function(deviceId) {
    console.log("Received a real time device movement subscription request for device: " + deviceId);
    check(deviceId, String);
    if(this.userId) {
        console.log("User is logged in, returning real time device movement data for the given device");
        return Device_Moved_Data.findOne({device_id: deviceId}, {sort: {createdAt: -1}});
    } else {
        console.log("user is not logged in, returning null");
        return null;
    }
});

/**
 * Publication which subscribes to the sensor settings for the given device
 * 
 * @return subscription to sensor settings for the given device
 */
Meteor.publish('sensorSettingsForDevice', function (deviceId) {
    console.log("Received a sensor settings subscription request for device: " + deviceId);
    check(deviceId, String);
    return Sensor_Setting.find({ device_id: deviceId });
});

Meteor.publish('allSensorSettings', function() {
   console.log("Received an all sensor settings subscription request"); 
   if(Meteor.accountHelpers.isAdmin(this.userId)) {
       return Sensor_Setting.find();
   } else {
       return null;
   }
});

/**
 * Publication which subscribes to a collection of measurement types used by sensors
 * 
 * @return subscription to measurement types collection
 */
Meteor.publish('measurement_types', function() {
    return Measurement_Type.find();
});

Meteor.publish('measurementTypeForId', function(typeId) {
    check(typeId, String);
    if(typeId) {
        return Measurement_Type.find({_id: typeId});
    } else {
        return null;
    }
});
