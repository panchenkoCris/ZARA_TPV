package com.example.zara_tpv.manager;

import com.example.zara_tpv.adapter.ListProductsAdapter;
import com.example.zara_tpv.pojo.LineaTicket;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.pojo.Ticket;
import com.example.zara_tpv.pojo.Usuarios;
import com.example.zara_tpv.retrofit.RetrofitInterface;
import com.example.zara_tpv.retrofit.RetrofitService;
import com.google.firebase.crashlytics.buildtools.ndk.internal.csym.CsymSymbolFileService;

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
    public static List<LineaTicket> lineaTickets = new ArrayList<>();

    public TicketManager(Ticket ticket) {
        createTicket(ticket);
    }

    public static void getTicket() {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface ticketInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        ticketInterface.obtenerTicketLibre().enqueue(new Callback<Ticket>() {
            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                Ticket ticket = response.body();
                if(ticket == null) {
                    Date date = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM_dd", Locale.getDefault());
                    String dateFormatted = df.format(date);

                    Ticket newTicket = new Ticket(dateFormatted, 0);
                    createTicket(newTicket);
                } else {
                    ticketFinal = ticket;
                    getLineasTicketPerTicket();
                }
            }

            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {
                System.out.println("Hola");
            }
        });
    }

    private static void createTicket(Ticket ticket) {
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

    public static void addLineTicket(Producto producto, ListProductsAdapter adapter) {
        LineaTicket lineaTicket = new LineaTicket(producto.getCb_producto(), ticketFinal.getId());
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface ticketInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        ticketInterface.createLineaTicket(lineaTicket).enqueue(new Callback<LineaTicket>() {
            @Override
            public void onResponse(Call<LineaTicket> call, Response<LineaTicket> response) {
                getProductsPerTicket(adapter);
            }

            @Override
            public void onFailure(Call<LineaTicket> call, Throwable t) {

            }
        });
    }

    public static List<Producto> getProductsPerTicket(ListProductsAdapter adapter) {
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
        return productos;
    }

    private static void getLineasTicketPerTicket() {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface ticketInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        ticketInterface.getLineasTicketPerTicket(ticketFinal.getId()).enqueue(new Callback<List<LineaTicket>>() {
            @Override
            public void onResponse(Call<List<LineaTicket>> call, Response<List<LineaTicket>> response) {
                lineaTickets = response.body();
            }

            @Override
            public void onFailure(Call<List<LineaTicket>> call, Throwable t) {

            }
        });
    }

    public static void updateTicket(Usuarios usuarios) {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface ticketInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        ticketInterface.updateTicket(usuarios, ticketFinal.getId()).enqueue(new Callback<Ticket>() {
            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                Ticket ticket = response.body();
            }

            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {
                System.out.println("Hola");
            }
        });
    }

    public static void updateTicket() {
        ticketFinal.setRegistrado(1);
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface ticketInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        ticketInterface.updateTicket(ticketFinal, ticketFinal.getId()).enqueue(new Callback<Ticket>() {

            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                Ticket ticket = response.body();
            }

            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {
                System.out.println("Hola");
            }
        });
    }

    public static void eliminarLineaTicket(int cb_producto) {
        LineaTicket lineaTicket = buscarLineaTicket(ticketFinal.getId(), cb_producto);
        if(lineaTicket != null) {
            RetrofitService retrofitService = new RetrofitService();
            RetrofitInterface ticketInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
            ticketInterface.eliminarLineaTicket(lineaTicket).enqueue(new Callback<LineaTicket>() {
                @Override
                public void onResponse(Call<LineaTicket> call, Response<LineaTicket> response) {
                    System.out.println(response.body());
                }

                @Override
                public void onFailure(Call<LineaTicket> call, Throwable t) {
                    System.out.println(t.getLocalizedMessage());
                }
            });
        }
    }

    private static LineaTicket buscarLineaTicket(int id_ticket, int cb_producto) {
        for (LineaTicket lineaTicket: lineaTickets) {
            if (lineaTicket.getId_ticket() == id_ticket &&
                    lineaTicket.getCb_producto() == cb_producto) {
                return lineaTicket;
            }
        }
        return null;
    }

    public static void setAdapter(ListProductsAdapter adapter) {
        TicketManager.adapter = adapter;
    }

    public static List<Producto> getProductos() {
        return productos;
    }
}
