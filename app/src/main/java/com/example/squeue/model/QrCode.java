package com.example.squeue.model;

import java.io.Serializable;

public class QrCode implements Serializable {
    private int id;
    private String duongdan;
    private String mota;

    public QrCode() {
    }

    public QrCode(int id, String duongdan, String mota) {
        this.id = id;
        this.duongdan = duongdan;
        this.mota = mota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuongdan() {
        return duongdan;
    }

    public void setDuongdan(String duongdan) {
        this.duongdan = duongdan;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
