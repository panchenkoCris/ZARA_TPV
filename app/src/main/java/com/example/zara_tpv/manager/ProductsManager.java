package com.example.zara_tpv.manager;

import android.content.Context;
import android.widget.Toast;

import com.example.zara_tpv.R;
import com.example.zara_tpv.adapter.ListProductsAdapter;
import com.example.zara_tpv.adapter.ListProductsShopAdapter;
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
    private static List<Producto> clothesAdmin;

    public ProductsManager(Context context) {
        this.context = context;
        clothes = new ArrayList<>();
    }

    public ProductsManager() {
        clothesAdmin = new ArrayList<>();
        clothesAdmin = getAllProducts();
    }

    public void getAllProducts(ListProductsShopAdapter adapter) {
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
                DialogManager.openDialogError(context, context.getString(R.string.cant_get_products));
            }
        });
    }

    public List<Producto> getAllProducts() {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface productoInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        productoInterface.getAllProductos().enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                clothesAdmin = response.body();
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                DialogManager.openDialogError(context, context.getString(R.string.cant_get_products));
            }
        });

        return clothesAdmin;
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
                    DialogManager.openDialogError(context, context.getString(R.string.product_not_exists));
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
            }
        });
    }

    public static void deleteClothe(Producto producto) {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface productoInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        productoInterface.eliminarProducto(producto).enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                Producto prod = response.body();
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });
    }

    public static void updateProducto(Producto producto) {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface productoInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        productoInterface.updateProducto(producto, producto.getCb_producto()).enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                Producto prod = response.body();
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {

            }
        });
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static List<Producto> getClothes() {
        return clothes;
    }

    public static Context getContext() {
        return context;
    }

    public static List<Producto> getClothesAdmin() {
        return clothesAdmin;
    }
}
