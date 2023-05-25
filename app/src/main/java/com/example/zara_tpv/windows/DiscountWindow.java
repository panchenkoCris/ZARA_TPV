package com.example.zara_tpv.windows;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.example.zara_tpv.R;
import com.example.zara_tpv.adapter.DiscountAdapter;
import com.example.zara_tpv.manager.DialogManager;
import com.example.zara_tpv.pojo.Discount;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.pojo.ProductoInterface;
import com.example.zara_tpv.retrofit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscountWindow extends AppCompatActivity{
    private List<Discount> discounts;
    private DiscountAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_window);

        RetrofitService retrofitService = new RetrofitService();
        ProductoInterface productoInterface = retrofitService.getRetrofit().create(ProductoInterface.class);
        productoInterface.getAllProductos().enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {

            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

            }
        });
    }


    private void setRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setAdapter() {
        adapter = new DiscountAdapter(discounts);
    }
}