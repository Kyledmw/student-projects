Template.realtimeAtmoPressure.helpers({

    atmoReady: function () {
        return Router.current().atmoHandle.ready();
    },
    atmoPressure: function () {
        return Atmospheric_Pressure_Data.findOne({ device_id: Session.get(CURRENT_DEVICE_KEY) }, { sort: { createdAt: -1 } });
    },
    getType: function(typeId) {
        return Meteor.viewHelpers.getType(typeId);
    }
});
