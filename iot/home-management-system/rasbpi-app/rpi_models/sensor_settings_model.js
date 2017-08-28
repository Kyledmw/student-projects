var SensorSettings = function SensorSettingsModel(data) {
	this.data = data;
}

SensorSettings.prototype.get = function(field_name) {
	return this.data[field_name];
}

SensorSettings.prototype.set = function(field_name, value) {
	this.data[field_name] = value;
}

module.exports = SensorSettings;