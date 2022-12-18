package com.example.magis.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Barang implements Serializable {
    @SerializedName("nama")
    private String nama;

    @SerializedName("qty")
    private int qty;

    @SerializedName("kode")
    private int kode;

    @SerializedName("berat")
    private int berat;

    @SerializedName("id")
    private int id;

    public String getNama() {
        return nama;
    }

    public int getBerat() {
        return berat;
    }

    public int getQty() {
        return qty;
    }

    public int getKode() {
        return kode;
    }

    public int getId() {
        return id;
    }
}
