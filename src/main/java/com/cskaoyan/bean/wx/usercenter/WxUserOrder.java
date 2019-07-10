package com.cskaoyan.bean.wx.usercenter;

import java.util.List;

public class WxUserOrder {

    int actualPrice;

    List<WxGoods> goodsList;

    WxGoodsHandleOptions handleOption;

    int id;

    boolean isGroupin;

    String orderSn;

    String orderStatusText;

    public int getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }

    public List<WxGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<WxGoods> goodsList) {
        this.goodsList = goodsList;
    }

    public WxGoodsHandleOptions getHandleOption() {
        return handleOption;
    }

    public void setHandleOption(WxGoodsHandleOptions handleOption) {
        this.handleOption = handleOption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGroupin() {
        return isGroupin;
    }

    public void setGroupin(boolean groupin) {
        isGroupin = groupin;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderStatusText() {
        return orderStatusText;
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }
}
