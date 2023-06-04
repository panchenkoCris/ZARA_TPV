package com.example.zara_tpv.manager;

import com.example.zara_tpv.adapter.ListProductsAdapter;
import com.example.zara_tpv.pojo.LineaTicket;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.pojo.Ticket;
import com.example.zara_tpv.retrofit.RetrofitInterface;
import com.example.zara_tpv.retrofit.RetrofitService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketManager {
    public static Ticket ticketFinal;
    public static ListProductsAdapter adapter;
    public static List<Producto> productos = new ArrayList<>();

    public TicketManager() {
        createTicket();
    }

//    public static void getTicket() {
//        RetrofitService retrofitService = new RetrofitService();
//        RetrofitInterface ticketInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
//        ticketInterface.obtenerTicketNoRegistrado().enqueue(new Callback<Ticket>() {
//            @Override
//            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
//                Ticket ticket = response.body();
//                if(ticket == null) {
//                    createTicket();
//                } else {
//                    ticketFinal = ticket;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Ticket> call, Throwable t) {
//
//            }
//        });
//    }

    private static void createTicket() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM_dd", Locale.getDefault());
        String dateFormatted = df.format(date);

        Ticket ticket = new Ticket(dateFormatted, 0);

        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface ticketInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        ticketInterface.createTicket(ticket).enqueue(new Callback<Ticket>() {
            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                Ticket ticket = response.body();
                ticketFinal = ticket;
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
                getProductsPerTicket();
            }

            @Override
            public void onFailure(Call<LineaTicket> call, Throwable t) {

            }
        });
    }

    public static void getProductsPerTicket() {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface ticketInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        ticketInterface.getProductosPerTicket(ticketFinal.getId()).enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                productos = response.body();
                ListProductsAdapter.setProductos(productos);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

            }
        });
    }

    public static void setAdapter(ListProductsAdapter adapter) {
        TicketManager.adapter = adapter;
    }

    public static List<Producto> getProductos() {
        return productos;
    }
}
