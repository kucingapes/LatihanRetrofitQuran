package com.kucingapes.latihanretrofit_quran.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cek {
    @SerializedName("data")
    private Data data;

    public Cek(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
