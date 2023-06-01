package com.example.zara_tpv.windows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zara_tpv.R;
import com.example.zara_tpv.adapter.ListProductsAdapter;
import com.example.zara_tpv.adapter.ListProductsShopAdapter;
import com.example.zara_tpv.manager.DialogManager;
import com.example.zara_tpv.manager.FilterManager;
import com.example.zara_tpv.manager.ProductsManager;
import com.example.zara_tpv.pojo.Producto;

import java.util.ArrayList;
import java.util.List;

public class ShopWindow extends AppCompatActivity {
    private static List<Producto> clothes = new ArrayList<>();
    private ListProductsShopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_window);
        setToolbar((Toolbar) findViewById(R.id.toolbar_menu));

        ProductsManager pm = new ProductsManager(this);
        setAdapter();
        setRecyclerView((RecyclerView) findViewById(R.id.recyclerview_menu_clothes));
        setSearchView((EditText) findViewById(R.id.searchView_filter_name));
        setSpinner((Spinner) findViewById(R.id.spinner_filter_size));
        setSpinner((Spinner) findViewById(R.id.spinner_filter_color));
        pm.getAllProducts(adapter);
    }

    private void setSearchView(EditText searchView) {
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    FilterManager.setFilterReference(s.toString(), adapter);
                }
            }
        });
    }

    private void setSpinner(Spinner spin) {
        boolean isSpinnerColor = (spin.getId() == R.id.spinner_filter_color);

        setArrayAdapter(isSpinnerColor, spin);
        FilterManager.setFilterS(spin, adapter, isSpinnerColor);
    }

    private void setArrayAdapter(boolean isSpinnerColor, Spinner spinner) {
        ArrayAdapter<CharSequence> adapterS;

        adapterS = (isSpinnerColor) ?
                ArrayAdapter.createFromResource(this,
                        R.array.attributes_colors,
                        android.R.layout.simple_spinner_item) :
                ArrayAdapter.createFromResource(this,
                        R.array.attributes_sizes,
                        android.R.layout.simple_spinner_item);

        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterS);
    }

    private void setAdapter() {
        adapter =  new ListProductsShopAdapter(clothes, true, new ListProductsShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto item) {
                DialogManager.openDialogClotheResumeShop(ShopWindow.this, adapter, item, clothes.indexOf(item));
            }
        });
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);
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
                startActivity(new Intent(this, ResumeShopWindow.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
}