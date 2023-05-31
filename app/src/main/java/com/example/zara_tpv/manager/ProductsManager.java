package com.example.zara_tpv.manager;

import android.content.Context;
import android.widget.Toast;

import com.example.zara_tpv.adapter.ListProductsAdapter;
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

    public void getAllProducts(ListProductsAdapter adapter) {
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

    public static void getProducto(int id, ListProductsAdapter adapter) {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface productoInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        productoInterface.getProducto(id).enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                Producto producto = response.body();
                if(producto.getCb_producto() != 0) {
                    adapter.addProducto(producto);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(context, "Producto añadido correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    DialogManager.openDialogError(context, "El código insertado no se encuentra registrado en nuestra base");
                }
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
