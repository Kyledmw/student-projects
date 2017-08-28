Template.realtimeTemperature.helpers({

    tempReady: function () {
        return Router.current().tempHandle.ready();
    },
    temperature: function () {
        return Temperature_Data.findOne({ device_id: Session.get(CURRENT_DEVICE_KEY) }, { sort: { createdAt: -1 } });
    },
    getType: function(typeId) {
        return Meteor.viewHelpers.getType(typeId);
    }
});