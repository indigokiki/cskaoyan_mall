package com.cskaoyan.service;

import com.cskaoyan.bean.CskaoyanMallGoods;
import com.cskaoyan.bean.CskaoyanMallGoodsExample;
import com.cskaoyan.mapper.CskaoyanMallGoodsMapper;
import com.cskaoyan.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-03 20:32
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    CskaoyanMallGoodsMapper goodsMapper;

    @Override
    public Page<CskaoyanMallGoods> selectGoodsPage() {
        List<CskaoyanMallGoods> goodsList = goodsMapper.selectByExample(new CskaoyanMallGoodsExample());
        Page<CskaoyanMallGoods> goodsPage = new Page<>();
        goodsPage.setItems(goodsList);
        goodsPage.setTotal(goodsList.size());
        return goodsPage;
    }
}
