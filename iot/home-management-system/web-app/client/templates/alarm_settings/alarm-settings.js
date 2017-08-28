

Template.alarmSettings.helpers({

    alarmSettingsReady: function() {
        return Router.current().alarmSettingsHandle.ready();
    },
    alarmSettings: function() {
        return Alarm_Setting.findOne({device_id: Session.get(CURRENT_DEVICE_KEY)});
    },
    updateSettings: function() {
        var alarmFeature = DeviceFeature.findOne({feature_type: "Alarm"});
        if(alarmFeature) {
            var featureImpl = DeviceFeature_Implementation.findOne({device_id: Session.get(CURRENT_DEVICE_KEY), device_feature_id: alarmFeature._id});
            if(featureImpl) {
                var settings = Alarm_Setting.findOne({device_id: Session.get(CURRENT_DEVICE_KEY)});
                var pSwitch = (settings.power_switch == 1);
                var tSwitch = (settings.trigger_switch == 1);

                $('#alarmPS').prop('checked', pSwitch);
                $('#alarmTS').prop('checked', tSwitch);
                $('#alarmPS').prop('disabled', false);
                $('#alarmTS').prop('disabled', false);
                $('#alarmDuration').prop('disabled', false);
            } else {
                $('#alarmPS').prop('checked', false);
                $('#alarmTS').prop('checked', false);
                $('#alarmPS').prop('disabled', true);
                $('#alarmTS').prop('disabled', true);
                $('#alarmDuration').prop('disabled', true);
            }
        }
    }
});

Template.alarmSettings.events({
    'click .js-alarm-ps': function(event) {
        var a = event.currentTarget;
        var pSwitch = $(a).is(":checked") ? 1 : 0;
        Alarm_Setting.update($(a).val(), {$set: {power_switch: pSwitch}});
    },
    'click .js-alarm-ts': function(event) {
        var a = event.currentTarget;
        var tSwitch = $(a).is(":checked") ? 1 : 0;
        Alarm_Setting.update($(a).val(), {$set: {trigger_switch: tSwitch}});
    },
    'keyup .js-alarm-duration': function(event) {
        var a = event.currentTarget;

        var num = a.value;
        var id = a.name;
        if(parseInt(num) >= 0) {
            Alarm_Setting.update(id, {$set: {duration: num}});
        } else {
            a.value = 0;
        }
    }
});

Template.alarmSettings.rendered = function() {
    var alarmFeature = DeviceFeature.findOne({feature_type: "Alarm"});
    if(alarmFeature) {
        var featureImpl = DeviceFeature_Implementation.findOne({device_id: Session.get(CURRENT_DEVICE_KEY), device_feature_id: alarmFeature._id});
        if(featureImpl) {
            var settings = Alarm_Setting.findOne({device_id: Session.get(CURRENT_DEVICE_KEY)});
            var pSwitch = (settings.power_switch == 1);
            var tSwitch = (settings.trigger_switch == 1);

            $('#alarmPS').prop('checked', pSwitch);
            $('#alarmTS').prop('checked', tSwitch);
        } else {
            $('#alarmPS').prop('checked', false);
            $('#alarmTS').prop('checked', false);
            $('#alarmPS').prop('disabled', true);
            $('#alarmTS').prop('disabled', true);
            $('#alarmDuration').prop('disabled', true);
        }
    }
};