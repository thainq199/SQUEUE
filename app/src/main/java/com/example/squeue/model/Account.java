package com.example.squeue.model;

import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    private String name;
    private String img;
    private String address;
    private String birth;
    private String phone;

    public Account(String name, String img, String address, String birth, String phone) {
        this.name = name;
        this.img = img;
        this.address = address;
        this.birth = birth;
        this.phone = phone;
    }

    public Account(int id, String name, String img, String address, String birth, String phone) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.address = address;
        this.birth = birth;
        this.phone = phone;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
