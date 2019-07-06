package com.cskaoyan.service.goods;

import com.cskaoyan.bean.goods.CskaoyanMallGoodsSpecification;

import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-04 23:58
 */
public interface GoodsSpecificationService {
    int insertSpecificationSelective(CskaoyanMallGoodsSpecification specification);

    //用goodsID查specifications，用于goodsdetail
    List<CskaoyanMallGoodsSpecification> selectSpecificationsByGoodsid(Integer goodsid);

    int setDeletedFalseByGoodsid(Integer goodsId);

    int updateSpecifications(List<CskaoyanMallGoodsSpecification> specifications);
}
