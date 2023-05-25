package com.example.zara_tpv.pojo;

import java.util.List;

public class ListClothesNested {
    private List<Producto> nestedList;
    private String nameCategory;
    private boolean isExpandable;

    public ListClothesNested(List<Producto> nestedList, String nameCategory) {
        this.nestedList = nestedList;
        this.nameCategory = nameCategory;
        this.isExpandable = false;
    }

    public List<Producto> getNestedList() {
        return nestedList;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
