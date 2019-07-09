package com.cskaoyan.bean.mallmanage;

import com.cskaoyan.bean.CskaoyanMallBrand;
import com.cskaoyan.bean.goods.CskaoyanMallGoods;

import java.util.List;

public class Goodsdetail {

    private int userHasCollect;

    private List specificationList;

    private String shareImage;

    private List productList;

    private List issue;

    private CskaoyanMallGoods info;

    private List groupon;

    private Comment comment;

    private CskaoyanMallBrand brand;

    private List attribute;

    public int getUserHasCollect() {
        return userHasCollect;
    }

    public void setUserHasCollect(int userHasCollect) {
        this.userHasCollect = userHasCollect;
    }

    public List getSpecificationList() {
        return specificationList;
    }

    public void setSpecificationList(List specificationList) {
        this.specificationList = specificationList;
    }

    public String getShareImage() {
        return shareImage;
    }

    public void setShareImage(String shareImage) {
        this.shareImage = shareImage;
    }

    public List getProductList() {
        return productList;
    }

    public void setProductList(List productList) {
        this.productList = productList;
    }

    public List getIssue() {
        return issue;
    }

    public void setIssue(List issue) {
        this.issue = issue;
    }

    public CskaoyanMallGoods getInfo() {
        return info;
    }

    public void setInfo(CskaoyanMallGoods info) {
        this.info = info;
    }

    public List getGroupon() {
        return groupon;
    }

    public void setGroupon(List groupon) {
        this.groupon = groupon;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public CskaoyanMallBrand getBrand() {
        return brand;
    }

    public void setBrand(CskaoyanMallBrand brand) {
        this.brand = brand;
    }

    public List getAttribute() {
        return attribute;
    }

    public void setAttribute(List attribute) {
        this.attribute = attribute;
    }
}
