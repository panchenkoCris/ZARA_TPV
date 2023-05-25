package com.example.zara_tpv.pojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductoInterface {

    @GET("producto")
    Call<List<Producto>> getAllProductos();

    @POST("producto/create")
    Call<Producto> create(@Body Producto producto);
}
