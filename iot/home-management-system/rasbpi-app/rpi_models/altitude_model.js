var Altitude = function AltitudeModel(data) {
	this.data = data;
}

Altitude.prototype.get = function(field_name) {
	return this.data[field_name];
}

Altitude.prototype.set = function(field_name, value) {
	this.data[field_name] = value;
}

module.exports = Altitude;