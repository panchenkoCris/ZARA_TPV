package com.example.zara_tpv.pojo;

import java.util.List;

public class ListClothesNested {
    private List<ListClothes> nestedList;
    private String nameCategory;
    private boolean isExpandable;

    public ListClothesNested(List<ListClothes> nestedList, String nameCategory) {
        this.nestedList = nestedList;
        this.nameCategory = nameCategory;
        this.isExpandable = false;
    }

    public List<ListClothes> getNestedList() {
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
