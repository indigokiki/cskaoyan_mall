package com.cskaoyan.service;

import com.cskaoyan.bean.CskaoyanMallGoods;
import com.cskaoyan.bean.CskaoyanMallGoodsExample;
import com.cskaoyan.mapper.CskaoyanMallGoodsMapper;
import com.cskaoyan.util.Page;
import com.github.pagehelper.PageHelper;
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
    public Page<CskaoyanMallGoods> selectGoodsPageWithSnOrName(int page, int limit, String goodsSn, String name) {
        Page<CskaoyanMallGoods> goodsPage = new Page<>();

        CskaoyanMallGoodsExample goodsExample = new CskaoyanMallGoodsExample();
        CskaoyanMallGoodsExample.Criteria criteria = goodsExample.createCriteria();
        if (goodsSn != null && goodsSn != ""){
            criteria.andGoodsSnEqualTo(goodsSn);
        }
        if (name != null && name != ""){
            name = "%" + name + "%";
            criteria.andNameLike(name);
        }

        PageHelper.startPage(page, limit);
        List<CskaoyanMallGoods> goodsList = goodsMapper.selectByExample(goodsExample);
        goodsPage.setItems(goodsList);

        int count = (int) goodsMapper.countByExample(goodsExample);
        goodsPage.setTotal(count);

        return goodsPage;
    }

    @Override
    public int deleteGoods(CskaoyanMallGoods goods) {
        int i = goodsMapper.deleteByPrimaryKey(goods.getId());
        return i;
    }
}
