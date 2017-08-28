Template.sensorSettings.helpers({
    sensorSettingsReady: function() {
        return Router.current().sensorSettingsHandle.ready();
    },
    sensorSettings: function() {
        var sensorSettings = Sensor_Setting.findOne({device_id: Session.get(CURRENT_DEVICE_KEY)});
        if(sensorSettings) {
            var state = (sensorSettings.power_switch == 1);
            $('#sensorSettings').prop('checked', state);
        } else {
            $('#sensorSettings').prop('checked', false);
        }
        return sensorSettings;
    },
    updateSettings: function() {
        var sensorSettings = Sensor_Setting.findOne({device_id: Session.get(CURRENT_DEVICE_KEY)});
        if(sensorSettings) {
            var state = (sensorSettings.power_switch == 1);
            $('#sensorSettings').prop('checked', state);
            $('#sensorSettings').prop('disabled', false);
        } else {
            $('#sensorSettings').prop('checked', false);
            $('#sensorSettings').prop('disabled', true);
        }
    }
});

Template.sensorSettings.events({
    'click .js-sensor-settings-p-switch': function(event) {
        var a = event.currentTarget;
        var pSwitch = $(a).is(":checked") ? 1 : 0;
        Sensor_Setting.update($(a).val(), {$set: {power_switch: pSwitch}});
    }
});

Template.sensorSettings.rendered = function() {
    var sensorSettings = Sensor_Setting.findOne({device_id: Session.get(CURRENT_DEVICE_KEY)});
    if(sensorSettings) {
        var state = (sensorSettings.power_switch == 1);
        $('#sensorSettings').prop('checked', state);
    } else {
        $('#sensorSettings').prop('checked', false);
        $('#sensorSettings').prop('disabled', true);
    }
};