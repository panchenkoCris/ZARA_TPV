package com.example.zara_tpv.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Ticket {
    @SerializedName("id_ticket")
    private int id;
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("registrado")
    private int registrado;
    @SerializedName("id_usuario")
    private Usuarios usuario;

    public Ticket(String fecha, int registrado) {
        this.fecha = fecha;
        this.registrado = registrado;
        this.usuario = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getRegistrado() {
        return registrado;
    }

    public void setRegistrado(int registrado) {
        this.registrado = registrado;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
