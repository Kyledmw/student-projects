package com.appl_maint_mngt.address.models.interfaces;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IAddressWriteable {

    void setAddressLine1(String addressLine1);
    void setAddressLine2(String addressLine2);
    void setCity(String city);
    void setState(String state);
    void setCountry(String country);
    void setPostalCode(String postalCode);
    void setLongitude(float longitude);
    void setLatitude(float latitude);
}
