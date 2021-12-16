package com.example.squeue.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private String id;
    private String name;
    private String dob;
    private String phoneNum;
    private Address address;
    private int state;

    public Customer() {
    }

    public Customer(String id, String name, String dob, String phoneNum, Address address, int state) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phoneNum = phoneNum;
        this.address = address;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
