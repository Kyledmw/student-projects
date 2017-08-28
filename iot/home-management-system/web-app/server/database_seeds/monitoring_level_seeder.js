Meteor.mongoSeeders.monitoring_level = function() {

	if(Monitoring_Level.find().count() === 0) {

		var monitoringLevelsData = [
			{level: FULL_MONITORING},
			{level: 'self_monitoring'},
            {level: 'monitorer'}

		];

		_.each(monitoringLevelsData, function(data) {
			Meteor.modelFactories.createMonitoringLevel(data.level);
		});	

	};
};