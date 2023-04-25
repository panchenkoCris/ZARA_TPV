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
import android.widget.Toast;

import com.example.zara_tpv.R;
import com.example.zara_tpv.adapter.ListClothesAdapterNested;
import com.example.zara_tpv.manager.DialogManager;
import com.example.zara_tpv.manager.ListManager;
import com.example.zara_tpv.pojo.ListClothesNested;

import java.util.ArrayList;
import java.util.List;

public class ShopWindow extends AppCompatActivity {
    private RecyclerView recyclerViewMenu;
    private List<ListClothesNested> listNestedClothes;
    private ListClothesAdapterNested adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_window);
        setToolbar((Toolbar) findViewById(R.id.toolbar_menu));

        recyclerViewMenu = findViewById(R.id.recyclerview_menu_clothes);
        recyclerViewMenu.setHasFixedSize(true);
        recyclerViewMenu.setLayoutManager((new LinearLayoutManager(this)));

        listNestedClothes = new ArrayList<>();
        listNestedClothes.add(new ListClothesNested(ListManager.getClothes(), "Ropa"));
        listNestedClothes.add(new ListClothesNested(ListManager.getClothes(), "Ropa2"));
        listNestedClothes.add(new ListClothesNested(ListManager.getClothes(), "Ropa2"));
        listNestedClothes.add(new ListClothesNested(ListManager.getClothes(), "Ropa2"));
        listNestedClothes.add(new ListClothesNested(ListManager.getClothes(), "Ropa2"));

        linearLayoutManager = new LinearLayoutManager(ShopWindow.this, LinearLayoutManager.VERTICAL, false);

        adapter = new ListClothesAdapterNested(listNestedClothes, ShopWindow.this);
        recyclerViewMenu.setLayoutManager(linearLayoutManager);
        recyclerViewMenu.setAdapter(adapter);
    }

    private void setToolbar(Toolbar toolbar) {
        toolbar.setTitle(R.string.title_dresser);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_menu);
        toolbar.setOverflowIcon(drawable);
        setSupportActionBar(toolbar);
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
                Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_account:
                DialogManager.openDialogLogin(this);
                break;
            case R.id.action_shopping:
                startActivity(new Intent(this, ShopWindow.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
}