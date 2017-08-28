Template.cameraSettings.helpers({

    cameraSettingsReady: function() {
        return Router.current().cameraSettingsHandle.ready();
    },
    cameraSettings: function() {
        return Camera_Setting.findOne({device_id: Session.get(CURRENT_DEVICE_KEY)});
    },
    updateSettings: function() {
        var camFeature = DeviceFeature.findOne({feature_type: "Camera"});
        if(camFeature) {

            var featureImpl = DeviceFeature_Implementation.findOne({device_id: Session.get(CURRENT_DEVICE_KEY), device_feature_id: camFeature._id});
            if(featureImpl) {
                var settings = Camera_Setting.findOne({device_id: Session.get(CURRENT_DEVICE_KEY)});
                var pSwitch = (settings.power_switch == 1);
                var tSwitch = (settings.recording == 1);

                $('#cameraPS').prop('checked', pSwitch);
                $('#cameraRec').prop('checked', tSwitch);
                $('#cameraPS').prop('disabled', false);
                $('#cameraRec').prop('disabled', false);
            } else {
                $('#cameraPS').prop('checked', false);
                $('#cameraRec').prop('checked', false);
                $('#cameraPS').prop('disabled', true);
                $('#cameraRec').prop('disabled', true);
            }
        }
    }
});

Template.cameraSettings.events({
    'click .js-camera-ps': function(event) {
        var a = event.currentTarget;
        var pSwitch = $(a).is(":checked") ? 1 : 0;
        Camera_Setting.update($(a).val(), {$set: {power_switch: pSwitch}});
    },
    'click .js-camera-rec': function(event) {
        var a = event.currentTarget;
        var recSwitch = $(a).is(":checked") ? 1 : 0;
        Camera_Setting.update($(a).val(), {$set: {recording: recSwitch}});
        if(recSwitch) {
            Meteor.modelFactories.createRecordingData(Session.get(CURRENT_DEVICE_KEY), Date.now(), -1);
        } else {
            var recData = Recording_Data.findOne({device_id: Session.get(CURRENT_DEVICE_KEY)}, { sort: { createdAt: -1 }});
            Recording_Data.update(recData._id, {$set: {end_time: Date.now()}});
        }
    }
});

Template.cameraSettings.rendered = function() {
    var settings = Camera_Setting.findOne({device_id: Session.get(CURRENT_DEVICE_KEY)});
    var camFeature = DeviceFeature.findOne({feature_type: "Camera"});
    if(camFeature) {
        var featureImpl = DeviceFeature_Implementation.findOne({device_id: Session.get(CURRENT_DEVICE_KEY), device_feature_id: camFeature._id});
        if(featureImpl) {
            var psState = (settings.power_switch == 1);
            var recState = (settings.recording == 1);

            $('#cameraPS').prop('checked', psState);
            $('#cameraRec').prop('checked', recState);
        } else {
            $('#cameraPS').prop('checked', false);
            $('#cameraRec').prop('checked', false);
            $('#cameraPS').prop('disabled', true);
            $('#cameraRec').prop('disabled', true);
        }
    }
};