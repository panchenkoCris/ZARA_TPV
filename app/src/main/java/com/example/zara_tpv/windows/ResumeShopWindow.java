package com.example.zara_tpv.windows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zara_tpv.R;
import com.example.zara_tpv.adapter.ListProductsAdapter;
import com.example.zara_tpv.manager.DialogManager;
import com.example.zara_tpv.manager.ProductsManager;
import com.example.zara_tpv.manager.TicketManager;
import com.example.zara_tpv.pojo.Producto;

import java.util.ArrayList;
import java.util.List;


public class ResumeShopWindow extends AppCompatActivity implements View.OnClickListener {
    private static List<Producto> clothes = new ArrayList<>();
    private static ListProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_shop_window);

        ProductsManager pm = new ProductsManager(this);
        clothes = new ArrayList<>();

        setAdapter();
        clothes = TicketManager.getProductsPerTicket(adapter);

        setRecyclerView((RecyclerView) findViewById(R.id.recyclerView_clothes));
        setToolbar((Toolbar) findViewById(R.id.toolbar_menu));
        setButtons((Button) findViewById(R.id.button_openScannerCode),
                (Button) findViewById(R.id.button_openDialogCode),
                (Button) findViewById(R.id.button_payClothes));
    }

    private void setAdapter() {
        adapter =  new ListProductsAdapter(clothes, new ListProductsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto clothe) {
                DialogManager.openDialogClotheResumeCart(ResumeShopWindow.this, adapter, clothe, clothes.indexOf(clothe));
            }
        });
    }

    private void setButtons(Button openDialog, Button openScanner, Button payCart) {
        openDialog.setOnClickListener(this);
        openScanner.setOnClickListener(this);
        payCart.setOnClickListener(this);
    }

    private void setToolbar(Toolbar toolbar) {
        toolbar.setTitle(R.string.title_shop);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_menu);
        toolbar.setOverflowIcon(drawable);
        toolbar.setContentInsetsAbsolute(40,0);
        setSupportActionBar(toolbar);
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general_options_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dresser:
                startActivity(new Intent(this, ShopWindow.class));
                break;
            case R.id.action_account:
                DialogManager.openDialogLogin(this);
                break;
            case R.id.action_shopping:
                Toast.makeText(this, this.getString(R.string.already_purchase), Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        switch (b.getId()) {
            case R.id.button_openDialogCode:
                DialogManager.openDialogCode(this);
                break;
            case R.id.button_openScannerCode:
                startActivity(new Intent(this, ScannerWindow.class));
                break;
            case R.id.button_payClothes:
                if(!TicketManager.getProductos().isEmpty()) {
                    startActivity(new Intent(this, PayWindow.class));
                } else {
                    DialogManager.openDialogError(this, this.getString(R.string.products_cart));
                }
                break;
        }
    }

    public static ListProductsAdapter getAdapter() {
        return adapter;
    }
}
