var deviceId = "WN4XrTi74pGK8CsJb";
var motionFeatureId = "xrKeH66rx3kukghW5";
var MOTION_FEATURE = "MOTION";

var DDPClient = require("ddp");
var async = require("async");

var sleep = require('sleep');
var ModelFactories = require('./rpi_models/model_factories.js');

var breadboard = require('./rpi_breadboard/index.js');

var ddpclient = new DDPClient({
    // All properties optional, defaults shown 
    host: "192.168.1.1",
    port: 3000,
    ssl: false,
    autoReconnect: true,
    autoReconnectTimer: 500,
    maintainCollections: true,
    ddpVersion: '1'
});

var sensorSettings = {};
var motionTrigger = {};
var alarmSettings = {};

/*
 * Connect to the Meteor Server
 */
ddpclient.connect(function (error, wasReconnect) {
    // If autoReconnect is true, this callback will be invoked each time 
    // a server connection is re-established 
    if (error) {
        console.log('DDP connection error!');
        return;
    }

    if (wasReconnect) {
        console.log('Reestablishment of a connection.');
    }

    console.log('connected!');

    ddpclient.subscribe(
        'sensorSettingsForDevice',
        [deviceId], function () {
            console.log("Sensor Settings Subscription complete");

            breadboard.init(function (err, res) {
                async.whilst(function () {
                    return true;
                }, function (callback) {
                    if (sensorSettings.get) {
                        if (sensorSettings.get('power_switch') == 1) {
                            breadboard.watchInfrared(function (motionDetected) {
                                if (motionDetected) {
                                    if (motionTrigger.get('limit') >= 1 && alarmSettings.get('power_switch') == 1) {
                                        breadboard.turnOnBuzzer(function() {
                                            setTimeout(function () {
                                                breadboard.turnOffBuzzer();
                                            }, alarmSettings.get('duration') * 1000);
                                        });
                                    }
                                    setTimeout(function () {
                                        var deviceMovedData = ModelFactories.createDeviceMovedData(Date.now());
                                        ddpclient.call('pushMotionDetected', [deviceId, deviceMovedData], function (err, result) {
                                            console.log("Called pushMotionDetected. Result: " + result);
                                        }, function () {
                                            sleep.sleep(1);
                                            callback();
                                        }, 3000);
                                    });
                                }
                            })
                        }
                        else {
                            callback();
                        }
                    }
                    else {
                        callback();
                    }
                }, function (err) {
                    console.log("Error occurred: " + err);
                });
            });
        });

    var sensorSettingObserver = ddpclient.observe("sensor_setting");

    sensorSettingObserver.added = function (id) {
        var sensorObj = ddpclient.collections.sensor_setting[id];
        sensorSettings = ModelFactories.createSensorSettingsData(sensorObj.power_switch);
    };

    sensorSettingObserver.changed = function (id, oldFields, clearedFields, newFields) {
        var sensorObj = ddpclient.collections.sensor_setting[id];
        sensorSettings = ModelFactories.createSensorSettingsData(sensorObj.power_switch);
    };

    ddpclient.subscribe('alarmSettingsForDevice', [deviceId], function () {
        console.log("Alarm Settings Subscription");
    });

    var alarmSettingsObserver = ddpclient.observe('alarm_setting');

    alarmSettingsObserver.added = function (id) {
        var alarmObj = ddpclient.collections.alarm_setting[id];
        alarmSettings = ModelFactories.createAlarmSettingsData(alarmObj.power_switch, alarmObj.trigger_switch, alarmObj.duration)
    };

    alarmSettingsObserver.changed = function (id, oldFields, clearedFields, newFields) {
        var alarmObj = ddpclient.collections.alarm_setting[id];
        alarmSettings = ModelFactories.createAlarmSettingsData(alarmObj.power_switch, alarmObj.trigger_switch, alarmObj.duration)
    };

    ddpclient.subscribe('dataTriggersForDevice', [deviceId], function () {
        console.log("Data Trigger Subscription complete");
    });

    var dataTriggerObserver = ddpclient.observe("data_trigger");

    dataTriggerObserver.added = function (id) {
        var dTrigger = ddpclient.collections.data_trigger[id];
        if (dTrigger.device_feature_id == motionFeatureId) {
            motionTrigger = ModelFactories.createDataTriggerData(MOTION_FEATURE, dTrigger.limit, dTrigger.alarm_enabled)
        }
    };

    dataTriggerObserver.changed = function (id, oldFields, clearedFields, newFields) {
        var dTrigger = ddpclient.collections.data_trigger[id];
        if (dTrigger.device_feature_id == motionFeatureId) {
            motionTrigger = ModelFactories.createDataTriggerData(MOTION_FEATURE, dTrigger.limit, dTrigger.alarm_enabled);
            if (motionTrigger.get('trigger_switch') == 1) {
                breadboard.turnOnBuzzer();
            } else {
                breadboard.turnOffBuzzer();
            }
        }
    };
})
;