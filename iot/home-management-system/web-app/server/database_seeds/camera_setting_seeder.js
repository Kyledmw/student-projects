Meteor.mongoSeeders.camera_settings = function() {

	if(Camera_Setting.find().count() === 0) {
        var device1 = Device.findOne({'key' : '1111-1111-1111-1111'});
		var device2 = Device.findOne({'key' : '2111-1111-1111-1111'});

		var cameraSettings = [
			{device_id: device1._id, power_switch: 1, recording: 0},
			{device_id: device2._id, power_switch: 1, recording: 0}
		];

		_.each(cameraSettings, function(data) {
			Meteor.modelFactories.createCameraSetting(data.device_id, data.power_switch, data.recording);
		});
	}

};