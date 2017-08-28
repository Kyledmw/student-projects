Meteor.mongoSeeders.recording_data = function() {

	if(Recording_Data.find().count() === 0) {

		var recordingData = [
		];

		_.each(recordingData, function(data) {
			Meteor.modelFactories.createRecordingData(data.device_id, data.start_time, data.end_time);
		});
	}

};