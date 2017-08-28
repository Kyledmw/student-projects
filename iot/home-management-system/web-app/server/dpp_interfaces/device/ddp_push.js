/**
 ********************************************************************
 * This script contains ddp push functions for devices to be used by ddp clients
 *
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */

Meteor.methods({
    
    /**
     * Request the server to activate a device for the current user
     * 
     * Requires a user to be logged in
     * 
     * @param activationKey key to activate device
     * @return success or error
     */
    'pushActivateDevice': function(activationKey, name) {
        console.log("Received activate device requests for key: " + activationKey);
        check(activationKey, String);
        check(name, String);
        if(Meteor.userId()) {
            console.log("User is logged in, attempting to activate device");
            var device = Device.findOne({key: activationKey});
            if(device) {
                if(!device.activated) {
                    console.log("Attempting to update device");
                    Device.update(device._id, {$set: {device_name: name, account_id: Meteor.userId(), activated: 1}}, {}, function(err, res) {
                        if(err) {
                            console.log("Error updating device");
                        } else {
                            console.log("Successfully updated device");
                        }
                    });
                } else {
                    console.log("Device is already activated");
                    return "error";
                }
            } else {
                console.log("Device not found");
                return "error";
            }
        } else {
            console.log("No user loggedin, returning error");
            return "error";
        }
    },
    
    /**
     * Request the server to de-activate a device for the current user
     * 
     * Requires a user to be logged in
     * 
     * @param deviceId id of the device to deactivate
     * @return success or error
     */
    'pushDeactivateDevice': function(deviceId) {
        console.log("Received a device de-activation request for device: " + deviceId);
        check(deviceId, String);
        
        if(Meteor.userId()) {
            console.log("User is logged in, attempting to deactivate device");
            var device = Device.findOne({_id: deviceId, account_id: Meteor.userId()});
            if(device) {
                Device.update(deviceId, {$set: {activated: 0, account_id: ''}}, {}, function(err, res) {
                   if(err) {
                       console.log("Error updating device");
                   }  else {
                       console.log("Successfully updated device");
                   }
                });
            } else{
                console.log("Error cant find suitable device");
                return "error";
            }
        } else {
            console.log("No user loggedin, returning error");
            return "error";
        }
    },
    
    'deviceFeatureForId': function(deviceFeatureId) {
        console.log("Received a device feature for id request for id: " + deviceFeatureId);
        check(deviceFeatureId, String);
        if(Meteor.userId()) {
            return DeviceFeature.find(deviceFeatureId);
        }
    },

    'implementedDeviceFeaturesForDevice': function(deviceId){
        console.log("Received a device feature for device request for id: " + deviceId);
        if(Meteor.userId()) {
            var devFeatImpl = DeviceFeature_Implementation.find({device_id: deviceId}).fetch();
            var devFeatIds = [];
            _.forEach(devFeatImpl, function(data) {
                devFeatIds.push(data.device_feature_id);
            });

            var devFeature = DeviceFeature.find({_id: {"$in": devFeatIds}});
            return devFeature.fetch();
        }
    }
    
});