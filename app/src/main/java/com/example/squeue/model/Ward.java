package com.example.squeue.model;

import java.io.Serializable;

public class Ward implements Serializable {
    private String name;
    private int code;
    private int district_code;

    public Ward() {
    }

    public Ward(String name, int code, int district_code) {
        this.name = name;
        this.code = code;
        this.district_code = district_code;
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

    public int getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(int district_code) {
        this.district_code = district_code;
    }
}
