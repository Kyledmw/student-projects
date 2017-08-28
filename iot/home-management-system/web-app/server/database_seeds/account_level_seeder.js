if(!Meteor.mongoSeeders) {
    Meteor.mongoSeeders = {};
}
Meteor.mongoSeeders.account_level = function() {
	if(Account_Level.find().count() === 0) {

		var accountLevels = [
			{level: ADMIN},
			{level: HOME_OWNER},
		];

		_.each(accountLevels, function(data) {
			Meteor.modelFactories.createAccountLevel(data.level);
		});
	}
}