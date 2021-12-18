package com.example.squeue.model;

import java.io.Serializable;

public class Address implements Serializable {
    private int id;
    private String city;
    private String district;
    private String ward;

    public Address() {

    }

    public Address(int id, String city, String district, String ward) {
        this.id = id;
        this.city = city;
        this.district = district;
        this.ward = ward;
    }

    public Address(String city, String district, String ward) {
        this.city = city;
        this.district = district;
        this.ward = ward;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }
}
