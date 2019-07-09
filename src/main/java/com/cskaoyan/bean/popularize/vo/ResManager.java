package com.cskaoyan.bean.popularize.vo;

import java.util.List;

public class ResManager<T> {
    private Integer total;
    private List<T> items;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
