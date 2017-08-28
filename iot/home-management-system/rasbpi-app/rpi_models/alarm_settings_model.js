var AlarmSettings = function AlarmSettingsModel(data) {
	this.data = data;
}

AlarmSettings.prototype.get = function(field_name) {
	return this.data[field_name];
}

AlarmSettings.prototype.set = function(field_name, value) {
	this.data[field_name] = value;
}

module.exports = AlarmSettings;