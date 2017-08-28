if (Meteor.isServer) {
    Accounts.onCreateUser(function (options, user) {

        if (options.profile) {
            user.profile = options.profile;
        }
        return user;
    });
}
Meteor.modelFactories = {

    createAccount: function (email, password, accountLevelId, monitoringLevelId, callback) {
        Accounts.createUser({
            email: email,
            password: password,
            profile: {
                monitoring_level_id: monitoringLevelId,
                account_level_id: accountLevelId
            }
        }, callback);
    },

    createAccountLevel: function (level) {
        return Account_Level.insert({
            level: level,
            createdAt: new Date()
        });
    },

    createMonitoringLevel: function (level) {
        return Monitoring_Level.insert({
            level: level,
            createdAt: new Date()
        });
    },

    createDevice: function (accountId, name, key, activated) {
        return Device.insert({
            account_id: accountId,
            device_name: name,
            key: key,
            activated: activated,
            createdAt: new Date()
        });
    },

    createDeviceFeature: function (featureType) {
        return DeviceFeature.insert({
            feature_type: featureType,
            createdAt: new Date()
        });
    },

    createDeviceFeatureImplementation: function (deviceId, deviceFeatureId, monitor, control) {
        return DeviceFeature_Implementation.insert({
            device_id: deviceId,
            device_feature_id: deviceFeatureId,
            monitor: monitor,
            control: control,
            createdAt: new Date()
        });
    },

    createTemperatureData: function (deviceId, timestamp, value, typeId) {
        return Temperature_Data.insert({
            device_id: deviceId,
            timestamp: timestamp,
            value: value,
            type_id: typeId,
            createdAt: new Date()
        });
    },

    createMeasurementType: function (type) {
        return Measurement_Type.insert({
            type: type,
            createdAt: new Date()
        });
    },

    createAtmosphericPressureData: function (deviceId, timestamp, value, typeId) {
        return Atmospheric_Pressure_Data.insert({
            device_id: deviceId,
            timestamp: timestamp,
            value: value,
            type_id: typeId,
            createdAt: new Date()
        });
    },

    createAltitudeData: function (deviceId, timestamp, value, typeId) {
        return Altitude_Data.insert({
            device_id: deviceId,
            timestamp: timestamp,
            value: value,
            type_id: typeId,
            createdAt: new Date()
        });
    },

    createCameraData: function (deviceId, image, timestamp, recordId) {
        return Camera_Data.insert({
            device_id: deviceId,
            image: image,
            timestamp: timestamp,
            record_id: recordId,
            createdAt: new Date()
        });
    },

    createMotionDetectionData: function (deviceId, timestamp, cameraDataId) {
        return Motion_Detection_Data.insert({
            device_id: deviceId,
            timestamp: timestamp,
            camera_data_id: cameraDataId,
            createdAt: new Date()
        });
    },

    createDeviceMovedData: function (deviceId, timestamp) {
        return Device_Moved_Data.insert({
            device_id: deviceId,
            timestamp: timestamp,
            createdAt: new Date()
        })
    },

    createCameraSetting: function (deviceId, powerSwitch, recording) {
        return Camera_Setting.insert({
            device_id: deviceId,
            power_switch: powerSwitch,
            recording: recording,
            createdAt: new Date()
        });
    },

    createRecordingData: function (deviceId, startTime, endTime) {
        return Recording_Data.insert({
            device_id: deviceId,
            start_time: startTime,
            end_time: endTime,
            createdAt: new Date()
        });
    },

    createSensorSetting: function (deviceId, powerSwitch) {
        return Sensor_Setting.insert({
            device_id: deviceId,
            power_switch: powerSwitch,
            createdAt: new Date()
        });
    },

    createAlarmSetting: function (deviceId, powerSwitch, triggerSwitch, duration) {
        return Alarm_Setting.insert({
            device_id: deviceId,
            power_switch: powerSwitch,
            trigger_switch: triggerSwitch,
            duration: duration,
            createdAt: new Date()
        });
    },

    createDataTriggers: function (deviceId, deviceFeatureId, limit, alarmEnabled, notificationsEnabled) {
        return Data_Trigger.insert({
            device_id: deviceId,
            device_feature_id: deviceFeatureId,
            limit: limit,
            alarm_enabled: alarmEnabled,
            notifications_enabled: notificationsEnabled,
            createdAt: new Date()
        });
    },
    
    createPowerSocket: function(deviceId, name, socketNumber, powerSwitch) {
        return Power_Socket.insert({
            device_id: deviceId, 
            socket_name: name,
            socket_number: socketNumber, 
            power_switch: powerSwitch,
            createdAt: new Date()
        });
    }
};