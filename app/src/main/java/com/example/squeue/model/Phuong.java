package com.example.squeue.model;

import java.io.Serializable;

public class Phuong implements Serializable {
    private String id;
    private String ten;
    private String mota;

    public Phuong() {
    }

    public Phuong(String id, String ten, String mota) {
        this.id = id;
        this.ten = ten;
        this.mota = mota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
