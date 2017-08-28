/**
 ********************************************************************
 * This script contains publish functions for alarm data 
 * These can be subscribed to by ddp clients
 *
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */

/**
 * Publication to subscribe to alarm settings data for the given device
 * 
 * @param deviceId, id of the device with alarm
 * @return alarm settings object for the given device
 */
Meteor.publish('alarmSettingsForDevice', function (deviceId) {
	console.log("Received an alarm state subscription request from device: " + deviceId);
    check(deviceId, String);
    return Alarm_Setting.find({device_id: deviceId});
});

Meteor.publish('allAlarmSettings', function() {
   console.log("Received an alarm settings subscription request");
    if(Meteor.accountHelpers.isAdmin(this.userId)) {
        return Alarm_Setting.find();
    }
});

/**
 * Publication to subscribe to data triggers for the given device
 * 
 * @param deviceId, id of the device for these triggers
 * @return data triggers collection subscription
 */
Meteor.publish('dataTriggersForDevice', function (deviceId) {
	console.log("Received a data trigger subscription request from device: " + deviceId);
    check(deviceId, String);
    return Data_Trigger.find({device_id: deviceId});
});

Meteor.publish('allDataTriggers', function() {
    console.log("Received an all data triggers subscription request");
    if(Meteor.accountHelpers.isAdmin(this.userId)) {
        return Data_Trigger.find();
    }
});
