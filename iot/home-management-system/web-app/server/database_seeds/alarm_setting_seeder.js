Meteor.mongoSeeders.alarm_settings = function() {

	if(Alarm_Setting.find().count() === 0) {
        var device1 = Device.findOne({'key' : '1111-1111-1111-1111'});

		var alarmSettingData = [
			{device_id: device1._id, power_switch: 1, trigger_switch: 0, duration: 5}
		];

		_.each(alarmSettingData, function(data) {
			Meteor.modelFactories.createAlarmSetting(data.device_id, data.power_switch, data.trigger_switch, data.duration);
		});
	}

};