package com.example.magis.model;

import com.google.gson.annotations.SerializedName;

public class InsertProductResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private int status;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
