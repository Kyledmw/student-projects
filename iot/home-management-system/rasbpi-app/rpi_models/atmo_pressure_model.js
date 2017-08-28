var AtmospherePressure = function AtmosphereModel(data) {
	this.data = data;
}

AtmospherePressure.prototype.get = function(field_name) {
	return this.data[field_name];
}

AtmospherePressure.prototype.set = function(field_name, value) {
	this.data[field_name] = value;
}

module.exports = AtmospherePressure;