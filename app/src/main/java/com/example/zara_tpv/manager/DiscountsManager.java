package com.example.zara_tpv.manager;

import android.content.Context;

import com.example.zara_tpv.pojo.Discount;
import com.example.zara_tpv.pojo.Type;
import com.example.zara_tpv.retrofit.RetrofitInterface;
import com.example.zara_tpv.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscountsManager {
    private List<Discount> descuentos;

    public DiscountsManager() {
        this.descuentos = new ArrayList<>();
    }
    public void getAllDiscounts(int id_usuario, Context context) {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface productoInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        productoInterface.getAllDiscountsPerUser(id_usuario).enqueue(new Callback<List<Discount>>() {
            @Override
            public void onResponse(Call<List<Discount>> call, Response<List<Discount>> response) {
                descuentos = response.body();
                DialogManager.openDialogDiscounts(context, descuentos);
            }

            @Override
            public void onFailure(Call<List<Discount>> call, Throwable t) {

            }
        });
    }

    public List<Discount> getDescuentos() {
        return descuentos;
    }
}
