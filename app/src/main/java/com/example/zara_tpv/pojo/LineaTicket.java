package com.example.zara_tpv.pojo;

import com.google.gson.annotations.SerializedName;

public class LineaTicket {
    @SerializedName("cb_producto")
    private int cb_producto;
    @SerializedName("id_lineaticket")
    private int id_lineaticket;
    @SerializedName("id_ticket")
    private int id_ticket;

    public LineaTicket(int cb_producto, int id_ticket) {
        this.cb_producto = cb_producto;
        this.id_ticket = id_ticket;
    }

    public int getCb_producto() {
        return cb_producto;
    }

    public void setCb_producto(int cb_producto) {
        this.cb_producto = cb_producto;
    }

    public int getId_lineaticket() {
        return id_lineaticket;
    }

    public void setId_lineaticket(int id_lineaticket) {
        this.id_lineaticket = id_lineaticket;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }
}
