package com.example.zara_tpv.pojo;

public class Discount {
    private int id;
    private String descripcion;
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
