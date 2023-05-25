package com.example.zara_tpv.manager;

import android.content.Context;

import com.example.zara_tpv.adapter.ListClothesAdapter;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.retrofit.RetrofitInterface;
import com.example.zara_tpv.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsManager {
    private static Context context;
    private static List<Producto> clothes;

    public ProductsManager(Context context) {
        this.context = context;
        clothes = new ArrayList<>();
    }

    public void getAllProducts(ListClothesAdapter adapter) {
        DialogManager.openProgressBar(context);
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface productoInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        productoInterface.getAllProductos().enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                clothes = response.body();
                adapter.setProductos(clothes);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                DialogManager.openDialogError(context, "No se ha podido sacar los productos");
            }
        });
    }

    public static void getProducto(int id, ListClothesAdapter adapter) {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface productoInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        productoInterface.getProducto(id).enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                Producto producto = response.body();
                adapter.addProducto(producto);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {

            }
        });
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
