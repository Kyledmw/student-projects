Meteor.mongoSeeders.measurement_type = function() {

	if(Measurement_Type.find().count() === 0) {

		var typeData = [
			{type: 'Celsius'},
			{type: 'Meter'},
			{type: 'Pascal'},
			{type: 'Milligals'}
		];

		_.each(typeData, function(data) {
			Meteor.modelFactories.createMeasurementType(data.type);
		});
	}

};