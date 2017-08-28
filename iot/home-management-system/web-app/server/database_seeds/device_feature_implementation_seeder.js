Meteor.mongoSeeders.device_feature_impl = function() {

	if(DeviceFeature_Implementation.find().count() === 0) {
        
        var device1 = Device.findOne({'key' : '1111-1111-1111-1111'});
		var device2 = Device.findOne({'key' : '2111-1111-1111-1111'});
		var device3 = Device.findOne({'key' : '3111-1111-1111-1111'});


        var camera = DeviceFeature.findOne({'feature_type': 'Camera'});
        var temp = DeviceFeature.findOne({'feature_type': 'Temperature_Sensor'});
        var atmo = DeviceFeature.findOne({'feature_type': 'AtmosphericPressure_Sensor'});
        var alt = DeviceFeature.findOne({'feature_type': 'Altitude'});
        var motion = DeviceFeature.findOne({'feature_type': 'Motion_Detection'});
        var moved = DeviceFeature.findOne({'feature_type': 'Device_Moved'});
        var alarm = DeviceFeature.findOne({'feature_type': 'Alarm'});
		var ldr = DeviceFeature.findOne({'feature_type': 'Light Detection'});
		var pSockets = DeviceFeature.findOne({'feature_type': 'Power Sockets'});

		var deviceFeatureImplData = [
			{device_id: device1._id, device_feature_id: camera._id, monitor: 1, control: 1},
			{device_id: device1._id, device_feature_id: motion._id, monitor: 1, control: 1},
			{device_id: device1._id, device_feature_id: alarm._id, monitor: 1, control: 1},
			{device_id: device1._id, device_feature_id: ldr._id, monitor: 1, control: 1},

			{device_id: device2._id, device_feature_id: camera._id, monitor: 1, control: 1},
			{device_id: device2._id, device_feature_id: pSockets._id, monitor: 1, control: 1},

			{device_id: device3._id, device_feature_id: temp._id, monitor: 1, control: 0},
			{device_id: device3._id, device_feature_id: atmo._id, monitor: 1, control: 0},
			{device_id: device3._id, device_feature_id: alt._id, monitor: 1, control: 0},
			{device_id: device3._id, device_feature_id: moved._id, monitor: 1, control: 0}
		];

		_.each(deviceFeatureImplData, function(data) {
			Meteor.modelFactories.createDeviceFeatureImplementation(data.device_id, data.device_feature_id, data.monitor, data.control);
		});
	}

};
