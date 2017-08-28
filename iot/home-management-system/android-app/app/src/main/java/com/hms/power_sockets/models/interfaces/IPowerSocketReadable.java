package com.hms.power_sockets.models.interfaces;

/**
 * Created by Kyle on 01/03/2016.
 */
public interface IPowerSocketReadable {

    String getId();

    boolean getPowerSwitch();

    String getName();

    int getNumber();

    void flipPowerSwitch();

    void setSwitchState(boolean isChecked);
}
