function RPIMemsSensor() {
	var mag3110 = require('rpi_mems_sensor/mag3110.js');
	var mma8491q = require('rpi_mems_sensor/mma8491q.js');
	var mpl3115a2 = require('rpi_mems_sensor/mpl3115a2.js');
	
	mag3110.construct();
	mma8491q.construct();
	mpl3115a2.construct();
	
	this.mag3110 = mag3110;
	this.mma8491q = mma8491q;
	this.mpl3115a2 = mpl3115a2;
}

module.exports = new RPIMemsSensor();