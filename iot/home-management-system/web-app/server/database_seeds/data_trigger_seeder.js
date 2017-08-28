Meteor.mongoSeeders.data_triggers = function() {

	if(Data_Trigger.find().count() === 0) {
        var device1 = Device.findOne({'key' : '1111-1111-1111-1111'});
		var feature2 = DeviceFeature.findOne({feature_type: "Motion_Detection"});

		var dataTriggers = [
			{device_id: device1._id, device_feature_id: feature2._id, limit: 1, alarm_enabled: 1, notifications_enabled: 1},
		];

		_.each(dataTriggers, function(data) {
			Meteor.modelFactories.createDataTriggers(data.device_id, data.device_feature_id, data.limit, data.alarm_enabled, data.notifications_enabled);
		});
	}

};