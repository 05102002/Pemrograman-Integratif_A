package com.example.magis.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BarangResponse {

    @SerializedName("data")
    private List<Barang> data;

    @SerializedName("status")
    private int status;

    public List<Barang> getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }
}
