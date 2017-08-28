Template.dataTriggers.helpers({

    dataTriggers: function() {
        return Data_Trigger.find({device_id: Session.get(CURRENT_DEVICE_KEY)});
    },
    dataTriggersReady: function() {
        return Router.current().dataTriggerHandle.ready();
    },
    deviceFeatures: function() {
        return DeviceFeature.find();
    }
});

Template.dataTriggers.events({
    'click .js-dt-alarm': function(event) {
        var a = event.currentTarget;
        var alarmSwitch = $(a).is(":checked") ? 1 : 0;
        Data_Trigger.update($(a).val(), {$set: {alarm_enabled : alarmSwitch}});
    },
    'click .js-dt-notif': function(event) {
        var a = event.currentTarget;
        var notifSwitch = $(a).is(":checked") ? 1 : 0;
        Data_Trigger.update($(a).val(), {$set: {notifications_enabled : notifSwitch}});
    },
    'submit .js-dt-limit': function(event) {
        event.preventDefault();
        var a = event.target;
        var limit = a.limit.value;
        var id = a.id.value;
        Data_Trigger.update(id, {$set: {limit: limit}});
    },
    'click .js-remove-dt': function(event) {
        var a = event.currentTarget;
        Data_Trigger.remove($(a).val());
    },
    'submit .js-new-dt': function(event) {
        event.preventDefault();
        var a = event.target;
        var limit = a.limit.value;
        var alarmSwitch = $(a.alarm).is(":checked") ? 1 : 0;
        var notifSwitch = $(a.notif).is(":checked") ? 1 : 0;
        var deviceFeatId = $(a.deviceFeat).find(":selected").val();

        if(limit == "") {
            limit = 0;
        }
        Meteor.modelFactories.createDataTriggers(Session.get(CURRENT_DEVICE_KEY), deviceFeatId, limit, alarmSwitch, notifSwitch)
    }
});


Template.dataTrigger.rendered = function() {
    var dataTriggers = Data_Trigger.find({device_id: Session.get(CURRENT_DEVICE_KEY)});
    dataTriggers.forEach(function(element) {
        var alarmState = (element.alarm_enabled == 1);
        var notifState = (element.notifications_enabled == 1);
        $('#' + element._id + "DTAlarm").prop('checked', alarmState);
        $('#' + element._id + "DTNotification ").prop('checked', notifState);
    });
};

Template.dataTrigger.helpers({
    getDeviceFeature: function(typeId) {
        if (typeId) {
            var type = DeviceFeature.findOne({ _id: typeId });
            return type.feature_type;
        }
    }
});