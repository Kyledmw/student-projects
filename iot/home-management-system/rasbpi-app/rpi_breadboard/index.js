function Breadboard() {
    var GPIO = require('rpi-gpio');
    var async = require('async');
    var sleep = require('sleep');
    
    var BUZZER_GPIO = 15;
    var LDR_GPIO = 13;
    var INFRARED_GPIO = 11;
    
    function init(callback) {
        async.parallel([
            function(innerCallback) {
                GPIO.setup(BUZZER_GPIO, GPIO.DIR_OUT, innerCallback);
            },
            function(innerCallback) {
                GPIO.setup(INFRARED_GPIO, GPIO.DIR_IN, innerCallback);
            }
        ],
        function(err, res) {
            if(callback) {
                callback(err, res);
            }
        });
    }
    
    function turnOnBuzzer(callback) {
        GPIO.output(BUZZER_GPIO, true, callback);
    }
    
    function turnOffBuzzer(callback) {
        GPIO.output(BUZZER_GPIO, false, callback);
    }
    
    function readLDR(callback) {
        var LDRCount = 0;
        var check = false;
        GPIO.setup(LDR_GPIO, GPIO.DIR_OUT, function() {
            GPIO.output(LDR_GPIO, false, function() {
                sleep.usleep(100000); //Drains all charge from the capacitor
                GPIO.setup(LDR_GPIO, GPIO.DIR_IN, function() {
                    GPIO.input(LDR_GPIO, function(err, value) {
                        check = value;
                        async.whilst(function() {
                            return (check == false);
                        }, function(innerCallback) {
                            LDRCount += 1;
                            GPIO.input(LDR_GPIO, function(err, value) {
                                check = (value == false);
                                innerCallback();
                            });
                        }, function(err, n) {
                            callback(LDRCount);
                        });
                    });
                });
            });
        });
    }
    
    function watchInfrared(callback) {
        var currentState = false;
        var previousState = false;
        console.log("Waiting for PIR to settle ....");
        var check = false;
        GPIO.input(INFRARED_GPIO, function(err, value) {
            check = value;
            async.whilst(function() {
                return check;
            }, function(innerCallback) {
                GPIO.input(INFRARED_GPIO, function(err, value) {
                   check = value; 
                   innerCallback();
                });
            }, function(err, n) {
                console.log("Ready");
                async.whilst(function() {
                    return true;
                }, function(innerCallback) {
                    GPIO.input(INFRARED_GPIO, function(err, value) {
                        currentState = value;
                        if(currentState && !previousState) { 
                            callback(true);
                            previousState = true;
                        } else if(!currentState && previousState) {
                            console.log("Ready");
                            callback(false);
                            previousState = false;
                        }
                        sleep.usleep(100000);
                        innerCallback();
                    });
                }, function(err, n) {
                    if(err) {
                        console.log(err);
                    }
                });
            });
        });
    }
    
    this.init = init;
    this.turnOnBuzzer = turnOnBuzzer;
    this.turnOffBuzzer = turnOffBuzzer;
    this.watchInfrared = watchInfrared;
    this.readLDR = readLDR;
}
module.exports = new Breadboard();