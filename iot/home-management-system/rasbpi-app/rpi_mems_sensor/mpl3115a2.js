/**
 ********************************************************************
 * This contains function mapping with sensor.so for working with the
 * MPL3115A2, I2C Precision Altimeter
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
function mpl3115a2() {
	
	var ffi = require('ffi');
	
    //Mapping C functions
	var sensor = ffi.Library(__dirname + '/sensor.so', {
		'bcm2835_init' : ['int', []],
		'MPL3115A2_WRITE_REGISTER' : ['void', ['uint8', 'uint8']],
		'MPL3115A2_READ_REGISTER' : ['uint8', ['uint8']],
		'MPL3115A2_Active' : ['void', []],
		'MPL3115A2_Standby' : ['void', []],
		'MPL3115A2_Init_Alt' : ['void', []],
		'MPL3115A2_Init_Bar' : ['void', []],
		'MPL3115A2_Read_Alt' : ['int', []],
		'MPL3115A2_Read_Temp' : ['int', []],
		'MPL3115A2_SetOSR' : ['void', ['uint8']],
		'MPL3115A2_SetStepTime' : ['void', ['uint8']]
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
     * Write to the given register
     * 
     * @param register unsigned int16 whose register will be written to
     * @param val unsigned int16 value
     * 
     * @return int error code
     */
	function writeRegister(register, value) {
		sensor.MPL3115A2_WRITE_REGISTER(register, value);
	}
    
    /**
     * Read the given register
     * 
     * @param register unsigned int16 whose register will be read
     * 
     * @return unsigned int16 value of the given register
     */
	function readRegister(register) {
		sensor.MPL3115A2_READ_REGISTER(register);
	}
	
    /**
     * Activate the sensor
     */
	function active() {
		sensor.MPL3115A2_Active();
	}
	
    /**
     * Put the sensor on standby
     */
	function standby() {
		sensor.MPL3115A2_Standby();
	}
	
    /**
     * Inititialise the altometer
     */
	function initAlt() {
		sensor.MPL3115A2_Init_Alt();
	}
	
    /**
     * Initialise the barometer
     */
	function initBar() {
		sensor.MPL3115A2_Init_Bar();
	}
    
    /**
     * Read the raw altimeter value
     * 
     * @return int altimeter value
     */	
	function readAlt() {
		return sensor.MPL3115A2_Read_Alt();
	}
	
    /**
     * Read the raw temperature
     * 
     * @return int temperature value 
     */	
	function readTemp() {
		return sensor.MPL3115A2_Read_Temp();
	}
	
	function setOSR(osr) {
		sensor.MPL3115A2_SetOSR(osr);
	}
	
	function setStepTime(step) {
		sensor.MPL3115A2_SetStepTime(step);
	}
	
    /**
     * Get the temperature value 
     * 
     * @return double temperature value in celsius
     */
	function getTemp() {
		var temp = readTemp();
		var t_m = (temp >> 8) & 0xff;
		var t_l = temp & 0xff;
		
		if(t_l > 99) {
			t_l = t_l / 1000.00;
		} else {
			t_l = t_l / 100.00;
		}
		return (t_m + t_l);
	}
    
	/**
     * Get the altimeter value 
     * 
     * @return double altometer value in meters
     */
	function getAlt() {
		var alt = readAlt();
		var alt_m = alt >> 8;
		var alt_l = alt & 0xff;
		
		if(alt_l > 99) {
			alt_l = alt_l / 1000.00;
		} else {
			alt_l = alt_l / 100.00;
		}
		return twosToInt(alt_m, 16) + alt_l;
	}
    
    /**
     * Get the varometer value 
     * 
     * @return double barometer value in pascals
     */
	function getBar() {
		var alt = readAlt();
		var alt_m = alt >> 6;
		var alt_l = alt & 0x03;
		
		return twosToInt(alt_m, 18);
	}
	
	function twosToInt(val, len) {
		if(val & (1 << len - 1)) {
			val = val - (1<<len)
		}
		return val
	}
	
	this.construct = construct;
	this.writeRegister = writeRegister;
	this.readRegister = readRegister;
	this.active = active;
	this.standby = standby;
	this.initAlt = initAlt;
	this.initBar = initBar;
	this.readAlt = readAlt;
	this.readTemp = readTemp;
	this.setOSR = setOSR;
	this.setStepTime = setStepTime;
	this.getTemp = getTemp;
	this.getAlt = getAlt;
	this.getBar = getBar;
}

module.exports = new mpl3115a2;