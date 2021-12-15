package com.example.squeue.model;

import java.io.Serializable;

public class City implements Serializable {
    private String name;
    private int code;

    public City() {
    }

    public City(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
