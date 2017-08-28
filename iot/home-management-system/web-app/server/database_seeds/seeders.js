Meteor.startup(function () {
    Meteor.mongoSeeders.account_level();
    Meteor.mongoSeeders.monitoring_level();
    Meteor.mongoSeeders.device_feature();
    Meteor.mongoSeeders.measurement_type();


    Meteor.mongoSeeders.account();
    Meteor.mongoSeeders.device();
    Meteor.mongoSeeders.device_feature_impl();
    Meteor.mongoSeeders.temperature();
    Meteor.mongoSeeders.atmo_pressure();
    Meteor.mongoSeeders.altitude();
    Meteor.mongoSeeders.recording_data();
    Meteor.mongoSeeders.camera_data();
    Meteor.mongoSeeders.motion_detection();
    Meteor.mongoSeeders.device_moved();
    Meteor.mongoSeeders.camera_settings();
    Meteor.mongoSeeders.sensor_settings();
    Meteor.mongoSeeders.alarm_settings();
    Meteor.mongoSeeders.data_triggers();
    Meteor.mongoSeeders.power_sockets();
});