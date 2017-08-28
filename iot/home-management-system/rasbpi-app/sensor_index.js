var deviceId = "s2enBvxuQ3DaBwLCP";

var DDPClient = require("ddp");
var async = require("async");

var sleep = require('sleep');

var ModelFactories = require('./rpi_models/model_factories.js');
var constants = require('./rpi_models/model_constants.js');

var sensor_module = require('./rpi_mems_sensor/rpi_mems_sensor.js');

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

var mpl = sensor_module.mpl3115a2;
mpl.initAlt();
mpl.active();

var mma = sensor_module.mma8491q;
mma.init();
mma.enable();

var accellVarianceCheck = {x: 50, y: 60, z: 70}
var prevAccellData = null;

var sensorSettings = {};

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

            async.whilst(function () {
                return true;
            }, function (callback) {
                if (sensorSettings.get) {
                    if (sensorSettings.get('power_switch') == 1) {
                        async.parallel([
                            function (innerCallback) {
                                console.log("Temp: " + mpl.getTemp());
                                setTimeout(function () {
                                    /*
                                     * Call a Meteor Method
                                     */
                                    ddpclient.call(
                                        'pushTemperatureData',             // name of Meteor Method being called
                                        [deviceId, ModelFactories.createTemperatureData(Date.now(), mpl.getTemp(), constants.TEMPERATURE_TYPE)],            // parameters to send to Meteor Method
                                        function (err, result) {   // callback which returns the method call results
                                            console.log('called function, result: ' + result);
                                        },
                                        function () {              // callback which fires when server has finished
                                            console.log('updated');  // sending any updated documents as a result of
                                            innerCallback();
                                        }
                                    );
                                }, 3000);
                            }, function (innerCallback) {
                                console.log("Bar: " + mpl.getBar());
                                setTimeout(function () {
                                    /*
                                     * Call a Meteor Method
                                     */
                                    ddpclient.call(
                                        'pushAtmoPressureData',             // name of Meteor Method being called
                                        [deviceId, ModelFactories.createAtmosphereData(Date.now(), mpl.getBar(), constants.ATMOSPHEREIC_TYPE)],            // parameters to send to Meteor Method
                                        function (err, result) {   // callback which returns the method call results
                                            console.log('called function, result: ' + result);
                                        },
                                        function () {              // callback which fires when server has finished
                                            console.log('updated');  // sending any updated documents as a result of
                                            innerCallback();
                                        }
                                    );
                                }, 3000);
                            }, function (innerCallback) {

                                console.log("Alt: " + mpl.getAlt());
                                setTimeout(function () {
                                    /*
                                     * Call a Meteor Method
                                     */
                                    ddpclient.call(
                                        'pushAltitudeData',             // name of Meteor Method being called
                                        [deviceId, ModelFactories.createAltitudeData(Date.now(), mpl.getAlt(), constants.ALTITUDE_TYPE)],            // parameters to send to Meteor Method
                                        function (err, result) {   // callback which returns the method call results
                                            console.log('called function, result: ' + result);
                                        },
                                        function () {              // callback which fires when server has finished
                                            console.log('updated');  // sending any updated documents as a result of
                                            innerCallback();
                                        }
                                    );
                                }, 3000);
                            }, function (innerCallback) {
                                var data = mma.getAccelerometer();
                                console.log("MMA8491Q:\tX: " + data.x + "\tY: " + data.y + "\tZ:" + data.z);
                                if (prevAccellData) {
                                    var x = {
                                        low: prevAccellData.x - accellVarianceCheck.x,
                                        high: prevAccellData.x + accellVarianceCheck.x
                                    };
                                    var y = {
                                        low: prevAccellData.y - accellVarianceCheck.y,
                                        high: prevAccellData.y + accellVarianceCheck.y
                                    };
                                    var z = {
                                        low: prevAccellData.z - accellVarianceCheck.z,
                                        high: prevAccellData.z + accellVarianceCheck.z
                                    };
                                    if (data.x > x.high || data.x < x.low || data.y > y.high || data.y < y.low || data.z > z.high || data.z < z.low) {
                                        setTimeout(function () {
                                            /*
                                             * Call a Meteor Method
                                             */
                                            ddpclient.call(
                                                'pushDeviceMoved',             // name of Meteor Method being called
                                                [deviceId, ModelFactories.createDeviceMovedData(Date.now())],            // parameters to send to Meteor Method
                                                function (err, result) {   // callback which returns the method call results
                                                    console.log('called function, result: ' + result);
                                                },
                                                function () {              // callback which fires when server has finished
                                                    console.log('updated');  // sending any updated documents as a result of
                                                    mma.enable();
                                                    prevAccellData = data;
                                                    innerCallback();
                                                }
                                            );
                                        }, 3000);
                                    } else {
                                        mma.enable();
                                        prevAccellData = data;
                                        innerCallback();
                                    }
                                } else {
                                    mma.enable();
                                    prevAccellData = data;
                                    innerCallback();
                                }
                            }
                        ], function (err, res) {
                            if (err) {
                                console.log("Error occurred: " + err);
                            }
                            sleep.sleep(1);
                            callback();
                        });
                    } else {
                        callback();
                    }
                } else {
                    callback();
                }
            }, function (err) {
                console.log("Error occurred: " + err);
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
});