Meteor.mongoSeeders.atmo_pressure = function() {

	if(Atmospheric_Pressure_Data.find().count() === 0) {
        
        var device1 = Device.findOne({'key' : '3111-1111-1111-1111'});
        var type = Measurement_Type.findOne({'type': 'Pascal'});

		var atmoPressureData = [
			{device_id: device1._id, timestamp: Date.now(), value: 0, type_id: type._id},
		];

		_.each(atmoPressureData, function(data) {
			Meteor.modelFactories.createAtmosphericPressureData(data.device_id, data.timestamp, data.value, data.type_id);
		});
	}

};