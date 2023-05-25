package com.example.zara_tpv.manager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.zara_tpv.adapter.ListClothesAdapterNested;
import com.example.zara_tpv.pojo.ListClothes;
import com.example.zara_tpv.pojo.Producto;

import java.util.ArrayList;
import java.util.List;

public class FilterManager {
    /**
     * Method to create filter to EditText
     *
     * @param editT
     * @param adapter
     */
    public static void setFilter(EditText editT, ListClothesAdapterNested adapter) {
        editT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setFilterReference(s.toString(), adapter);
                }
            }
        });
    }

    /**
     * Method to create filter to spinner
     *
     * @param spin
     * @param adapter
     */

    public static void setFilterS(Spinner spin, ListClothesAdapterNested adapter, boolean isColor) {
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if (isColor) {
                     setFilterColor(String.valueOf(spin.getItemAtPosition(position)), adapter);
                 } else {
                     setFilterSize(String.valueOf(spin.getItemAtPosition(position)), adapter);
                 }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private static void setFilterSize(String size, ListClothesAdapterNested adapter) {
        int talla = Integer.valueOf(size);
        List<Producto> list = new ArrayList<>();
        for (Producto item : ListManager.getCategoryClothes()) {
            if (item.getTalla() == talla) {
                list.add(item);
            }
        }
        adapter.getAdapterHorizontal().filterReference(list);
    }

    private static void setFilterColor(String color, ListClothesAdapterNested adapter) {
        List<Producto> list = new ArrayList<>();
        for (Producto item : ListManager.getCategoryClothes()) {
            if (item.getColor().equals(color)) {
                list.add(item);
            }
        }
        adapter.getAdapterHorizontal().filterReference(list);
    }

    /**
     * Method to filter the references of clothes
     *
     * @param nameClothe
     * @param adapter
     */
    private static void setFilterReference(String nameClothe, ListClothesAdapterNested adapter) {
        List<Producto> list = new ArrayList<>();
        for (Producto item : ListManager.getCategoryClothes()) {
            if (item.getDescripcion().equals(nameClothe)) {
                list.add(item);
            }
        }
        adapter.getAdapterHorizontal().filterReference(list);
    }


}
