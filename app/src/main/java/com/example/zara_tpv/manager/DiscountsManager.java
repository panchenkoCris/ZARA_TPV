package com.example.zara_tpv.manager;

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
    private static List<Discount> descuentos = new ArrayList<>();

    public DiscountsManager() {
        this.descuentos = new ArrayList<>();
        getAllDiscounts();
    }
    public void getAllDiscounts() {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface productoInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        productoInterface.getAllDiscounts().enqueue(new Callback<List<Discount>>() {
            @Override
            public void onResponse(Call<List<Discount>> call, Response<List<Discount>> response) {
                descuentos = response.body();
            }

            @Override
            public void onFailure(Call<List<Discount>> call, Throwable t) {

            }
        });
    }
}
