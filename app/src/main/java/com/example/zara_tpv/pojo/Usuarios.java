package com.example.zara_tpv.pojo;

import com.google.gson.annotations.SerializedName;

public class Usuarios {
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("correo")
    private String correo;
    @SerializedName("contraseña")
    private String contraseña;
    @SerializedName("rol")
    private String rol;

    public Usuarios(String nombre, String correo, String contraseña, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
