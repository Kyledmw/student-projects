/**
 ********************************************************************
 * This script contains ddp push functions for the alarm to be used by ddp clients
 *
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */

Meteor.methods({

    /**
     * Sends alarm settings to the server for a given device
     *
     * User must be logged in
     *
     * @param deviceId, id of the device sending the data
     * @param settingsData, alarm settings data
     * @return success or error
     */
    'pushAlarmSettings': function (deviceId, settingsDataJSON) {
        if (typeof settingsDataJSON == "string") {
            settingsDataJSON = JSON.parse(settingsDataJSON);
        }
        console.log("Received alarm state data for device: " + deviceId);
        check(deviceId, String);
        if (settingsDataJSON._id) {

            check(settingsDataJSON, {
                _id: String,
                power_switch: Number,
                trigger_switch: Number,
                duration: Number
            });
        } else {

            check(settingsDataJSON, {
                power_switch: Number,
                trigger_switch: Number,
                duration: Number
            });
        }
        if (Meteor.userId()) {
            var alarmSettings = Alarm_Setting.findOne({device_id: deviceId});
            if (!alarmSettings) {
                console.log("No alarm settings found, creating settings");
                alarmSettings = Meteor.modelFactories.createAlarmSetting(deviceId, 0);
            }
            Alarm_Setting.update(alarmSettings._id, {
                $set: {
                    power_switch: settingsDataJSON.power_switch,
                    trigger_switch: settingsDataJSON.trigger_switch,
                    duration: settingsDataJSON.duration
                }
            }, {}, function (err, res) {
                if (err) {
                    console.log("Error updating alarm settings for device: " + deviceId);
                } else {
                    console.log("Alarm Settings update for device: " + deviceId);
                }
            });
        } else {
            console.log("User is not logged in");
            return "error";
        }
    },

    /**
     * Sends data triggers to the server for a given device
     *
     * User must be logged in
     *
     * @param deviceId, id of the device sending the data
     * @param triggerData, trigger data for alarms
     * @return success or error
     */
    'pushDataTriggers': function (deviceId, triggerData) {
        console.log("Received data triggers for device: " + deviceId);
        if (typeof triggerData == "string") {
            triggerData = JSON.parse(triggerData);
        }
        check(deviceId, String);
        if (Meteor.userId()) {
            if (triggerData._id) {
                check(triggerData, {
                    _id: String,
                    device_feature_id: String,
                    limit: Number,
                    alarm_enabled: Number,
                    notifications_enabled: Number
                });
                console.log("Updating Data Trigger");
                Data_Trigger.update(triggerData._id, {
                    $set: {
                        device_feature_id: triggerData.device_feature_id,
                        limit: triggerData.limit,
                        alarm_enabled: triggerData.alarm_enabled,
                        notifications_enabled: triggerData.notifications_enabled
                    }
                }, {}, function (err, res) {
                    if (err) {
                        console.log("Error updating data trigger: " + triggerData._id);
                    } else {
                        console.log("Updated data trigger: " + triggerData._id);
                    }
                });
            } else {
                check(triggerData, {
                    device_feature_id: String,
                    limit: Number,
                    alarm_enabled: Number,
                    notifications_enabled: Number
                });
                console.log("Creating Data Trigger");
                Meteor.modelFactories.createDataTriggers(deviceId, triggerData.device_feature_id, triggerData.limit, triggerData.alarm_enabled, triggerData.notifications_enabled);
            }
        } else {
            console.log("User not logged in");
            return "error";
        }
    }

});
