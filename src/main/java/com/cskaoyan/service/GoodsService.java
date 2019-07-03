package com.cskaoyan.service;

import com.cskaoyan.bean.CskaoyanMallGoods;
import com.cskaoyan.util.Page;

import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-03 20:31
 */
public interface GoodsService {
    //查看+查找商品+分页
    Page<CskaoyanMallGoods> selectGoodsPageWithSnOrName(int page, int limit, String goodsSn, String name);

    //删除商品
    int deleteGoods(CskaoyanMallGoods goods);

}
