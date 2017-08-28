package com.hms.devices.handlers;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.hms.account.models.interfaces.IAccountReadable;
import com.hms.devices.handlers.interfaces.IDeviceHandler;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.devices.model.DeviceModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.ResultListener;

/**
 * Created by alan on 01/03/16.
 */
public class DeviceHandler implements MeteorCallback, IDeviceHandler {

    private String _deviceSubscriptionId;
    private JsonParser parser = new JsonParser();
    private ArrayList<IDeviceReadable> _deviceList;
    private ICallback<List<IDeviceReadable>> callback;


    public DeviceHandler() {
        _deviceList = new ArrayList<IDeviceReadable>();
    }

    public void unRegisterAsCallback() {
        MeteorSingleton.getInstance().unsubscribe(_deviceSubscriptionId);
        MeteorSingleton.getInstance().unsetCallback(this);
    }


    @Override
    public String getDevices(IAccountReadable account, ICallback<List<IDeviceReadable>> callback) {

        this.callback = callback;
        MeteorSingleton.getInstance().setCallback(this);
        return _deviceSubscriptionId =  MeteorSingleton.getInstance().subscribe("deviceForLoggedInUser");
    }


    @Override
    public void activateDevice(String key, String name, final ICallback<Boolean> callback) {
        MeteorSingleton.getInstance().call("pushActivateDevice", new Object[]{key, name}, new ResultListener() {
            @Override
            public void onSuccess(String s) {

                if(s == null) {
                    callback.callback(null, true);
                }
                else {
                    callback.callback(null, false);
                }

            }

            @Override
            public void onError(String s, String s1, String s2) {
                callback.callback(null, false);
            }
        });
    }


    @Override
    public void deActivateDevice(IDeviceReadable device, final ICallback<Boolean> callback) {
        MeteorSingleton.getInstance().call("pushDeactivateDevice", new Object[]{device.getId()});
    }

    @Override
    public void onConnect(boolean b) {

    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onException(Exception e) {

    }

    private IDeviceReadable parseJsonDevice(String documentID, String fieldsJson) {
        JsonObject jsonDevice = parser.parse(fieldsJson).getAsJsonObject();
        JsonPrimitive jsonDeviceName = jsonDevice.getAsJsonPrimitive("device_name");
        if(jsonDeviceName != null) {
            String deviceName = jsonDeviceName.getAsString();
            return new DeviceModel(documentID, deviceName);
        } else {
            return new DeviceModel(documentID, "");
        }
    }

    @Override
    public void onDataAdded(String collectionName, String documentID, String fieldsJson) {
        System.out.println("Data added to <" + collectionName + "> in document <" + documentID + ">");
        System.out.println("    Added: " + fieldsJson);
        System.out.println("HERE");
        IDeviceReadable addedDevice = parseJsonDevice(documentID, fieldsJson);

        _deviceList.add(addedDevice);
        callback.callback(null, _deviceList);
    }

    @Override
    public void onDataChanged(String collectionName, String documentID, String updatedValuesJson, String removedValuesJson) {
        System.out.println("Data changed in <" + collectionName + "> in document <" + documentID + ">");
        System.out.println("    Updated: " + updatedValuesJson);
        System.out.println("    Removed: " + removedValuesJson);

        if(removedValuesJson != null) {

            IDeviceReadable removedDevice = parseJsonDevice(documentID, removedValuesJson);
            _deviceList.remove(removedDevice);
            callback.callback(null, _deviceList);
        }
        else if (updatedValuesJson != null) {

            IDeviceReadable updatedDevice = parseJsonDevice(documentID, updatedValuesJson);
            for(IDeviceReadable device : _deviceList) {

                if(device.getId() == updatedDevice.getId()) {
                    _deviceList.remove(device);
                    _deviceList.add(updatedDevice);
                }
            }
        }
    }

    @Override
    public void onDataRemoved(String collectionName, String documentID) {
        System.out.println("Data removed from <" + collectionName + "> in document <" + documentID + ">");

        for(Iterator<IDeviceReadable> it = _deviceList.iterator(); it.hasNext();) {
            IDeviceReadable device = it.next();
            if(device.getId().equals(documentID)) {
               it.remove();
            }
        }

        callback.callback(null, _deviceList);
    }
}
