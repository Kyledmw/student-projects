var deviceId = "xrKeH66rx3kukghW5";
//var deviceId = "eXG9F7t2arycJfqu8";

var DDPClient = require("ddp");
var async = require("async");

var sleep = require('sleep');

var ModelFactories = require('./rpi_models/model_factories.js');

var fs = require('fs');

var camera = require('./rpi_camera/rpi_camera.js');

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

camera.connect();

var cameraSettings = {};

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

    ddpclient.subscribe('cameraSettingsForDevice',
        [deviceId],
        function () {
            console.log("Subscribed to Camera Settings");

            camera.notifyOnChange(function (imagePath, timestamp) {
                if (cameraSettings.get) {
                    if (cameraSettings.get('power_switch') == 1) {
                        fs.readFile(__dirname + imagePath, function (err, data) {
                            var buffer = new Buffer(data);
                            var base64data = buffer.toString('base64');
                            var cameraData = ModelFactories.createCameraData(timestamp, base64data);
                            setTimeout(function () {
                                ddpclient.call('pushCameraData', [deviceId, cameraData], function (err, result) {
                                    console.log("Called function result:");
                                }, function () {
                                    console.log("updated")
                                }, 3000);
                            });
                        });
                    }
                }
            });
        });

    var cameraSettingObserver = ddpclient.observe("camera_setting");

    cameraSettingObserver.added = function (id) {
        var camObj = ddpclient.collections.camera_setting[id]
        cameraSettings = ModelFactories.createCameraSettingsData(camObj.power_switch);
    };

    cameraSettingObserver.changed = function (id, oldFields, clearedFields, newFields) {
        var camObj = ddpclient.collections.camera_setting[id]
        cameraSettings = ModelFactories.createCameraSettingsData(camObj.power_switch);
    }


});
