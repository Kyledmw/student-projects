var Temperature = function TemperatureModel(data) {
	this.data = data;
}

Temperature.prototype.get = function(field_name) {
	return this.data[field_name];
}

Temperature.prototype.set = function(field_name, value) {
	this.data[field_name] = value;
}

module.exports = Temperature;