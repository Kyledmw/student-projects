Template.adminDashboard.helpers({
    devicesReady: function () {
        return Router.current().devicesHandle.ready();
    },
    devMovedReady: function () {
        return Router.current().devMovedHandle.ready();
    },
    motionDetectedReady: function() {
        return Router.current().motionDetectionHandle.ready();
    },
    devices: function () {
        return Device.find();
    },
    deviceMoved: function () {
        var device_moved = Device_Moved_Data.findOne({ device_id: Session.get(CURRENT_DEVICE_KEY) }, { sort: { createdAt: -1 } });
        if (device_moved) {
            return (device_moved.timestamp >= (Date.now() - 600000) && device_moved.timestamp <= (Date.now() + 600000));
        }
    },
    motionDetected: function() {
        var motion_detected = Motion_Detection_Data.findOne({ device_id: Session.get(CURRENT_DEVICE_KEY) }, { sort: { createdAt: -1 } });
        if(motion_detected) {
            return (motion_detected.timestamp >= (Date.now() - 600000) &&  motion_detected.timestamp <= (Date.now() + 600000));
        }
    },
    fm_users: function () {
        var f_monitoring_level = Monitoring_Level.findOne({ 'level': FULL_MONITORING });
        if(f_monitoring_level) {
            return Meteor.users.find({ 'profile.monitoring_level_id': f_monitoring_level._id }).fetch();
        }
    },
    getEmail: function (id) {
        var user = Meteor.users.find({ _id: id }).fetch()[0]
        if(user) {
            var email = user.emails[0].address;
            return email.substring(0, email.indexOf('@'));
        }
    },
    userSelected: function() {
        return (Session.get(CURRENT_ACCOUNT_KEY) != '');
    },
    deviceSelected: function() {
        return (Session.get(CURRENT_DEVICE_KEY) != '');
    },
    getActiveAccountClass: function(id) {
        if(id == Session.get(CURRENT_ACCOUNT_KEY)) {
            return 'sidebar-nav-active';
        } else {
            return '';
        }
    },
    getActiveDeviceClass: function(id) {
        if(id == Session.get(CURRENT_DEVICE_KEY)) {
            return 'btn device-btn-active';
        } else {
            return 'btn-info device-btn';
        }
    },
    devicesForAccount: function() {
        return devices = Device.find({account_id: Session.get(CURRENT_ACCOUNT_KEY)});
    }
});


Template.adminDashboard.events({
    'click .js-select-account': function(event) {
        var a = event.currentTarget;
        var account = Meteor.users.findOne({ _id: $(a).attr('value') });
        Session.set(CURRENT_ACCOUNT_KEY, account._id);
        Session.set(CURRENT_DEVICE_KEY, '');
    },
    'click .js-select-device': function(event) {
        var a = event.currentTarget;
        var device = Device.findOne({ _id: $(a).val() });
        Session.set(CURRENT_DEVICE_KEY, device._id);
    }
});