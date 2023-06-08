package com.example.zara_tpv.manager;

import android.content.Context;
import android.widget.Toast;

import com.example.zara_tpv.pojo.Usuarios;
import com.example.zara_tpv.retrofit.RetrofitInterface;
import com.example.zara_tpv.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuariosManager {
    public static void createUsuarios(Usuarios usuarios, Context context) {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface usuarioInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        usuarioInterface.createUsuarios(usuarios).enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                DiscountsManager.setId_usuario_discounts(usuarios.getId_usuario());
                Toast.makeText(context, "Usuario creado", Toast.LENGTH_SHORT).show();
                TicketManager.updateTicket(usuarios);
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {

            }
        });
    }
}
