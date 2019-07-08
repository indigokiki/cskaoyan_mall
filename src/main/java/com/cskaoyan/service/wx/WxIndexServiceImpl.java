package com.cskaoyan.service.wx;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsExample;
import com.cskaoyan.bean.wx.IndexList;
import com.cskaoyan.mapper.*;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsMapper;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxIndexServiceImpl implements WxIndexService {

    @Autowired
    CskaoyanMallAdMapper cskaoyanMallAdMapper;

    @Autowired
    CskaoyanMallBrandMapper cskaoyanMallBrandMapper;

    @Autowired
    CskaoyanMallCategoryMapper cskaoyanMallCategoryMapper;

    @Autowired
    CskaoyanMallCouponMapper cskaoyanMallCouponMapper;

    @Autowired
    CskaoyanMallGoodsMapper cskaoyanMallGoodsMapper;

    @Override
    public ResponseVo getIndex() {
        //banner
        CskaoyanMallAdExample cskaoyanMallAdExample = new CskaoyanMallAdExample();
        CskaoyanMallAdExample.Criteria criteria = cskaoyanMallAdExample.createCriteria();
        criteria.andDeletedNotEqualTo(true);
        List<CskaoyanMallAd> banner = cskaoyanMallAdMapper.selectByExample(cskaoyanMallAdExample);

        //brandList
        CskaoyanMallBrandExample cskaoyanMallBrandExample = new CskaoyanMallBrandExample();
        CskaoyanMallBrandExample.Criteria criteria1 = cskaoyanMallBrandExample.createCriteria();
        criteria1.andDeletedNotEqualTo(true);
        List<CskaoyanMallBrand> brandList = cskaoyanMallBrandMapper.selectByExample(cskaoyanMallBrandExample);

        //channel
        CskaoyanMallCategoryExample cskaoyanMallCategoryExample = new CskaoyanMallCategoryExample();
        CskaoyanMallCategoryExample.Criteria criteria2 = cskaoyanMallCategoryExample.createCriteria();
        criteria2.andDeletedNotEqualTo(true).andLevelEqualTo("L1");
        List<CskaoyanMallCategory> channel = cskaoyanMallCategoryMapper.selectByExample(cskaoyanMallCategoryExample);

        //couponList
        CskaoyanMallCouponExample cskaoyanMallCouponExample = new CskaoyanMallCouponExample();
        CskaoyanMallCouponExample.Criteria criteria3 = cskaoyanMallCouponExample.createCriteria();
        criteria3.andDeletedNotEqualTo(true);
        List<CskaoyanMallCoupon> couponList = cskaoyanMallCouponMapper.selectByExample(cskaoyanMallCouponExample);

        //floorGoodsList
        //grouponList

        //hotGoodsList
        CskaoyanMallGoodsExample cskaoyanMallGoodsExample = new CskaoyanMallGoodsExample();
        CskaoyanMallGoodsExample.Criteria criteria4 = cskaoyanMallGoodsExample.createCriteria();
        criteria4.andDeletedNotEqualTo(true).andIsHotEqualTo(true);
        List<CskaoyanMallGoods> hotGoodsList = cskaoyanMallGoodsMapper.selectByExample(cskaoyanMallGoodsExample);

        //newGoodsList
        cskaoyanMallGoodsExample.clear();
        criteria4.andIsNewEqualTo(true).andDeletedNotEqualTo(true);
        List<CskaoyanMallGoods> newGoodsList = cskaoyanMallGoodsMapper.selectByExample(cskaoyanMallGoodsExample);

        //topicList

        //indexlist
        IndexList indexList = new IndexList();
        indexList.setBanner(banner);
        indexList.setBrandList(brandList);
        indexList.setChannel(channel);
        indexList.setCouponList(couponList);
        indexList.setHotGoodsList(hotGoodsList);
        indexList.setNewGoodsList(newGoodsList);
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setData(indexList);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }
}
