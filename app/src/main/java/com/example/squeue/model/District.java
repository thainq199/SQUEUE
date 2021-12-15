package com.example.squeue.model;

import java.io.Serializable;

public class District implements Serializable {
    private String name;
    private int code;
    private int province_code;

    public District() {
    }

    public District(String name, int code, int province_code) {
        this.name = name;
        this.code = code;
        this.province_code = province_code;
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

    public int getProvince_code() {
        return province_code;
    }

    public void setProvince_code(int province_code) {
        this.province_code = province_code;
    }
}
