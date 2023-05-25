package com.example.zara_tpv.retrofit;

import com.example.zara_tpv.pojo.Producto;

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
}
