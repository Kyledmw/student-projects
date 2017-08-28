package com.hms.common.controllers;

import com.hms.common.controllers.interfaces.IExternalController;
import com.hms.alarm_settings.handlers.AlarmSettingsHandler;
import com.hms.alarm_settings.handlers.IAlarmSettingsHandler;
import com.hms.camera.handlers.CameraDataHandler;
import com.hms.camera.handlers.CameraSettingsHandler;
import com.hms.camera.handlers.interfaces.ICameraDataHandler;
import com.hms.camera.handlers.interfaces.ICameraSettingsHandler;
import com.hms.devices.handlers.DeviceDashboardHandler;
import com.hms.devices.handlers.DeviceHandler;
import com.hms.devices.handlers.interfaces.IDeviceDashboardHandler;
import com.hms.devices.handlers.interfaces.IDeviceHandler;
import com.hms.power_sockets.handlers.interfaces.IPowerSocketHandler;
import com.hms.power_sockets.handlers.PowerSocketHandler;
import com.hms.sensors.altitude.handlers.interfaces.IAltitudeHandler;
import com.hms.sensors.atmospheric_pressure.handlers.interfaces.IAtmosphericPressureHandler;
import com.hms.devices.handlers.interfaces.IDeviceMovedHandler;
import com.hms.sensors.motion.handlers.interfaces.IMotionDetectionHandler;
import com.hms.sensors.settings.handlers.interfaces.ISensorSettingsHandler;
import com.hms.sensors.temperature.handlers.interfaces.ITemperatureHandler;
import com.hms.sensors.altitude.handlers.AltitudeHandler;
import com.hms.sensors.atmospheric_pressure.handlers.AtmosphericPressureHandler;
import com.hms.devices.handlers.DeviceMovedHandler;
import com.hms.sensors.motion.handlers.MotionDetectedHandler;
import com.hms.sensors.settings.handlers.SensorSettingsHandler;
import com.hms.sensors.temperature.handlers.TemperatureHandler;
import com.hms.account.handlers.AccountHandler;
import com.hms.account.handlers.interfaces.IAccountHandler;

/**
 * Created by alan on 23/02/16.
 */
public class ExternalController implements IExternalController {

    private IAccountHandler _accountHandler;
    private IDeviceHandler _deviceHandler;

    private IMotionDetectionHandler _motionDetectedHandler;
    private IDeviceMovedHandler _deviceMovedHandler;
    private IAltitudeHandler _altitudeHandler;
    private IAtmosphericPressureHandler _atmosphericPressureHandler;
    private ITemperatureHandler _temperatureHandler;
    private IPowerSocketHandler  _powerSocketHandler;
    private ICameraDataHandler _cameraDataHandler;
    private IAlarmSettingsHandler _alarmSettingsHandler;
    private ISensorSettingsHandler _sensorSettingsHandler;
    private ICameraSettingsHandler _cameraSettingsHandler;
    private IDeviceDashboardHandler _devFeatureHandler;


    @Override
    public IAccountHandler getAccountHandler() {
        if(_accountHandler == null) {
            _accountHandler = new AccountHandler();
        }
        return _accountHandler;
    }

    @Override
    public IDeviceHandler getDeviceHandler() {
        if(_deviceHandler == null) {
            _deviceHandler = new DeviceHandler();
        }
        return _deviceHandler;
    }

    @Override
    public IMotionDetectionHandler getMotionDetectionHandler() {
        if(_motionDetectedHandler == null){
            _motionDetectedHandler = new MotionDetectedHandler();
        }
        return _motionDetectedHandler;
    }

    @Override
    public ITemperatureHandler getTemperatureHandler() {
        if(_temperatureHandler == null) {
            _temperatureHandler = new TemperatureHandler();
        }
        return _temperatureHandler;
    }

    @Override
    public IAtmosphericPressureHandler getAtmosphericPressureHandler() {
        if(_atmosphericPressureHandler == null) {
            _atmosphericPressureHandler = new AtmosphericPressureHandler();
        }
        return _atmosphericPressureHandler;
    }

    @Override
    public IAltitudeHandler getAltitudeHandler() {
        if(_altitudeHandler == null) {
            _altitudeHandler = new AltitudeHandler();
        }
        return _altitudeHandler;
    }

    @Override
    public IPowerSocketHandler getPowerSocketHandler() {
        if(_powerSocketHandler == null) {
            _powerSocketHandler = new PowerSocketHandler();
        }
        return _powerSocketHandler;
    }

    @Override
    public ICameraDataHandler getCameraDataHandler() {
        if(_cameraDataHandler == null) {
            _cameraDataHandler = new CameraDataHandler();
        }
        return _cameraDataHandler;
    }

    @Override
    public IDeviceMovedHandler getDeviceMovedHandler() {
        if(_deviceMovedHandler == null) {
            _deviceMovedHandler = new DeviceMovedHandler();
        }
        return _deviceMovedHandler;
    }

    @Override
    public ISensorSettingsHandler getSensorSettingsHandler() {
        if(_sensorSettingsHandler == null) {
            _sensorSettingsHandler = new SensorSettingsHandler();
        }
        return _sensorSettingsHandler;
    }


    @Override
    public ICameraSettingsHandler getCameraSettingsHandler() {
        if(_cameraSettingsHandler == null) {
            _cameraSettingsHandler = new CameraSettingsHandler();
        }
        return _cameraSettingsHandler;
    }


    @Override
    public IAlarmSettingsHandler getAlarmSettingsHandler() {
        if(_alarmSettingsHandler == null) {
            _alarmSettingsHandler = new AlarmSettingsHandler();
        }
        return _alarmSettingsHandler;
    }

    @Override
    public IDeviceDashboardHandler getDeviceFeatures() {
        if(_devFeatureHandler == null) {
            _devFeatureHandler = new DeviceDashboardHandler();
        }

        return _devFeatureHandler;
    }
}
