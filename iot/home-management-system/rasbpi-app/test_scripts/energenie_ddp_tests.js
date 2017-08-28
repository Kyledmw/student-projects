var deviceId = "zHKdd3MQD2fBDtLAz";

var DDPClient = require("ddp");
var async = require("async");

var sleep = require('sleep');

var ModelFactories = require('../rpi_models/model_factories.js');
var constants = require('../rpi_models/model_constants.js');

var energenie = require('energenie');

var switchSocket = function(powerSocketData) {
    if(powerSocketData.get('power_switch') == 1) {
        energenie.switchOn(powerSocketData.get('socket_number'), function() {
            
        });
    } else {
        energenie.switchOff(powerSocketData.get('socket_number'), function() {
            
        });
    }
}

var ddpclient = new DDPClient({
    // All properties optional, defaults shown 
    host: "192.168.1.6",
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
    
    ddpclient.subscribe(
        'powerSocketsForDevice',
        [deviceId],
        function() {
            console.log("Power Socket Subscription complete");
        }
    );
    
    var observer = ddpclient.observe("power_socket");
    
    observer.added = function(id) {
	console.log("Added");
        var socketObj = ddpclient.collections.power_socket[id];
        var socketAdded = ModelFactories.createPowerSocketData(socketObj.socket_number, socketObj.power_switch);
        switchSocket(socketAdded);
    };
    
    observer.changed = function(id, oldFields, clearedFields, newFields) {
	console.log("Changed");
        var socketObj = ddpclient.collections.power_socket[id];
        var socketAdded = ModelFactories.createPowerSocketData(socketObj.socket_number, socketObj.power_switch);
        switchSocket(socketAdded);
    }
    observer.removed = function(id, oldValue) {
        var socketAdded = ModelFactories.createPowerSocketData(oldValue.socket_number, 0);
        switchSocket(socketAdded);
    }

});
