package com.cskaoyan.bean.goods;

import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-04 15:33
 */
public class GoodsAdd {
    List<CskaoyanMallGoodsAttribute> attributes;
    CskaoyanMallGoods goods;
    List<CskaoyanMallGoodsProduct> products;
    List<CskaoyanMallGoodsSpecification> specifications;

    public List<CskaoyanMallGoodsAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CskaoyanMallGoodsAttribute> attributes) {
        this.attributes = attributes;
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
