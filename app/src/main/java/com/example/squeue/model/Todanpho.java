package com.example.squeue.model;

import java.io.Serializable;

public class Todanpho implements Serializable {
    private int id;
    private String ten;
    private String mota;
    private int phuong_id;

    public Todanpho() {
    }

    public Todanpho(int id, String ten, String mota, int phuong_id) {
        this.id = id;
        this.ten = ten;
        this.mota = mota;
        this.phuong_id = phuong_id;
    }

    public int getPhuong_id() {
        return phuong_id;
    }

    public void setPhuong_id(int phuong_id) {
        this.phuong_id = phuong_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
