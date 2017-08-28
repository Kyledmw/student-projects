Meteor.mongoSeeders.camera_data = function() {

	if(Camera_Data.find().count() === 0) {

		var cameraData = [
		];

		_.each(cameraData, function(data) {
			Meteor.modelFactories.createCameraData(data.device_id, data.image, data.timestamp, data.record_id);
		});
	}

};