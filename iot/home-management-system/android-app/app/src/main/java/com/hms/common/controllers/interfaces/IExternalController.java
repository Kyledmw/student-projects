package com.hms.common.controllers.interfaces;

import com.hms.alarm_settings.handlers.IAlarmSettingsHandler;
import com.hms.camera.handlers.interfaces.ICameraDataHandler;
import com.hms.camera.handlers.interfaces.ICameraSettingsHandler;
import com.hms.devices.handlers.interfaces.IDeviceDashboardHandler;
import com.hms.devices.handlers.interfaces.IDeviceHandler;

import com.hms.power_sockets.handlers.interfaces.IPowerSocketHandler;
import com.hms.sensors.altitude.handlers.interfaces.IAltitudeHandler;
import com.hms.sensors.atmospheric_pressure.handlers.interfaces.IAtmosphericPressureHandler;
import com.hms.devices.handlers.interfaces.IDeviceMovedHandler;
import com.hms.sensors.motion.handlers.interfaces.IMotionDetectionHandler;
import com.hms.sensors.settings.handlers.interfaces.ISensorSettingsHandler;
import com.hms.sensors.temperature.handlers.interfaces.ITemperatureHandler;
import com.hms.account.handlers.interfaces.IAccountHandler;



/**
 * Created by Kyle on 09/02/2016.
 */
public interface IExternalController {

    IAccountHandler getAccountHandler();

    IDeviceHandler getDeviceHandler();

    IMotionDetectionHandler getMotionDetectionHandler();

    IDeviceMovedHandler getDeviceMovedHandler();

    ITemperatureHandler getTemperatureHandler();

    IAtmosphericPressureHandler getAtmosphericPressureHandler();

    IAltitudeHandler getAltitudeHandler();

    IPowerSocketHandler getPowerSocketHandler();

    ICameraDataHandler getCameraDataHandler();

    ISensorSettingsHandler getSensorSettingsHandler();

    ICameraSettingsHandler getCameraSettingsHandler();

    IAlarmSettingsHandler getAlarmSettingsHandler();

    IDeviceDashboardHandler getDeviceFeatures();
}
