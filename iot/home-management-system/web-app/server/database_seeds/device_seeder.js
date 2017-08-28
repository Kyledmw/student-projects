Meteor.mongoSeeders.device = function() {

	if(Device.find().count() === 0) {
        
        var account1 = Accounts.findUserByEmail('test1@test.com');
        
		var deviceData = [
			{account_id: account1._id, name: 'Cam & Breadboard', key: '1111-1111-1111-1111', activated: 1},
			{account_id: account1._id, name: 'Cam & Sockets', key: '2111-1111-1111-1111', activated: 1},
			{account_id: account1._id, name: 'Sensors', key: '3111-1111-1111-1111', activated: 1},

		];

		_.each(deviceData, function(data) {
			Meteor.modelFactories.createDevice(data.account_id, data.name, data.key, data.activated);
		});
	}
};
