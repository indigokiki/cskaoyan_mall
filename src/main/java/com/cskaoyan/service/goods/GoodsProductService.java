package com.cskaoyan.service.goods;

import com.cskaoyan.bean.goods.CskaoyanMallGoodsProduct;

import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-04 23:57
 */
public interface GoodsProductService {
    int insertProductSelective(CskaoyanMallGoodsProduct product);

    //用goodsid查products，用于goodsdetail
    List<CskaoyanMallGoodsProduct> selectProductsByGoodsid(Integer goodsid);

    int setDeletedFalseByGoodsid(Integer goodsId);

    int updateProducts(List<CskaoyanMallGoodsProduct> products);
}
