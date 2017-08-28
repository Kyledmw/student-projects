var deviceId = "zHKdd3MQD2fBDtLAz";

var DDPClient = require("ddp");
var async = require("async");

var sleep = require('sleep');

var ModelFactories = require('../rpi_models/model_factories.js');
var constants = require('../rpi_models/model_constants.js');

var fs = require('fs');

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

    fs.readFile(__dirname + "/test_image.jpg", function (err, data) {  
        var buffer = new Buffer(data);
        var base64data = buffer.toString('base64');
        var cameraData = ModelFactories.createCameraData(Date.now(), base64data);
        setTimeout(function () {
            ddpclient.call('pushCameraData', [deviceId, cameraData], function (err, result) {
                console.log("Called function result:");
            }, function () {
                console.log("updated")
            });
        });
    });
});
