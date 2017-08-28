var Motion = function MotionModel(data) {
	this.data = data;
}

Motion.prototype.get = function(field_name) {
	return this.data[field_name];
}

Motion.prototype.set = function(field_name, value) {
	this.data[field_name] = value;
}

module.exports = Motion;