package com.hms.power_sockets.models;

import com.hms.power_sockets.models.interfaces.IPowerSocketReadable;
import com.hms.power_sockets.models.interfaces.IPowerSocketWriteable;

/**
 * Created by Kyle on 01/03/2016.
 */
public class PowerSocketModel implements IPowerSocketWriteable, IPowerSocketReadable {

    private String _id;
    private String _name;
    private int _number;
    private boolean _powerSwitch;

    public PowerSocketModel(String id, String name, int number, boolean powerSwitch){

        _id = id;
        _name = name;
        _number = number;
        _powerSwitch = powerSwitch;

    }

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public boolean getPowerSwitch() {
        return _powerSwitch;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public int getNumber() {
        return _number;
    }

    @Override
    public void setName(String name) {
        _name = name;
    }

    @Override
    public void setNumber(int number) {
        _number = number;
    }

    @Override
    public void flipPowerSwitch() {
        _powerSwitch = !_powerSwitch;
    }

    @Override
    public void setSwitchState(boolean isChecked) {
        _powerSwitch = isChecked;
    }
}
