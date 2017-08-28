Meteor.methods({
    
    'pushPowerSocket': function(deviceId, powerSocketJSON) {
        if(typeof powerSocketJSON == "string") {
            powerSocketJSON = JSON.parse(powerSocketJSON);
        }
        console.log("Power Socket data received for device: " + deviceId);
        check(deviceId, String);

        if(powerSocketJSON._id) {
            check(powerSocketJSON, {
                _id: String,
                socket_name: String,
                socket_number: Number,
                power_switch: Number
            });
        } else {
            check(powerSocketJSON, {
                socket_name: String,
                socket_number: Number,
                power_switch: Number
            });
        }
        if(Meteor.userId()) {
            var powerSocket = Power_Socket.findOne({device_id: deviceId, _id: powerSocketJSON._id});
            if(!powerSocket) {
                console.log("No power socket found, creating one");
                Meteor.modelFactories.createPowerSocket(deviceId, powerSocketJSON.socket_name, powerSocketJSON.socket_number, powerSocketJSON.power_switch);
                console.log("Power Socket created");
            } else {
                Power_Socket.update(powerSocket._id, { $set: {socket_name: powerSocketJSON.socket_name, power_switch:powerSocketJSON.power_switch}}, {}, function(err, res) {
                    if(err) {
                        console.log("Error Updating Power Socket");
                    } else {
                        console.log("Successfully updated the power socket");
                    }
                });
            }
        } else {
            console.log("User not logged in");
            return "error";
        }
    },
    
    'pushDeletePowerSocket': function(deviceId, powerSocketId) {
        console.log("Received a power socket delete request for: " + deviceId + " for socket: " + powerSocketId);
        check(deviceId, String);
        check(powerSocketId, String);
        if(Meteor.userId()) {
            Power_Socket.remove(powerSocketId);
            console.log("Socket deleted");
        } else {
            console.log("User not logged in");
            return "error";
        }
    }
});