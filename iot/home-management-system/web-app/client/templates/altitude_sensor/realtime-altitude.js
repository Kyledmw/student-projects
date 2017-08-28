Template.realtimeAltitude.helpers({

    altReady: function () {
        return Router.current().altHandle.ready();
    },
    altitude: function () {
        return Altitude_Data.findOne({ device_id: Session.get(CURRENT_DEVICE_KEY) }, { sort: { createdAt: -1 } });
    },
    getType: function(typeId) {
        return Meteor.viewHelpers.getType(typeId);
    }
});