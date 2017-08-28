package com.appl_maint_mngt.address.models.interfaces;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IAddressReadable {
    String getAddressLine1();
    String getAddressLine2();
    String getCity();
    String getState();
    String getCountry();
    String getPostalCode();
    float getLongitude();
    float getLatitude();
}
