var DataTriggersSettings = function DataTriggersSettingsModel(data) {
	this.data = data;
}

DataTriggersSettings.prototype.get = function(field_name) {
	return this.data[field_name];
}

DataTriggersSettings.prototype.set = function(field_name, value) {
	this.data[field_name] = value;
}

module.exports = DataTriggersSettings;