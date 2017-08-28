var camera = require('../rpi_camera/rpi_camera.js');


camera.connect();

setTimeout(function() {
   camera.disconnect(); 
}, 60000);

camera.notifyOnChange(function() {
   console.log("File Changed: " + new Date()); 
});