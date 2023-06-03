package com.example.zara_tpv.pojo;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("id")
    private int id;
    @SerializedName("genero")
    private String genero;

    public Category(int id, String genero) {
        this.id = id;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
