/**
 ********************************************************************
 * This contains function mapping with sensor.so for working with the
 * Xtrinsic MMA8491Q 3-Axis Multifunction Digital Accelerometer
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
function mma8491q() {
	
	var ref = require('ref');
	var Struct = require('ref-struct');
	
    //Create c structure scheme needed for the c functions
	var p = Struct({
		'x' : 'int16',
		'y': 'int16',
		'z': 'int16'
	});
	
	
	var pPtr = ref.refType(p);
	
	var ffi = require('ffi');
	var sensor = ffi.Library(__dirname + '/sensor.so', {
		'bcm2835_init' : ['int', []],
		'MMA8491Q_Init' : ['int', []],
		'MMA8491Q_Enable' : ['int', []],
		'MMA8491Q_DisEnable' : ['int', []],
		'MMA8491Q_WRITE_REGISTER' : ['int', []],
		'MMA8491Q_READ_REGISTER' : ['int', []],
		'MMA8491_Read' : ['int', [pPtr]]
	});

    /**
     * Constructs the object, should be called before anything else.
     */
	function construct() {
		if(sensor.bcm2835_init() == 0) {
			console.log("bcm2835 driver init failed");
		}
	}
    
	/**
     * Initialise the sensor for use
     * 
     * @return int error code
     */
	function init() {
		sensor.MMA8491Q_Init();
	}
	
    /**
     * Enable the sensor
     * 
     * @return int error code
     */
	function enable() {
		sensor.MMA8491Q_Enable();
	}
	
    /**
     * Disenable the sensor
     * 
     * @return int error code
     */
	function disEnable() {
		sensor.MMA8491Q_DisEnable();
	}
    
	/**
     * Write to the given register
     * 
     * @param register character whose register will be written to
     * @param val character value
     *
     * (Appears to not be implemented in sensor.so)
     */
	function writeRegister(register, value) {
		sensor.MMA8491Q_WRITE_REGISTER();
	}
    
    /**
     * Read the given register
     * 
     * @param register character whose register will be read
     *
     * (Appears to not be implemented in sensor.so)
     */
	function readRegister(register) {
		sensor.MMA8491Q_READ_REGISTER();
	}
    /**
     * Read data from the sensor
     * 
     * @param data pointer to struct 'p' defined above
     *
     * @return int error code
     */
	function read(data) {
		sensor.MMA8491_Read(data);
	}
	
    /**
     * Gets coordinates from the accelerometer
     * 
     * @return {x, y, z}
     */
	function getAccelerometer() {
		var data = new p();
        var dataRef = data.ref();
		read(dataRef);
        var coordinates = {
			x : data.x,
			y : data.y,
			z : data.z	
		}
        dataRef.deref();
		return coordinates
	}
	
    /**
     * Creates string from x, y, z values
     * 
     * @return string of coordinate data
     */
	function toString() {
		var data = getAccelerometer();
		var str = 'x: ' + data.x + ' y: ' + data.y + ' z: ' + data.z;
		return str;
	}
	
	this.construct = construct;
	this.init = init;
	this.enable = enable;
	this.disEnable = disEnable;
	this.writeRegister = writeRegister;
	this.readRegister = readRegister;
	this.read = read;
	this.getAccelerometer = getAccelerometer;
	this.toString = toString;
	this.p = p;
}

module.exports = new mma8491q;