package com.cskaoyan.bean.wx;

import com.cskaoyan.bean.CskaoyanMallAd;
import com.cskaoyan.bean.CskaoyanMallBrand;
import com.cskaoyan.bean.CskaoyanMallCategory;
import com.cskaoyan.bean.CskaoyanMallCoupon;
import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.bean.mallmanage.Floor;
import com.cskaoyan.bean.mallmanage.Groupon;
import com.cskaoyan.bean.mallmanage.Topic;

import java.util.List;

public class IndexList {

    List<CskaoyanMallAd> banner;

    List<CskaoyanMallBrand> brandList;

    List<CskaoyanMallCategory> channel;

    List<CskaoyanMallCoupon> couponList;

    List<Floor> floorGoodsList;

    List<Groupon> grouponList;

    List<CskaoyanMallGoods> hotGoodsList;

    List<CskaoyanMallGoods> newGoodsList;

    List<Topic> topicList;

    public List<CskaoyanMallAd> getBanner() {
        return banner;
    }

    public void setBanner(List<CskaoyanMallAd> banner) {
        this.banner = banner;
    }

    public List<CskaoyanMallBrand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<CskaoyanMallBrand> brandList) {
        this.brandList = brandList;
    }

    public List<CskaoyanMallCategory> getChannel() {
        return channel;
    }

    public void setChannel(List<CskaoyanMallCategory> channel) {
        this.channel = channel;
    }

    public List<CskaoyanMallCoupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CskaoyanMallCoupon> couponList) {
        this.couponList = couponList;
    }

    public List<Floor> getFloorGoodsList() {
        return floorGoodsList;
    }

    public void setFloorGoodsList(List<Floor> floorGoodsList) {
        this.floorGoodsList = floorGoodsList;
    }

    public List<Groupon> getGrouponList() {
        return grouponList;
    }

    public void setGrouponList(List<Groupon> grouponList) {
        this.grouponList = grouponList;
    }

    public List<CskaoyanMallGoods> getHotGoodsList() {
        return hotGoodsList;
    }

    public void setHotGoodsList(List<CskaoyanMallGoods> hotGoodsList) {
        this.hotGoodsList = hotGoodsList;
    }

    public List<CskaoyanMallGoods> getNewGoodsList() {
        return newGoodsList;
    }

    public void setNewGoodsList(List<CskaoyanMallGoods> newGoodsList) {
        this.newGoodsList = newGoodsList;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }
}
