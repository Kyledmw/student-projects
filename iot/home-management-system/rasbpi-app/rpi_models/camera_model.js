var Camera = function CameraModel(data) {
	this.data = data;
}

Camera.prototype.get = function(field_name) {
	return this.data[field_name];
}

Camera.prototype.set = function(field_name, value) {
	this.data[field_name] = value;
}

module.exports = Camera;