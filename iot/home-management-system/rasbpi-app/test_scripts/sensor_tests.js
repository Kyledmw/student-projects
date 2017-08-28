var sensor_module = require('../rpi_mems_sensor/rpi_mems_sensor.js');
var sleep = require('sleep');

function mag3110Test() {
	var mag = sensor_module.mag3110;
	mag.init();
	
	console.log("x off: " + mag.x_off + " y off: " + mag.y_off + " z off: " + mag.z_off);
	
	while(1) {
		var data = mag.readAsInt();
		console.log("MAG3110: \tX: " + data.x + "\tY: " + data.y + "\tZ: " + data.z);
	}
}

function mma8491qTest() {
	var mma = sensor_module.mma8491q;
	mma.init();
	mma.enable();
	while(1) {
		var data = mma.getAccelerometer();
		console.log("MMA8491Q:\tX: " + data.x + "\tY: " + data.y + "\tZ:" + data.z);
	    mma.enable();
        sleep.usleep(500000);
	}	
}

function mpl3115a2Test() {
	var mpl = sensor_module.mpl3115a2;
	mpl.initAlt();
	mpl.active();
	while(1) {
		console.log("MPL3115: \tAlt: " + mpl.getAlt() + "\tTemp: " + mpl.getTemp());
	}
}


if(process.argv[2] == 1) {
	mag3110Test();
} else if(process.argv[2] == 2) {
	mma8491qTest();
}else if(process.argv[2] == 3) {
	mpl3115a2Test();
}