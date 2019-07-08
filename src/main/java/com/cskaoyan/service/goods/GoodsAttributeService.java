package com.cskaoyan.service.goods;

import com.cskaoyan.bean.goods.CskaoyanMallGoodsAttribute;

import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-04 23:50
 */
public interface GoodsAttributeService {
    //传来一个attribute，每个只有属性attribute\value\goodsid，要自动分配id、自己添加addtime
    int insertAttributeSelective(CskaoyanMallGoodsAttribute attribute);

    //用goodsid查attributes,用于goodsdetail
    List<CskaoyanMallGoodsAttribute> selectAttributesByGoodsid(Integer goodsid);


    int setDeletedFalseByGoodsid(Integer goodsId);

    int updateAttributes(List<CskaoyanMallGoodsAttribute> attributes);
}
