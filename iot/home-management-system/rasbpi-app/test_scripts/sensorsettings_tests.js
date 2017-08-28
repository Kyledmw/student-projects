var deviceId = "zHKdd3MQD2fBDtLAz";

var DDPClient = require("ddp");
var async = require("async");

var sleep = require('sleep');

var ModelFactories = require('../rpi_models/model_factories.js');
var constants = require('../rpi_models/model_constants.js');

var ddpclient = new DDPClient({
    // All properties optional, defaults shown 
    host: "localhost",
    port: 3000,
    ssl: false,
    autoReconnect: true,
    autoReconnectTimer: 500,
    maintainCollections: true,
    ddpVersion: '1'
});

var sensorSettings;
var dataTriggers = [];

var getTriggerForType = function(ddpclient, TYPE) {
    for(var key in ddpclient.collections.data_trigger) {
        if(ddpclient.collections.data_trigger.hasOwnProperty(key)) {
            var obj = ddpclient.collections.data_trigger[key]
            var typeId = obj.measurement_type_id;
            var type = ddpclient.collections.measurement_type[typeId].type;
            if(type == TYPE) {
             return ModelFactories.createDataTriggerData(type, obj.limit, obj.alarm_enabled);
            }
        }
    }
}
 
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
         [deviceId],
         function() {
             console.log("Subscribed to Sensor Settings");
         }
    );
    
    var ss_observer = ddpclient.observe("sensor_setting");
    
    ss_observer.added = function(id) {
        var sensorObj = ddpclient.collections.sensor_setting[id];
        sensorSettings = ModelFactories.createSensorSettingsData(sensorObj.power_switch);
    };
    
    ss_observer.changed = function(id, oldFields, clearedFields, newFields) {
        var sensorObj = ddpclient.collections.sensor_setting[id];
        sensorSettings = ModelFactories.createSensorSettingsData(sensorObj.power_switch);
    };
});
