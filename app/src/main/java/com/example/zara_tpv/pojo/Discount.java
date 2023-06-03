package com.example.zara_tpv.pojo;

import com.google.gson.annotations.SerializedName;

public class Discount {
    @SerializedName("id")
    private int id;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("cantidad_descuento")
    private double cantidad_descuento;
    public Discount(int id, String descripcion, double cantidad_descuento) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad_descuento = cantidad_descuento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidad_descuento() {
        return cantidad_descuento;
    }

    public void setCantidad_descuento(double cantidad_descuento) {
        this.cantidad_descuento = cantidad_descuento;
    }
}
