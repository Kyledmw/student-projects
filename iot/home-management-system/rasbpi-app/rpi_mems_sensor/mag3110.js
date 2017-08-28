/**
 ********************************************************************
 * This contains function mapping with sensor.so for working with the
 * Xtrinsic MAG3110 Three-Axis, Digital Magnetometer
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
function mag3110() {

    //Create instance variables
	var x_off;
	var y_off;
	var z_off;

	var fs = require('fs');
	var async = require('async');

	var ffi = require('ffi');
    
    //Mapping C functions
	var sensor = ffi.Library(__dirname + '/sensor.so', {
		'bcm2835_init': ['int', []],
		'MAG3110_WRITE_REGISTER': ['int', ['char', 'char']],
		'MAG3110_READ_REGISTER': ['char', ['char']],
		'MAG3110_Init': ['int', []],
		'MAG3110_ReadRawData_x': ['int16', []],
		'MAG3110_ReadRawData_y': ['int16', []],
		'MAG3110_ReadRawData_z': ['int16', []],
	});
    
    //path to the calibration data file. __dirname gets the full path to the file for us
	var CALIBRATION_DATA_FILE = __dirname + '/mag_calibration.data';
    
    /**
     * Constructs the object, should be called before anything else.
     */
	function construct() {
           
		var stats = fs.lstatSync(CALIBRATION_DATA_FILE)

        //Check if data is a file and read calib data
		if (stats.isFile()) {
			var data = fs.readFileSync(CALIBRATION_DATA_FILE, 'utf-8');
			var res = data.split(' ');
			x_off = res[0];
			y_off = res[1];
			z_off = res[2];
		} else {
            //Use recommended defaults
			x_off = -565;
			y_off = 700;
			z_off = 0;
		}

		if (sensor.bcm2835_init() == 0) {
			console.log("bcm2835 driver init failed");
		}
	}

    /**
     * Write to the given register
     * 
     * @param register character whose register will be written to
     * @param val character value
     * 
     * @return int error code
     */
	function writeRegister(register, value) {
		sensor.MAG3110_WRITE_REGISTER(register, value);
	}


    /**
     * Read the given register
     * 
     * @param register character whose register will be read
     * 
     * @return char value of the given register
     */
	function readRegister(register) {
		return sensor.MAG3110_READ_REGISTER(register);
	}
    

    /**
     * Initialise the sensor for use
     * 
     * @return int error code
     */
	function init() {
		sensor.MAG3110_Init();
	}

    /**
     * Read the x axis value
     * 
     * @return int16 value
     */
	function readRawData_x() {
		return sensor.MAG3110_ReadRawData_x();
	}

    /**
     * Read the y axis value
     * 
     * @return int16 value
     */
	function readRawData_y() {
		return sensor.MAG3110_ReadRawData_y();
	}

    /**
     * Read the z axis value
     * 
     * @return int16 value
     */
	function readRawData_z() {
		return sensor.MAG3110_ReadRawData_z();
	}

    /**
     * Read the all three axises
     * 
     * @return {x, y ,z}
     */
	function getAxes() {

		x = readRawData_x();
		y = readRawData_y();
		z = readRawData_z();

		return {
			x: x,
			y: y,
			z: z
		};
	}

    /**
     * Read the all three axises as integers
     * 
     * @return {x, y, ,z}
     */
	function readAsInt() {
		var divideValue = 40;

		x = readRawData_x() / divideValue;
		y = readRawData_y() / divideValue;
		z = readRawData_z() / divideValue;

		return {
			x: x,
			y: y,
			z: z
		};
	}

	function getHeading() {
		var axes = getAxes();
		var heading = Math.atan2((axes.y - y_off), (axes.x - x_off)) * 180 / Math.PI + 180;
		return heading;
	}

    /**
     * Calibrate sensor off given values
     * 
     * @param x array of x values to calibrate off
     * @param y array of y values to calibrate off
     * @param z array of z values to calibrate off
     */
	function calibrate(x, y, z) {
		var max_x = Math.max.apply(Math, x);
		var max_y = Math.max.apply(Math, y);
		var max_z = Math.max.apply(Math, z);

		var min_x = Math.min.apply(Math, x);
		var min_y = Math.min.apply(Math, y);
		var min_z = Math.min.apply(Math, z);

		x_off = (max_x + min_x) / 2;
		y_off = (max_y + min_y) / 2;
		z_off = 0;
	}

    /**
     * Creates string from x, y, z values
     * 
     * @return string of coordinate data
     */
	function toString() {
		var coordinates = getAxes();
		return 'x: ' + coordinates.x + ' y' + coordinates.y + ' z' + coordinates.z;
	}

	this.x_off = x_off;
	this.y_off = y_off;
	this.z_off = z_off;

	this.construct = construct;
	this.writeRegister = writeRegister;
	this.readRegister = readRegister;
	this.init = init;
	this.readRawData_x = readRawData_x;
	this.readRawData_y = readRawData_y;
	this.readRawData_z = readRawData_z;
	this.getAxes = getAxes;
	this.readAsInt = readAsInt;
	this.getHeading = getHeading;
	this.calibrate = calibrate;
	this.toString = toString;
}

module.exports = new mag3110;