package com.cskaoyan.bean.mallmanage;

import java.util.List;

public class Floor {

    private String id;

    private String name;

    private List<FloorGood> goodsList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FloorGood> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<FloorGood> goodsList) {
        this.goodsList = goodsList;
    }
}
