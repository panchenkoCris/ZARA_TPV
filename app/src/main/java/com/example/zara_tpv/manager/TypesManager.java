package com.example.zara_tpv.manager;

import android.animation.TypeEvaluator;

import com.example.zara_tpv.adapter.ListProductsAdapter;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.pojo.Type;
import com.example.zara_tpv.retrofit.RetrofitInterface;
import com.example.zara_tpv.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypesManager {
    private static List<Type> tipos = new ArrayList<>();

    public TypesManager() {
        this.tipos = new ArrayList<>();
        getAllTypes();
    }
    public void getAllTypes() {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface productoInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        productoInterface.getAllTypes().enqueue(new Callback<List<Type>>() {
            @Override
            public void onResponse(Call<List<Type>> call, Response<List<Type>> response) {
                tipos = response.body();
            }

            @Override
            public void onFailure(Call<List<Type>> call, Throwable t) {

            }
        });
    }

    public static Type getOneType(int id_tipo) {
        for(Type tipo: tipos) {
            if(tipo.getId() == id_tipo) {
                return tipo;
            }
        }
        return null;
    }
}
