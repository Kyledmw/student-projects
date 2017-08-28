/**
 ********************************************************************
 * This script contains publish functions related to devices.
 * 
 * These can be subscribed to by ddp clients.
 * 
 * Some subscriptions require a user to be loggedin and or an admin
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */

/**
 * Publication which subscribes to a collection of the devices belonging to the current user
 * 
 * User must be logged in
 * 
 * @return device subscription for the current user or null
 */
Meteor.publish('deviceForLoggedInUser', function () {
	console.log("Received a device subscription request");
    if(this.userId) {
        console.log("Valid for subscription")
        return Device.find({account_id: this.userId});
    } else {
        console.log("No user logged in");
        return null;
    }
});

/**
 * Publication that subscribes to a collection all of devices in the system
 * 
 * User must be logged in
 * 
 * @return device subscription for the current user or null
 */
Meteor.publish('allDevices', function() {
    console.log("Received an all device subscripion request");
    if(this.userId) {
        console.log("User is logged in, returning collection");
        return Device.find();
    } else {
        console.log("User not logged in");
        return null;
    }
});

/**
 * Publication that subscribes to devices for the given user id
 * 
 * @return device subscription for the given user id
 */
Meteor.publish('devicesForUserId', function(userId) {
    check(userId, String);
    if(userId) {
        console.log("User Id is not null");
        return Device.find({account_id: userId});
    }
});

/**
 * Publication that subscribes to devices feature implementation
 * 
 * Requires a user to be logged in
 * 
 * @param deviceId, id of device to retrieve implementations of
 * @return device feature impl subscription for the given device id
 */
Meteor.publish('deviceFeatureImplForDevice', function(deviceId) {
    console.log("Received a device feature implementation subscription request");
    check(deviceId, String);
    if(this.userId) {
        console.log("User was logged in, returning collection");
        return DeviceFeature_Implementation.find({'device_id': deviceId});
    } else {
        console.log("No user logged in, returning null");
        return null;
    }
});

/**
 * Publication that subscribes to a device feature
 * 
 * Requires a user to be logged in
 * 
 * @return device feature subscription for the given device feature id
 */
Meteor.publish('deviceFeatureForId', function(deviceFeatureId) {
    console.log("Received a device feature subscription request");
    check(deviceFeatureId, String);
    if(this.userId) {
        return DeviceFeature.find({'_id': deviceFeatureId});
    }
});

Meteor.publish('allDeviceFeatures', function() {
    if(Meteor.accountHelpers.isAdmin(this.userId)) {
        return DeviceFeature.find();
    }
});

Meteor.publish('allDeviceFeatureImpl', function() {
    if(Meteor.accountHelpers.isAdmin(this.userId)) {
        return DeviceFeature_Implementation.find();
    }
});