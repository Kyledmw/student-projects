var DeviceMoved = function DeviceMovedModel(data) {
	this.data = data;
}

DeviceMoved.prototype.get = function(field_name) {
	return this.data[field_name];
}

DeviceMoved.prototype.set = function(field_name, value) {
	this.data[field_name] = value;
}

module.exports = DeviceMoved;