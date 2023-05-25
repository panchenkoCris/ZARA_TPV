package com.example.zara_tpv.manager;

import android.content.Context;

import com.example.zara_tpv.adapter.ListClothesAdapter;
import com.example.zara_tpv.pojo.ListClothesNested;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.pojo.ProductoInterface;
import com.example.zara_tpv.retrofit.RetrofitService;
import com.example.zara_tpv.windows.ResumeShopWindow;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListManager {
    private static Context context;
    private static List<Producto> clothes;

    public ListManager(Context context) {
        this.context = context;
        declareClothes();
    }

    public static void declareClothes() {
        clothes = new ArrayList<>();
        TaskGetAllProductos taskGetAllProductos = new TaskGetAllProductos(context);
        taskGetAllProductos.iniciar();
    }

    public static void getCategoryClothes(ListClothesAdapter adapter) {
        RetrofitService retrofitService = new RetrofitService();
        ProductoInterface productoInterface = retrofitService.getRetrofit().create(ProductoInterface.class);
        productoInterface.getAllProductos().enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                clothes = response.body();
                adapter.setProductos(clothes);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

            }
        });

//        List<Producto> productos = new ArrayList<>();
//        productos.add(new Producto(1, 5, 15.0, 40, "Negro", "Elegante", 3, 2));
//        adapter.setProductos(productos);
    }

    public static void setListProductos(ArrayList<Producto> ps) {
        clothes = ps;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
