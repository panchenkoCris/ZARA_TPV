package com.example.zara_tpv.manager;

import android.content.Context;
import android.widget.Toast;


import com.example.zara_tpv.pojo.Cuenta;
import com.example.zara_tpv.pojo.Discount;
import com.example.zara_tpv.pojo.Usuarios;
import com.example.zara_tpv.retrofit.RetrofitInterface;
import com.example.zara_tpv.retrofit.RetrofitService;
import com.example.zara_tpv.windows.PayWindow;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuentaManager {
    public static List<Cuenta> cuentasUsuarios = new ArrayList<>();

    public static void getAllCuentasPerUser(Usuarios usuarios) {
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface cuentaInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        cuentaInterface.getAllAccountsPerUser(usuarios.getId_usuario()).enqueue(new Callback<List<Cuenta>>() {
            @Override
            public void onResponse(Call<List<Cuenta>> call, Response<List<Cuenta>> response) {
                cuentasUsuarios = response.body();
            }

            @Override
            public void onFailure(Call<List<Cuenta>> call, Throwable t) {

            }
        });
    }

    public static void actualizarDescuentoUsuario(Discount discount, Context context) {
        Cuenta cuenta = searchCuenta(discount);
        cuenta.setDescuento_activado(1);
        RetrofitService retrofitService = new RetrofitService();
        RetrofitInterface cuentaInterface = retrofitService.getRetrofit().create(RetrofitInterface.class);
        cuentaInterface.actualizarDescuentoUsuario(cuenta, cuenta.getId_cuenta()).enqueue(new Callback<Cuenta>() {
            @Override
            public void onResponse(Call<Cuenta> call, Response<Cuenta> response) {
                Toast.makeText(context, "Descuento añadido", Toast.LENGTH_SHORT).show();
                PayWindow.setTotalDiscounts(discount.getCantidad_descuento());
                DiscountsManager.removeDiscount(discount);
            }

            @Override
            public void onFailure(Call<Cuenta> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static Cuenta searchCuenta(Discount discount) {
        for(Cuenta cuenta : cuentasUsuarios) {
            if (cuenta.getId_descuento() == discount.getId()) {
                return cuenta;
            }
        }
        return null;
    }
}
