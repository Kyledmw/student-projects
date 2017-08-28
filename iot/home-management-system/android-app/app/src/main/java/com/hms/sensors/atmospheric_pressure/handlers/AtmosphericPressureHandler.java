package com.hms.sensors.atmospheric_pressure.handlers;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hms.sensors.atmospheric_pressure.handlers.interfaces.IAtmosphericPressureHandler;
import com.hms.sensors.atmospheric_pressure.models.interfaces.IAtmosphericPressureReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.sensors.atmospheric_pressure.models.AtmosphericPressureModel;

import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.ResultListener;
import im.delight.android.ddp.SubscribeListener;

/**
 * Created by alan on 26/02/16.
 */
public class AtmosphericPressureHandler implements MeteorCallback, IAtmosphericPressureHandler {

    private ICallback<IAtmosphericPressureReadable> callback;
    private String _subscriptionId;
    private JsonParser parser = new JsonParser();
    private String _type;

    public AtmosphericPressureHandler() {
        MeteorSingleton.getInstance().setCallback(this);
    }


    @Override
    public void getAtmosphericPressure(IDeviceReadable device, ICallback<IAtmosphericPressureReadable> callback) {

        this.callback = callback;

       _subscriptionId = MeteorSingleton.getInstance().subscribe("atmoPressureForDevice", new Object[]{device.getId()}, new SubscribeListener() {
            @Override
            public void onSuccess() {
                System.out.println("### Successfully subscribed to atmo Pressure ###");
            }

            @Override
            public void onError(String s, String s1, String s2) {
                System.out.println("### Atmo Pressure subscription failed ###");
                System.out.println(s + "\n" + s1 + "\n" + s2 + "\n");
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

    private void jsonToAtmoshericPressureModel(String fieldsJson) {

        final JsonObject jsonAtmoPressure = parser.parse(fieldsJson).getAsJsonObject();

        String typeId = jsonAtmoPressure.getAsJsonPrimitive("type_id").getAsString();

        MeteorSingleton.getInstance().call("measurementTypeForId", new Object[]{typeId}, new ResultListener() {
            @Override
            public void onSuccess(String s) {

                long timestamp = jsonAtmoPressure.getAsJsonPrimitive("timestamp").getAsLong();
                double value = jsonAtmoPressure.getAsJsonPrimitive("value").getAsDouble();

                JsonObject jsonObject = parser.parse(s).getAsJsonObject();
                String type = jsonObject.getAsJsonPrimitive("type").getAsString();

                callback.callback(null, new AtmosphericPressureModel(timestamp, value, type));

            }

            @Override
            public void onError(String s, String s1, String s2) {

            }
        });

    }

    @Override
    public void onDataAdded(String collectionName, String documentID, String fieldsJson) {
        System.out.println("Data added to <" + collectionName + "> in document <" + documentID + ">");
        System.out.println("    Added: " + fieldsJson);

        jsonToAtmoshericPressureModel(fieldsJson);
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
