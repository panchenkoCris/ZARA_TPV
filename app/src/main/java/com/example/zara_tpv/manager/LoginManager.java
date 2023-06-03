package com.example.zara_tpv.manager;

import android.content.Context;
import android.widget.Toast;

import com.example.zara_tpv.pojo.Autentificacion;
import com.example.zara_tpv.pojo.Discount;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.pojo.Usuarios;
import com.example.zara_tpv.retrofit.RetrofitInterface;
import com.example.zara_tpv.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginManager {
    public static void accreditAccount(String userEmail, String password, Context context) {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface productoInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        productoInterface.authenticate(userEmail, password).enqueue(new Callback<Autentificacion>() {
            @Override
            public void onResponse(Call<Autentificacion> call, Response<Autentificacion> response) {
                Autentificacion arra = response.body();
                if(arra.getStatus() == 1) {
                    DiscountsManager dm = new DiscountsManager();
                    dm.getAllDiscounts(arra.getUsuario().getId_usuario(), context);
                }
                Toast.makeText(context, arra.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Autentificacion> call, Throwable t) {
                DialogManager.openDialogError(context, "No existe el usuario insertado");
            }
        });
    }
}
