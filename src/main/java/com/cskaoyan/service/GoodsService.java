package com.cskaoyan.service;

import com.cskaoyan.bean.CskaoyanMallGoods;
import com.cskaoyan.util.Page;

import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-03 20:31
 */
public interface GoodsService {
    Page<CskaoyanMallGoods> selectGoodsPage();
}
