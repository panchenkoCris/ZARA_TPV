package com.example.zara_tpv.manager;

import com.example.zara_tpv.pojo.LineaTicket;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.pojo.Ticket;
import com.example.zara_tpv.retrofit.RetrofitInterface;
import com.example.zara_tpv.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketManager {
    public static Ticket ticketFinal;

    public TicketManager(Ticket ticket) {
        createTicket(ticket);
    }

    public static void createTicket(Ticket ticket) {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface ticketInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        ticketInterface.createTicket(ticket).enqueue(new Callback<Ticket>() {
            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                ticketFinal = response.body();
            }

            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {

            }
        });
    }

    public static void addLineTicket(Producto producto) {
        LineaTicket lineaTicket = new LineaTicket(producto.getCb_producto(), ticketFinal.getId());
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface ticketInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        ticketInterface.createLineaTicket(lineaTicket).enqueue(new Callback<LineaTicket>() {
            @Override
            public void onResponse(Call<LineaTicket> call, Response<LineaTicket> response) {
                LineaTicket lt = response.body();
            }

            @Override
            public void onFailure(Call<LineaTicket> call, Throwable t) {

            }
        });
    }

    public static Ticket getTicketFinal() {
        return ticketFinal;
    }
}
