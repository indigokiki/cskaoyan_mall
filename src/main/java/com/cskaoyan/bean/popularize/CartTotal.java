package com.cskaoyan.bean.popularize;

public class CartTotal {
    private Integer checkedGoodsCount;
    private Double checkedGoodsAmount;
    private Integer goodsCount;
    private Double goodsAmount;

    public Integer getCheckedGoodsCount() {
        return checkedGoodsCount;
    }

    public void setCheckedGoodsCount(Integer checkedGoodsCount) {
        this.checkedGoodsCount = checkedGoodsCount;
    }

    public Double getCheckedGoodsAmount() {
        return checkedGoodsAmount;
    }

    public void setCheckedGoodsAmount(Double checkedGoodsAmount) {
        this.checkedGoodsAmount = checkedGoodsAmount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Double getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Double goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }
}
