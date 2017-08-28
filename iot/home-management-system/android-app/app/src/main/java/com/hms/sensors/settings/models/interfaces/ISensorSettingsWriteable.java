package com.hms.sensors.settings.models.interfaces;

/**
 * Created by Kyle on 13/02/2016.
 */
public interface ISensorSettingsWriteable {

    void flipPowerSwitch();

    void setId(String id);

    void setPowerSwitch(boolean switchState);
}
