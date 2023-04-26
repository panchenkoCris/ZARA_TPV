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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zara_tpv.R;
import com.example.zara_tpv.adapter.ListClothesAdapterNested;
import com.example.zara_tpv.manager.DialogManager;
import com.example.zara_tpv.manager.FilterManager;
import com.example.zara_tpv.manager.ListManager;
import com.example.zara_tpv.pojo.ListClothesNested;

import java.util.List;

public class ShopWindow extends AppCompatActivity {
    private RecyclerView recyclerViewMenu;
    private List<ListClothesNested> listNestedClothes;
    private ListClothesAdapterNested adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_window);
        setToolbar((Toolbar) findViewById(R.id.toolbar_menu));

        setRecyclerView((RecyclerView) findViewById(R.id.recyclerview_menu_clothes));
        listNestedClothes = setListNested();

        adapter = setAdapter();
        setSpinner((Spinner) findViewById(R.id.spinner_filter_size));
        setSpinner((Spinner) findViewById(R.id.spinner_filter_color));
        setEditTextSearch((EditText) findViewById(R.id.editText_filter_name));
    }

    private void setEditTextSearch(EditText editT) {
        FilterManager.setFilter(editT, adapter);
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

    private List<ListClothesNested> setListNested() {
        List<ListClothesNested> listNestedClothes = ListManager.getAllClothes();
        return listNestedClothes;
    }

    private ListClothesAdapterNested setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopWindow.this, LinearLayoutManager.VERTICAL, false);
        ListClothesAdapterNested adapter = new ListClothesAdapterNested(listNestedClothes, ShopWindow.this);
        recyclerViewMenu.setLayoutManager(linearLayoutManager);
        recyclerViewMenu.setAdapter(adapter);
        return adapter;
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        recyclerViewMenu = recyclerView;
        recyclerViewMenu.setHasFixedSize(true);
        recyclerViewMenu.setLayoutManager((new LinearLayoutManager(this)));
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