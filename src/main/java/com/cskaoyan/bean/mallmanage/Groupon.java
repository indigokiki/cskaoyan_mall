package com.cskaoyan.bean.mallmanage;

import java.util.List;

public class Groupon {

    private int groupon_member;

    private int groupon_price;

    private GroupGood goods;

    public int getGroupon_member() {
        return groupon_member;
    }

    public void setGroupon_member(int groupon_member) {
        this.groupon_member = groupon_member;
    }

    public int getGroupon_price() {
        return groupon_price;
    }

    public void setGroupon_price(int groupon_price) {
        this.groupon_price = groupon_price;
    }

    public GroupGood getGoods() {
        return goods;
    }

    public void setGoods(GroupGood goods) {
        this.goods = goods;
    }
}
