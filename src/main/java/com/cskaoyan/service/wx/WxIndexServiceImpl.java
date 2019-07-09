package com.cskaoyan.service.wx;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsExample;
import com.cskaoyan.bean.mallmanage.Floor;
import com.cskaoyan.bean.mallmanage.Groupon;
import com.cskaoyan.bean.mallmanage.Topic;
import com.cskaoyan.bean.wx.IndexList;
import com.cskaoyan.mapper.*;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsMapper;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    CskaoyanMallGrouponRulesMapper rulesMapper;

    @Autowired
    CskaoyanMallTopicMapper topicMapper;

    @Autowired
    CskaoyanMallKeywordMapper keywordMapper;

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
        List<CskaoyanMallBrand> banners = brandList.subList(0, 4);

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
        List<Floor> floorGoodsList = cskaoyanMallCategoryMapper.getIndex();

        //grouponList
        List<Groupon> grouponList = rulesMapper.indexGroupon();

        //hotGoodsList
        CskaoyanMallGoodsExample cskaoyanMallGoodsExample = new CskaoyanMallGoodsExample();
        CskaoyanMallGoodsExample.Criteria criteria4 = cskaoyanMallGoodsExample.createCriteria();
        criteria4.andDeletedNotEqualTo(true).andIsHotEqualTo(true);
        List<CskaoyanMallGoods> hotGoods = cskaoyanMallGoodsMapper.selectByExample(cskaoyanMallGoodsExample);
        List<CskaoyanMallGoods> hotGoodsList = hotGoods.subList(0, 4);

        //newGoodsList
        cskaoyanMallGoodsExample.clear();
        criteria4.andIsNewEqualTo(true).andDeletedNotEqualTo(true);
        List<CskaoyanMallGoods> newGoods = cskaoyanMallGoodsMapper.selectByExample(cskaoyanMallGoodsExample);
        List<CskaoyanMallGoods> newGoodsList = newGoods.subList(0, 4);
        //topicList
        List<Topic> topicList = topicMapper.indexTopic();

        //indexlist
        IndexList indexList = new IndexList();
        indexList.setBanner(banner);
        indexList.setBrandList(banners);
        indexList.setChannel(channel);
        indexList.setCouponList(couponList);
        indexList.setHotGoodsList(hotGoodsList);
        indexList.setNewGoodsList(newGoodsList);
        indexList.setFloorGoodsList(floorGoodsList);
        indexList.setGrouponList(grouponList);
        indexList.setTopicList(topicList);
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setData(indexList);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }

    @Override
    public ResponseVo getGoodsCount() {
        CskaoyanMallGoodsExample goodsExample = new CskaoyanMallGoodsExample();
        int count = (int) cskaoyanMallGoodsMapper.countByExample(goodsExample);
        Map<Object, Object> map = new HashMap<>();
        map.put("goodsCount",count);
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setData(map);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }

    @Override
    public ResponseVo searchIndex() {
        CskaoyanMallKeywordExample keywordExample = new CskaoyanMallKeywordExample();
        CskaoyanMallKeywordExample.Criteria criteria = keywordExample.createCriteria();
        criteria.andIsHotEqualTo(true);
        List<CskaoyanMallKeyword> hotGoods = keywordMapper.selectByExample(keywordExample);
        CskaoyanMallKeywordExample example = new CskaoyanMallKeywordExample();
        CskaoyanMallKeywordExample.Criteria criteria1 = example.createCriteria();
        criteria1.andIsDefaultEqualTo(true);
        List<CskaoyanMallKeyword> defaultGoods = keywordMapper.selectByExample(example);
        return null;
    }
}
