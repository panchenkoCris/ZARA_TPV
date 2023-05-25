package com.example.zara_tpv.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;

    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://dbshoptpv.000webhostapp.com/public/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
