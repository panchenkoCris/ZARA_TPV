package com.example.zara_tpv.pojo;

import com.google.gson.annotations.SerializedName;

public class Autentificacion {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Usuarios usuario;

    public Autentificacion(int status, String message, Usuarios usuario) {
        this.status = status;
        this.message = message;
        this.usuario = usuario;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
