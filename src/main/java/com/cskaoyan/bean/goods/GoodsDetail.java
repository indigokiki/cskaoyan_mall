package com.cskaoyan.bean.goods;

import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-05 11:09
 */
public class GoodsDetail {
    List<CskaoyanMallGoodsAttribute> attributes;
    int[] categoryids;
    CskaoyanMallGoods goods;
    List<CskaoyanMallGoodsProduct> products;
    List<CskaoyanMallGoodsSpecification> specifications;

    public GoodsDetail() {
    }

    public GoodsDetail(List<CskaoyanMallGoodsAttribute> attributes, int[] categoryids,
                       CskaoyanMallGoods goods, List<CskaoyanMallGoodsProduct> products,
                       List<CskaoyanMallGoodsSpecification> specifications) {
        this.attributes = attributes;
        this.categoryids = categoryids;
        this.goods = goods;
        this.products = products;
        this.specifications = specifications;
    }

    public List<CskaoyanMallGoodsAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CskaoyanMallGoodsAttribute> attributes) {
        this.attributes = attributes;
    }

    public int[] getCategoryids() {
        return categoryids;
    }

    public void setCategoryids(int[] categoryids) {
        this.categoryids = categoryids;
    }

    public CskaoyanMallGoods getGoods() {
        return goods;
    }

    public void setGoods(CskaoyanMallGoods goods) {
        this.goods = goods;
    }

    public List<CskaoyanMallGoodsProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CskaoyanMallGoodsProduct> products) {
        this.products = products;
    }

    public List<CskaoyanMallGoodsSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<CskaoyanMallGoodsSpecification> specifications) {
        this.specifications = specifications;
    }
}
