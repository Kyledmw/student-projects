Meteor.mongoSeeders.device_feature = function() {

	if(DeviceFeature.find().count() === 0) {

		var deviceFeatureData = [
			{feature_type: 'Camera'},
			{feature_type: 'Temperature_Sensor'},
			{feature_type: 'AtmosphericPressure_Sensor'},
			{feature_type: 'Altitude'},
			{feature_type: 'Motion_Detection'},
			{feature_type: 'Device_Moved'},
			{feature_type: 'Alarm'},
			{feature_type: 'Light Detection'},
			{feature_type: 'Power Sockets'}

		];

		_.each(deviceFeatureData, function(data) {
			Meteor.modelFactories.createDeviceFeature(data.feature_type);
		});
	}

};