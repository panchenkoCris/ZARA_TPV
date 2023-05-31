package com.example.zara_tpv.pojo;

public class Type {
    private int id;
    private String nombre_tipo;
    private String longitud_tipo;

    public Type(int id, String nombre_tipo, String longitud_tipo) {
        this.id = id;
        this.nombre_tipo = nombre_tipo;
        this.longitud_tipo = longitud_tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public String getLongitud_tipo() {
        return longitud_tipo;
    }

    public void setLongitud_tipo(String longitud_tipo) {
        this.longitud_tipo = longitud_tipo;
    }
}
