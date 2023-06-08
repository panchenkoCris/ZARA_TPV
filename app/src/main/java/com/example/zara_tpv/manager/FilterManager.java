package com.example.zara_tpv.manager;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.zara_tpv.adapter.ListProductsShopAdapter;
import com.example.zara_tpv.pojo.Producto;

import java.util.ArrayList;
import java.util.List;

public class FilterManager {
    private static List<Producto> listModified = new ArrayList<>();
    /**
     * Method to create filter to spinner
     *  @param spin
     * @param adapter
     */

    public static void setFilterS(Spinner spin, ListProductsShopAdapter adapter, boolean isColor) {
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if (isColor) {
                     setFilterColor(String.valueOf(spin.getItemAtPosition(position)), adapter);
                 } else {
                     if(!spin.getItemAtPosition(position).equals("Talla") || !spin.getItemAtPosition(position).equals("Size")) {
                         setFilterSize(String.valueOf(spin.getItemAtPosition(position)), adapter);
                     }
                 }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private static void setFilterSize(String size, ListProductsShopAdapter adapter) {
        int talla = 0;
        if(!size.equals("Talla") && !size.equals("Size")) {
            if(!size.equals("Talla única") && !size.equals("Unique size")) {
                talla = Integer.valueOf(size);
            }

            List<Producto> list = new ArrayList<>();
            if (!listModified.isEmpty()) {
                for (Producto item : listModified) {
                    if (item.getTalla() == talla) {
                        list.add(item);
                    }
                }
            } else {
                for (Producto item : ProductsManager.getClothes()) {
                    if (item.getTalla() == talla) {
                        list.add(item);
                    }
                }
            }
            listModified = list;
            adapter.setFilteredList(list);
        } else {
            adapter.setFilteredList(ListProductsShopAdapter.getOriginalData());
        }
    }

    private static void setFilterColor(String color, ListProductsShopAdapter adapter) {
        List<Producto> list = new ArrayList<>();
        if(!color.equals("Color")) {
            if (!listModified.isEmpty()) {
                for (Producto item : listModified) {
                    if (item.getColor().equals(color)) {
                        list.add(item);
                    }
                }
            } else {
                for (Producto item : ProductsManager.getClothes()) {
                    if (item.getColor().equals(color)) {
                        list.add(item);
                    }
                }
            }
            listModified = list;
            adapter.setFilteredList(list);
        } else {
            adapter.setFilteredList(ListProductsShopAdapter.getOriginalData());
        }
    }

    /**
     * Method to filter the references of clothes
     *  @param nameClothe
     * @param adapter
     */
    public static void setFilterReference(String nameClothe, ListProductsShopAdapter adapter) {
        List<Producto> listFiltered = new ArrayList<>();
        for (Producto item : ProductsManager.getClothes()) {
            if (item.getCb_producto() == Integer.valueOf(nameClothe)) {
                listFiltered.add(item);
            }
        }

        if(!listFiltered.isEmpty()) {
            adapter.setFilteredList(listFiltered);
        }
    }


    public static void setFilterReferenceAdmin(String nameClothe, ListProductsShopAdapter adapter) {
        List<Producto> listFiltered = new ArrayList<>();
        for (Producto item : ProductsManager.getClothesAdmin()) {
            if (item.getCb_producto() == Integer.valueOf(nameClothe)) {
                listFiltered.add(item);
            }
        }

        if(!listFiltered.isEmpty()) {
            adapter.setFilteredList(listFiltered);
        }
    }
}
