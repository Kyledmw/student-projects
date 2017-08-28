function RPICamera() {
		
	var IMAGE_STREAM_PATH = '/stream/image_stream.jpg';
	
  	var args = ["-w", "640", "-h", "480", "-o", "." + IMAGE_STREAM_PATH, "-t", "999999999", "-tl", "50"];
	
	var fs = require('fs');
	var spawn = require('child_process').spawn;
	
	var proc = null;
	
	
	function connect() {
		if(!proc) {
			proc = spawn('raspistill', args);
		}
	}
	
	function notifyOnChange(callback) {
		fs.watchFile("." + IMAGE_STREAM_PATH, function(current, previous) {
			callback(IMAGE_STREAM_PATH, Date.now());
		});
	}
	
	function disconnect() {
		if(proc) {
			proc.kill();
			fs.unwatchFile("." + IMAGE_STREAM_PATH);
		}
	}
	
	this.IMAGE_STREAM_PATH = IMAGE_STREAM_PATH;
	this.connect = connect;
	this.notifyOnChange = notifyOnChange;
	this.disconnect = disconnect;
}

module.exports = new RPICamera();