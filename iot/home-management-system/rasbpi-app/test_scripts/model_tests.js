var factories = require('../rpi_models/model_factories.js');
var constants = require('../rpi_models/model_constants.js');

var temperature = factories.createTemperatureData(new Date().getTime(), 28, constants.TEMPERATURE_TYPE);

console.log(temperature.get('timestamp'));