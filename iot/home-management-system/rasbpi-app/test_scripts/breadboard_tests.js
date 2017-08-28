var breadboard = require('../rpi_breadboard/index.js');
var async = require('async');
var sleep = require('sleep');

breadboard.init(function(err ,res) {
    breadboard.turnOnBuzzer(function() {
        sleep.sleep(1);
       breadboard.turnOffBuzzer(function() {
           breadboard.watchInfrared(function(movementDetected) {
               console.log("MOVEMENT DETECTED:" + movementDetected);
           });
           async.whilst(function() {
               return true;
           }, function(innerCallback) {
               breadboard.readLDR(function(count) {
                   console.log("LDR Value: " + count);
                   innerCallback();
               });
           }, function(err, res) {
               
           });
       });
    });
});