package com.hms.devices.handlers;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.hms.devices.handlers.interfaces.IDeviceMovedHandler;
import com.hms.devices.model.interfaces.IDeviceMovedReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.devices.model.DeviceMovedModel;

import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.SubscribeListener;

/**
 * Created by alan on 26/02/16.
 */
public class DeviceMovedHandler implements MeteorCallback, IDeviceMovedHandler {

    private ICallback<IDeviceMovedReadable> callback;
    private JsonParser parser = new JsonParser();
    private String _subscriptionId;

    public DeviceMovedHandler() {
        MeteorSingleton.getInstance().setCallback(this);
    }

    @Override
    public void getDeviceMoved(IDeviceReadable device, ICallback<IDeviceMovedReadable> callback) {

        this.callback = callback;

        _subscriptionId = MeteorSingleton.getInstance().subscribe("deviceMovementForDevice", new Object[]{device.getId()}, new SubscribeListener() {
            @Override
            public void onSuccess() {
                System.out.println("### Successfully subscribed to device moved ###");
            }

            @Override
            public void onError(String s, String s1, String s2) {
                System.out.println("### Device moved subscription failed ###");
                System.out.println(s + "\n" + s1 + "\n" + s2 +"\n");
            }
        });
    }

    @Override
    public void unRegisterAsCallback() {
        MeteorSingleton.getInstance().unsubscribe(_subscriptionId);
        MeteorSingleton.getInstance().unsetCallback(this);
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

    private IDeviceMovedReadable parseJsonDeviceMoved(String fieldsJson) {
        JsonObject jsonDeviceMoved = parser.parse(fieldsJson).getAsJsonObject();
        JsonPrimitive jsonTimestamp = jsonDeviceMoved.getAsJsonPrimitive("timestamp");

        long timestamp = jsonTimestamp.getAsLong();

        return new DeviceMovedModel(timestamp);
    }

    @Override
    public void onDataAdded(String collectionName, String documentID, String fieldsJson) {
        System.out.println("Data added to <"+collectionName+"> in document <"+documentID+">");
        System.out.println("    Added: " + fieldsJson);

        IDeviceMovedReadable deviceMovedModel = parseJsonDeviceMoved(fieldsJson);
        callback.callback(null, deviceMovedModel);
    }

    @Override
    public void onDataChanged(String collectionName, String documentID, String updatedValuesJson, String removedValuesJson) {
        System.out.println("Data changed in <"+collectionName+"> in document <"+documentID+">");
        System.out.println("    Updated: " + updatedValuesJson);
        System.out.println("    Removed: " + removedValuesJson);
    }

    @Override
    public void onDataRemoved(String collectionName, String documentID) {
        System.out.println("Data removed from <" + collectionName + "> in document <" + documentID + ">");
    }
}
