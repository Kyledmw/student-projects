Meteor.mongoSeeders.altitude = function() {

	if(Altitude_Data.find().count() === 0) {
        
        var device1 = Device.findOne({'key' : '3111-1111-1111-1111'});
        var type = Measurement_Type.findOne({'type': 'Meter'});

		var altitudeData = [
			{device_id: device1._id, timestamp: Date.now(), value: 0, type_id: type._id}
		];

		_.each(altitudeData, function(data) {
			Meteor.modelFactories.createAltitudeData(data.device_id, data.timestamp, data.value, data.type_id);
		});
	}

};