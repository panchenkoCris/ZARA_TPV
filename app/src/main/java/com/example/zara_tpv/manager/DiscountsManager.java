package com.example.zara_tpv.manager;


import com.example.zara_tpv.pojo.Discount;
import com.example.zara_tpv.retrofit.RetrofitInterface;
import com.example.zara_tpv.retrofit.RetrofitService;
import com.example.zara_tpv.windows.PayWindow;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscountsManager {
    private static List<Discount> descuentos;
    private static int id_usuario_discounts;

    public DiscountsManager() {
        this.descuentos = new ArrayList<>();
    }

    public void getAllDiscounts(int id_usuario) {
        id_usuario_discounts = id_usuario;
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface productoInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        productoInterface.getAllDiscountsPerUser(id_usuario).enqueue(new Callback<List<Discount>>() {
            @Override
            public void onResponse(Call<List<Discount>> call, Response<List<Discount>> response) {
                descuentos = response.body();
                PayWindow.setDiscountList(descuentos);
            }

            @Override
            public void onFailure(Call<List<Discount>> call, Throwable t) {

            }
        });
    }

    public static void removeDiscount(Discount discount) {
        descuentos.remove(discount);
    }

    public static int getId_usuario_discounts() {
        return id_usuario_discounts;
    }

    public static void setId_usuario_discounts(int id_usuario_discounts) {
        DiscountsManager.id_usuario_discounts = id_usuario_discounts;
    }
}
