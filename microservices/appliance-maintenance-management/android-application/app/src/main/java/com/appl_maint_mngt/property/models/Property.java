package com.appl_maint_mngt.property.models;


import com.appl_maint_mngt.address.models.Address;

/**
 * Created by Kyle on 17/03/2017.
 */

public class Property extends AProperty {

    private Long id;

    private Address address;

    private int age;

    private int bedCount;

    private int bathroomCount;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int getBedCount() {
        return bedCount;
    }

    @Override
    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    @Override
    public int getBathroomCount() {
        return bathroomCount;
    }

    @Override
    public void setBathroomCount(int bathroomCount) {
        this.bathroomCount = bathroomCount;
    }
}
