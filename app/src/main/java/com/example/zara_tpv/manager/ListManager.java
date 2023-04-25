package com.example.zara_tpv.manager;

import com.example.zara_tpv.pojo.ListClothes;

import java.util.ArrayList;

public class ListManager {
    private static ArrayList<ListClothes> clothes;

    public static void declareClothes() {
        clothes = new ArrayList<>();
        clothes.add(new ListClothes("blusa", "38", "2.90"));
        clothes.add(new ListClothes("blusa", "38", "2.90"));
        clothes.add(new ListClothes("blusa", "38", "2.90"));
        clothes.add(new ListClothes("blusa", "38", "2.90"));
        clothes.add(new ListClothes("blusa", "38", "2.90"));
        clothes.add(new ListClothes("blusa", "38", "2.90"));
        clothes.add(new ListClothes("blusa", "38", "2.90"));
    }

    public static ArrayList<ListClothes> getClothes() {
        declareClothes();
        return clothes;
    }
}
