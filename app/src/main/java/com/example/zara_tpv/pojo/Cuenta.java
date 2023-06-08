package com.example.zara_tpv.pojo;

import com.google.gson.annotations.SerializedName;

public class Cuenta {
    @SerializedName("id_cuenta")
    private int id_cuenta;
    @SerializedName("id_usuario")
    private int id_usuario;
    @SerializedName("id_descuento")
    private int id_descuento;
    @SerializedName("descuento_activado")
    private int descuento_activado;

    public Cuenta(int id_cuenta, int id_usuario, int id_descuento, int descuento_activado) {
        this.id_cuenta = id_cuenta;
        this.id_usuario = id_usuario;
        this.id_descuento = id_descuento;
        this.descuento_activado = descuento_activado;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_descuento() {
        return id_descuento;
    }

    public void setId_descuento(int id_descuento) {
        this.id_descuento = id_descuento;
    }

    public int getDescuento_activado() {
        return descuento_activado;
    }

    public void setDescuento_activado(int descuento_activado) {
        this.descuento_activado = descuento_activado;
    }
}
