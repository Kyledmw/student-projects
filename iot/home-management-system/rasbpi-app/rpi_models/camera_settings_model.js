var CameraSettings = function CameraSettingsModel(data) {
	this.data = data;
}

CameraSettings.prototype.get = function(field_name) {
	return this.data[field_name];
}

CameraSettings.prototype.set = function(field_name, value) {
	this.data[field_name] = value;
}

module.exports = CameraSettings;