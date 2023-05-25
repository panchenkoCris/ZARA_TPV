package com.example.zara_tpv.pojo;

import com.google.gson.annotations.SerializedName;

public class Producto {
    @SerializedName("cb_producto")
    private int cb_producto;
    @SerializedName("existencias")
    private int existencias;
    @SerializedName("precio")
    private double precio;
    @SerializedName("talla")
    private int talla;
    @SerializedName("color")
    private String color;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("id_tipo")
    private int id_tipo;
    @SerializedName("id_categoria")
    private int id_categoria;

    public Producto(int cb_producto, int existencias, double precio, int talla, String color, String descripcion, int id_tipo, int id_categoria) {
        this.cb_producto = cb_producto;
        this.existencias = existencias;
        this.precio = precio;
        this.talla = talla;
        this.color = color;
        this.descripcion = descripcion;
        this.id_tipo = id_tipo;
        this.id_categoria = id_categoria;
    }

    public int getCb_producto() {
        return cb_producto;
    }

    public void setCb_producto(int cb_producto) {
        this.cb_producto = cb_producto;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
}
