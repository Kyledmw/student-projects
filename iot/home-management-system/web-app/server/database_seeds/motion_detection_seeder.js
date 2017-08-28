Meteor.mongoSeeders.motion_detection = function() {

	if(Motion_Detection_Data.find().count() === 0) {

		var motionDetectionData = [
		];

		_.each(motionDetectionData, function(data) {
			Meteor.modelFactories.createMotionDetectionData(data.device_id, data.timestamp, data.camera_data_id);
		});
	}
};