package com.example.zara_tpv.windows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zara_tpv.R;
import com.example.zara_tpv.adapter.ListClothesAdapter;
import com.example.zara_tpv.pojo.ListClothes;

import java.util.ArrayList;
import java.util.List;


public class ResumeShopWindow extends AppCompatActivity implements View.OnClickListener {
    private List<ListClothes> clothes;
    private ListClothesAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_shop_window);

        clothes = new ArrayList<>();
        clothes.add(new ListClothes("blusa", "38", "2.90"));
        clothes.add(new ListClothes("blusa", "38", "2.90"));
        clothes.add(new ListClothes("blusa", "38", "2.90"));
        clothes.add(new ListClothes("blusa", "38", "2.90"));
        clothes.add(new ListClothes("blusa", "38", "2.90"));
        clothes.add(new ListClothes("blusa", "38", "2.90"));
        clothes.add(new ListClothes("blusa", "38", "2.90"));


        recyclerView = findViewById(R.id.recyclerView_clothes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListClothesAdapter(clothes, new ListClothesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListClothes clothe) {
                openDialogClothe();
            }
        }));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_menu);
        toolbar.setOverflowIcon(drawable);
        setSupportActionBar(toolbar);

        Button openScanner = (Button) findViewById(R.id.button_openScannerCode);
        Button openDialog = (Button) findViewById(R.id.button_openDialogCode);

        openDialog.setOnClickListener(this);
        openScanner.setOnClickListener(this);

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
                Toast.makeText(this, "Dresser", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_account:
                Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_shopping:
                Toast.makeText(this, "Shopping", Toast.LENGTH_SHORT).show();
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
                openDialogCode();
                break;
            case R.id.button_openScannerCode:
                startActivity(new Intent(this, ScannerWindow.class));
                break;
        }
    }

    private void openDialogCode() {
        final Dialog dialog = new Dialog(ResumeShopWindow.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_code);

        final EditText numBarcode = dialog.findViewById(R.id.editText_num_barcode);
        final Button buttonInsertBarcode = dialog.findViewById(R.id.button_insert_barcode);

        buttonInsertBarcode.setOnClickListener((v) -> {
            //int barcode = Integer.parseInt(numBarcode.getText().toString());
            dialog.dismiss();
        });
        dialog.show();
    }

    private void openDialogClothe() {
        final Dialog dialog = new Dialog(ResumeShopWindow.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_clothe);

        final TextView textName = dialog.findViewById(R.id.textView_name_clothe);
        final Button buttonModifyClothe = dialog.findViewById(R.id.button_modify_clothe);

        buttonModifyClothe.setOnClickListener((v) -> {
            //int barcode = Integer.parseInt(numBarcode.getText().toString());
            dialog.dismiss();
        });
        dialog.show();
    }
}
