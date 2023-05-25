package com.example.zara_tpv.manager;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class TaskGetAllProductos implements Runnable{
    private RequestQueue queue;
    private Context context;
    private final String URL = "https://dbshoptpv.000webhostapp.com/public/producto/100000000";

    public TaskGetAllProductos(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public void run() {

    }

    public void iniciar() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
