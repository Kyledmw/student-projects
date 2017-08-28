Meteor.mongoSeeders.device_moved = function() {

	if(Device_Moved_Data.find().count() === 0) {

		var deviceMovedData = [
		];

		_.each(deviceMovedData, function(data) {
			Meteor.modelFactories.createDeviceMovedData(data.device_id, data.timestamp);
		});
	}

};