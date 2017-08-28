var ERRORS_KEY = "DEVICE_ERRORS";

Template.accountDashboard.onCreated(function () {
    Session.set(ERRORS_KEY, {});
});

Template.accountDashboard.helpers({

    devicesReady: function () {
        return Router.current().devicesHandle.ready();
    },

    devices: function () {
        return Device.find({account_id: Meteor.userId()});
    },

    userEmail: function () {
        if (Meteor.user()) {
            return Meteor.user().emails[0].address;
        }
    },

    errorMessages: function () {
        return _.values(Session.get(ERRORS_KEY));
    }

});

Template.accountDashboard.events({
    'click .js-radio-monitoring': function (event) {
        var clickedRadio = event.currentTarget;
        var monitoringLevel = Monitoring_Level.findOne({level: $(clickedRadio).val()});
        Meteor.users.update(Meteor.userId(), {$set: {'profile.monitoring_level_id': monitoringLevel._id}});
    },

    'submit .js-new-device': function (event) {
        event.preventDefault();
        var name = event.target.device_name.value;
        var key = event.target.activation_key.value;
        var device = Device.findOne({key: key});
        if(name == "") {
            Session.set(ERRORS_KEY, {no_name: "Please enter a name for the device"});
            return;
        }
        if (device) {
            if (!device.activated) {
                Device.update(device._id, {$set: {name: name, activated: 1, account_id: Meteor.userId()}});
            } else {
                Session.set(ERRORS_KEY, {activated: "Device is currently activated"});
            }
        } else {
            Session.set(ERRORS_KEY, {none: "Device not found"});
        }
    }
});

Template.device.events({
    'click .js-remove-device': function (event) {
        event.preventDefault();
        Device.update(this._id, {$set: {activated: 0, account_id: ''}});
    }
});

Template.accountDashboard.rendered = function () {
    var monitoringLevel = Monitoring_Level.findOne(Meteor.user().profile.monitoring_level_id);
    if (monitoringLevel) {
        $("#" + monitoringLevel.level).prop('checked', true);
    }
};