package com.example.zara_tpv.retrofit;

import com.example.zara_tpv.pojo.Autentificacion;
import com.example.zara_tpv.pojo.Cuenta;
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

    @GET("productosTicket")
    Call<List<Producto>> getProductosPerTicket(@Query("id_ticket") int id_ticket);

    @GET("lineaticketsTicket")
    Call<List<LineaTicket>> getLineasTicketPerTicket(@Query("id_ticket") int id_ticket);

    @GET("obtenerTicketLibre")
    Call<Ticket> obtenerTicketLibre();

    @GET("tipo")
    Call<List<Type>> getAllTypes();

    @GET("getAllAccountsPerUser")
    Call<List<Cuenta>> getAllAccountsPerUser(@Query("id_usuario") int id_usuario);

    @GET("authenticate")
    Call<Autentificacion> authenticate(@Query("correo") String correo, @Query("contraseña") String password);

    @GET("descuentosUsuario")
    Call<List<Discount>> getAllDiscountsPerUser(@Query("id_usuario") int id_usuario);

    @POST("ticket/update/{id_ticket}")
    Call<Ticket> updateTicket(@Body Usuarios usuarios, @Path("id_ticket") int id_ticket);

    @POST("ticket/update/{id_ticket}")
    Call<Ticket> updateTicket(@Body Ticket ticket, @Path("id_ticket") int id_ticket);

    @POST("usuarios/create")
    Call<Usuarios> createUsuarios(@Body Usuarios usuarios);

    @POST("producto/create")
    Call<Producto> create(@Body Producto producto);

    @POST("producto/update/{cb_producto}")
    Call<Producto> updateProducto(@Body Producto producto, @Path("cb_producto") int cb_producto);

    @POST("ticket/create")
    Call<Ticket> createTicket(@Body Ticket ticket);

    @POST("lineaticket/create")
    Call<LineaTicket> createLineaTicket(@Body LineaTicket lineaticket);

    @POST("cuenta/update/{id_cuenta}")
    Call<Cuenta> actualizarDescuentoUsuario(@Body Cuenta cuenta, @Path("id_cuenta") int id_cuenta);

    @POST("eliminarLineaTicket")
    Call<LineaTicket> eliminarLineaTicket(@Body LineaTicket lineaTicket);

    @POST("eliminarProducto")
    Call<Producto> eliminarProducto(@Body Producto producto);

}
