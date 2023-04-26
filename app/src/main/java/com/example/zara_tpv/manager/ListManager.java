package com.example.zara_tpv.manager;

import android.content.Context;

import com.example.zara_tpv.pojo.ListClothes;
import com.example.zara_tpv.pojo.ListClothesNested;

import java.util.ArrayList;
import java.util.List;

public class ListManager {
    private static ArrayList<ListClothes> clothes;

    public static void declareClothes() {
        clothes = new ArrayList<>();
        clothes.add(new ListClothes("camiseta", "39", "2.90", "Rosa"));
        clothes.add(new ListClothes("pantalon", "34", "2.90", "Negro"));
        clothes.add(new ListClothes("mocasinos", "38", "2.90", "Rosa"));
        clothes.add(new ListClothes("blusa", "40", "2.90", "Negro"));
        clothes.add(new ListClothes("chaqueta", "38", "2.90", "Rosa"));
        clothes.add(new ListClothes("collar", "38", "2.90", "Negro"));
        clothes.add(new ListClothes("cinturon", "37", "2.90", "Rosa"));
    }

    public static ArrayList<ListClothes> getCategoryClothes() {
        declareClothes();
        return clothes;
    }

    public static List<ListClothesNested> getAllClothes() {
        List<ListClothesNested> nestedList = new ArrayList<>();
        nestedList.add(new ListClothesNested(getCategoryClothes(), "Ropa"));
        nestedList.add(new ListClothesNested(getCategoryClothes(), "Ropa"));
        nestedList.add(new ListClothesNested(getCategoryClothes(), "Ropa"));
        nestedList.add(new ListClothesNested(getCategoryClothes(), "Ropa"));
        nestedList.add(new ListClothesNested(getCategoryClothes(), "Ropa"));
        return nestedList;
    }
}
