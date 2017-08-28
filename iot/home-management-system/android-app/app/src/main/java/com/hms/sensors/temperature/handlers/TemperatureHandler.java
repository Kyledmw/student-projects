package com.hms.sensors.temperature.handlers;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hms.sensors.temperature.handlers.interfaces.ITemperatureHandler;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.sensors.temperature.models.interfaces.ITemperatureReadable;
import com.hms.sensors.temperature.models.TemperatureModel;

import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.ResultListener;
import im.delight.android.ddp.SubscribeListener;

/**
 * Created by alan on 26/02/16.
 */
public class TemperatureHandler implements MeteorCallback, ITemperatureHandler {

    private ICallback<ITemperatureReadable> _callback;
    private JsonParser parser = new JsonParser();
    private String _subscriptionId;

    @Override
    public void getTemperature(IDeviceReadable device, ICallback<ITemperatureReadable> callback) {

        MeteorSingleton.getInstance().setCallback(this);

        this._callback = callback;

        _subscriptionId = MeteorSingleton.getInstance().subscribe("temperatureForDevice", new Object[]{device.getId()}, new SubscribeListener() {
            @Override
            public void onSuccess() {
                System.out.println("### Successfully subscribed to temperature ###");
            }

            @Override
            public void onError(String s, String s1, String s2) {
                System.out.println("### Temperature subscription failed ###");
                System.out.println(s + "\n" + s1 + "\n" + s2 +"\n");
            }
        });
    }

    @Override
    public void unRegisterAsCallback() {
        MeteorSingleton.getInstance().unsubscribe(_subscriptionId);
        MeteorSingleton.getInstance().unsetCallback(this);
        System.out.println("(Unregistered Temperature)");
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

    private void  jsonToTemperature(String fieldsJson) {
        final JsonObject jsonTemperature = parser.parse(fieldsJson).getAsJsonObject();

        long timestamp = jsonTemperature.getAsJsonPrimitive("timestamp").getAsLong();
        double value = jsonTemperature.getAsJsonPrimitive("value").getAsDouble();
        String typeId = jsonTemperature.getAsJsonPrimitive("type_id").getAsString();


        MeteorSingleton.getInstance().call("measurementTypeForId", new Object[]{typeId}, new ResultListener() {
            @Override
            public void onSuccess(String s) {

                long timestamp = jsonTemperature.getAsJsonPrimitive("timestamp").getAsLong();
                double value = jsonTemperature.getAsJsonPrimitive("value").getAsDouble();

                JsonObject jsonObject = parser.parse(s).getAsJsonObject();
                String type = jsonObject.getAsJsonPrimitive("type").getAsString();

                _callback.callback(null, new TemperatureModel(timestamp, value, type));

            }

            @Override
            public void onError(String s, String s1, String s2) {

            }
        });

    }

    @Override
    public void onDataAdded(String collectionName, String documentID, String fieldsJson) {
        System.out.println("Data added to <"+collectionName+"> in document <"+documentID+">");
        System.out.println("    Added: " + fieldsJson);

        jsonToTemperature(fieldsJson);
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
