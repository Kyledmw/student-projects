Router.configure({
    //We use the  appBody template to define the layout for the entire app
    layoutTemplate: 'appBody',

    //The appNotFound template is used for unknown routes and missing lists
    notFoundTemplate: 'appNotFound',

    //Show the appLoading template whilst the subscriptions below load their data
    loadingTemplate: 'appLoading',

    //Wait on required subscriptions before serving up any page
    waitOn: function () {
        return [
            Meteor.subscribe('accounts'),
            Meteor.subscribe('account_levels'),
            Meteor.subscribe('monitoring_levels'),
            Meteor.subscribe('measurement_types')
        ];
    }

});

//Define account routes
Router.route('join');
Router.route('signin');


Router.route('home', {
    path: '/',
    action: function () {
        this.render();
    }
});

//Route for the dashboard for homeowner accounts
Router.route('accountDashboard', {
    path: '/dashboard',
    onBeforeAction: function () {
        this.devicesHandle = Meteor.subscribe('allDevices');
    },
    action: function () {
        if (Meteor.accountHelpers.isAdmin()) {
            this.redirect('adminDashboard');
        } else {
            this.render();
        }
    }
});

Router.route('about', {
    path: '/about'
});

Router.route('download', {
    path: '/download'
});

//Route for account dashboards who are using the full monitoring service that admins see
Router.route('adminDashboard', {
    path: '/admin/dashboard',
    onBeforeAction: function () {
        this.devicesHandle = Meteor.subscribe('allDevices');
        this.tempHandle = Meteor.subscribe('allTemperature');
        this.altHandle = Meteor.subscribe('allAltitude');
        this.atmoHandle = Meteor.subscribe('allAtmoPressure');
        this.devMovedHandle = Meteor.subscribe('allDeviceMovement');
        this.cameraDataHandle = Meteor.subscribe('allCameraData');
        this.powerSwitchHandle = Meteor.subscribe('allPowerSockets');
        this.sensorSettingsHandle = Meteor.subscribe('allSensorSettings');
        this.alarmSettingsHandle = Meteor.subscribe('allAlarmSettings');
        this.cameraSettingsHandle = Meteor.subscribe('allCameraSettings');
        this.deviceFeatureHandle = Meteor.subscribe('allDeviceFeatures');
        this.deviceFeatureImplHandle = Meteor.subscribe('allDeviceFeatureImpl');
        this.dataTriggerHandle = Meteor.subscribe('allDataTriggers');
        this.motionDetectionHandle = Meteor.subscribe('allMotionDetection');
        this.recordingDataHandle = Meteor.subscribe('allRecordingData');
        Session.set('current_device', '');
        Session.set('current_account', '');
    },
    action: function () {
        if (Meteor.accountHelpers.isHomeOwner()) {
            this.redirect('accountDashboard');
        } else {
            this.render();
        }
    }
});

Router.route('toDashboard', {
    action: function () {
        if (Meteor.accountHelpers.isAdmin()) {
            this.redirect('adminDashboard');
        } else if (Meteor.accountHelpers.isHomeOwner()) {
            this.redirect('accountDashboard');
        }
    }
});