Meteor.mongoSeeders.sensor_settings = function() {

	if(Sensor_Setting.find().count() === 0) {
		var device1 = Device.findOne({'key' : '1111-1111-1111-1111'});
        var device3 = Device.findOne({'key' : '3111-1111-1111-1111'});

		var sensorSetting = [
			{device_id: device1._id, power_switch: 1},
			{device_id: device3._id, power_switch: 1}
		];

		_.each(sensorSetting, function(data) {
			Meteor.modelFactories.createSensorSetting(data.device_id, data.power_switch);
		});
	}

};