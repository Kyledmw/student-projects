Meteor.publish('powerSocketsForDevice', function(deviceId) {
    console.log("Received Power Socket subscription request for device: " + deviceId);
    check(deviceId, String);
    return Power_Socket.find({device_id: deviceId});
});

Meteor.publish('allPowerSockets', function() {
    if(Meteor.accountHelpers.isAdmin(this.userId)) {
        console.log("User is admin, returning collection of power socket data");
        return Power_Socket.find();
    } else {
        console.log("User is not an admin, returning null");
    }
});