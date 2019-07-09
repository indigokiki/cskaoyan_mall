package com.cskaoyan.bean.mallmanage;

import com.cskaoyan.bean.CskaoyanMallCategory;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SearchGoods {

    private int count;

    private List<CskaoyanMallCategory> filterCategoryList;

    private List<FloorGood> goodsList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CskaoyanMallCategory> getFilterCategoryList() {
        return filterCategoryList;
    }

    public void setFilterCategoryList(List<CskaoyanMallCategory> filterCategoryList) {
        this.filterCategoryList = filterCategoryList;
    }

    public List<FloorGood> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<FloorGood> goodsList) {
        this.goodsList = goodsList;
    }
}
