package com.example.zara_tpv.retrofit;

import com.example.zara_tpv.pojo.Discount;
import com.example.zara_tpv.pojo.LineaTicket;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.pojo.Ticket;
import com.example.zara_tpv.pojo.Type;
import com.example.zara_tpv.pojo.Usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("producto")
    Call<List<Producto>> getAllProductos();

    @GET("producto/{id}")
    Call<Producto> getProducto(@Path("id") int id);

    @POST("producto/create")
    Call<Producto> create(@Body Producto producto);

    @GET("tipo")
    Call<List<Type>> getAllTypes();

    @POST("usuarios/authenticate")
    Call<String[]> authenticate(@Query("correo") String correo, @Query("contraseña") String password);

    @GET("descuento")
    Call<List<Discount>> getAllDiscounts();

    @POST("ticket/create")
    Call<Ticket> createTicket(@Body Ticket ticket);

    @POST("lineaticket/create")
    Call<LineaTicket> createLineaTicket(@Body LineaTicket lineaticket);

}
