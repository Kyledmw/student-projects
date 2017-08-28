package com.hms.power_sockets.models.interfaces;

/**
 * Created by Kyle on 01/03/2016.
 */
public interface IPowerSocketWriteable {

    void setName(String name);

    void setNumber(int number);

    void flipPowerSwitch();
}
