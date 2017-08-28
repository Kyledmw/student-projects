package com.appl_maint_mngt.appliance.models;

import com.appl_maint_mngt.appliance.models.constants.ApplianceType;

/**
 * Created by Kyle on 18/03/2017.
 */

public class Appliance extends AAppliance {
    private String id;
    private String productNo;
    private String name;
    private String brand;
    private ApplianceType type;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getProductNo() {
        return productNo;
    }

    @Override
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public ApplianceType getType() {
        return type;
    }

    @Override
    public void setType(ApplianceType type) {
        this.type = type;
    }
}
