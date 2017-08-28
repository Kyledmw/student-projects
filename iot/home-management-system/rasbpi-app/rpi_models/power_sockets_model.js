var PowerSocket = function PowerSocketModel(data) {
	this.data = data;
}

PowerSocket.prototype.get = function(field_name) {
	return this.data[field_name];
}

PowerSocket.prototype.set = function(field_name, value) {
	this.data[field_name] = value;
}

module.exports = PowerSocket;