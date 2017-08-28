Meteor.mongoSeeders.power_sockets = function() {

	if(Power_Socket.find().count() === 0) {
        var device1 = Device.findOne({'key' : '2111-1111-1111-1111'});
        
        var socketData = [
            {device_id: device1._id, socket_name: "Socket 1", socket_number: 1, power_switch: 0},
            {device_id: device1._id, socket_name: "Socket 2", socket_number: 2, power_switch: 0},
            {device_id: device1._id, socket_name: "Socket 3", socket_number: 3, power_switch: 0},
            {device_id: device1._id, socket_name: "Socket 4", socket_number: 4, power_switch: 0}
        ];
        
        _.each(socketData, function(data) {
            Meteor.modelFactories.createPowerSocket(data.device_id, data.socket_name, data.socket_number, data.power_switch);
        });
	}

};