package com.cskaoyan.service.wx;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.goods.*;
import com.cskaoyan.bean.mallmanage.*;
import com.cskaoyan.bean.wx.IndexList;
import com.cskaoyan.mapper.*;

import com.cskaoyan.mapper.goods.*;

import com.cskaoyan.mapper.goods.CskaoyanMallGoodsMapper;
import com.cskaoyan.util.DateAddDayUtil;

import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.wxutil.UserTokenManager;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

    @Autowired
    CskaoyanMallSearchHistoryMapper historyMapper;

    @Autowired
    CskaoyanMallGoodsAttributeMapper attributeMapper;

    @Autowired
    CskaoyanMallCommentMapper commentMapper;

    @Autowired
    CskaoyanMallIssueMapper issueMapper;

    @Autowired
    CskaoyanMallGoodsProductMapper productMapper;

    @Autowired
    CskaoyanMallGoodsSpecificationMapper specificationMapper;

    @Autowired
    CskaoyanMallCollectMapper collectMapper;

    @Autowired
    CskaoyanMallFootprintMapper footprintMapper;

    @Autowired
    CskaoyanMallCouponUserMapper couponUserMapper;

    @Autowired
    CskaoyanMallBrandMapper brandMapper;


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
    public ResponseVo searchIndex(HttpServletRequest request) {
        CskaoyanMallKeywordExample keywordExample = new CskaoyanMallKeywordExample();
        CskaoyanMallKeywordExample.Criteria criteria = keywordExample.createCriteria();
        criteria.andIsHotEqualTo(true);
        List<CskaoyanMallKeyword> hotGoods = keywordMapper.selectByExample(keywordExample);
        CskaoyanMallKeywordExample example = new CskaoyanMallKeywordExample();
        CskaoyanMallKeywordExample.Criteria criteria1 = example.createCriteria();
        criteria1.andIsDefaultEqualTo(true);
        List<CskaoyanMallKeyword> defaultGoods = keywordMapper.selectByExample(example);
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        List<History> historyKeywordList = null;
        if(userId != null){
            historyKeywordList = historyMapper.searchHistory(userId.toString());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("defaultKeyword",defaultGoods.get(0));
        map.put("historyKeywordList",historyKeywordList);
        map.put("hotKeywordList",hotGoods);
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setData(map);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }

    @Override
    public ResponseVo searchHelper(String keyword) {
        keyword = "%" + keyword + "%";
        List<History> helper = keywordMapper.helper(keyword);
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setData(helper);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }

    @Override
    public ResponseVo goodslist(String keyword, int page, int size, String sort, String order, String categoryId,
                                String brandId,boolean isHot,boolean isNew,HttpServletRequest request) {
        CskaoyanMallGoodsExample goodsExample = new CskaoyanMallGoodsExample();
        CskaoyanMallGoodsExample.Criteria criteria = goodsExample.createCriteria();
        String s = null;
        if(keyword != null){
            s = keyword;
            keyword = "%" + keyword + "%";
            criteria.andNameLike(keyword);
        }
        if(categoryId != null && Integer.parseInt(categoryId) != 0){
            criteria.andCategoryIdEqualTo(Integer.parseInt(categoryId));
        }
        if(brandId != null){
            criteria.andBrandIdEqualTo(Integer.parseInt(brandId));
        }
        if(isHot == true){
            criteria.andIsHotEqualTo(true);
        }
        if(isNew == true){
            criteria.andIsNewEqualTo(true);
        }
        int count = (int) cskaoyanMallGoodsMapper.countByExample(goodsExample);
        List<FloorGood> floorGoods = cskaoyanMallGoodsMapper.searchGoods(keyword,sort,order,categoryId,brandId,isHot,isNew);
        List<CskaoyanMallCategory> categories = cskaoyanMallCategoryMapper.searchCategory(keyword);
        SearchGoods searchGoods = new SearchGoods();
        searchGoods.setGoodsList(floorGoods);
        searchGoods.setFilterCategoryList(categories);
        searchGoods.setCount(count);
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setData(searchGoods);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if(userId != null && s != null){
            CskaoyanMallSearchHistory history = new CskaoyanMallSearchHistory();
            history.setDeleted(false);
            history.setAddTime(new Date());
            history.setUpdateTime(new Date());
            history.setKeyword(s);
            history.setUserId(userId);
            int search = historyMapper.insertSearch(history);
        }
        return responseVo;
    }

    @Override
    public ResponseVo searchclearhistory(HttpServletRequest request) {
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        int history = historyMapper.deleteHistory(userId.toString());
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }

    @Override
    public ResponseVo couponlist(int page, int size) {
        CskaoyanMallCouponExample cskaoyanMallCouponExample = new CskaoyanMallCouponExample();
        CskaoyanMallCouponExample.Criteria criteria3 = cskaoyanMallCouponExample.createCriteria();
        criteria3.andDeletedNotEqualTo(true);
        PageHelper.startPage(page,size);
        List<CskaoyanMallCoupon> couponList = cskaoyanMallCouponMapper.selectByExample(cskaoyanMallCouponExample);
        int count = (int) cskaoyanMallCouponMapper.countByExample(cskaoyanMallCouponExample);
        CouponManage<Object> couponManage = new CouponManage<>();
        couponManage.setCount(count);
        couponManage.setData(couponList);
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setData(couponManage);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }

    @Override
    public ResponseVo goodsdetail(String id, HttpServletRequest request) {
        //attributes
        CskaoyanMallGoodsAttributeExample attributeExample = new CskaoyanMallGoodsAttributeExample();
        CskaoyanMallGoodsAttributeExample.Criteria criteria = attributeExample.createCriteria();
        criteria.andGoodsIdEqualTo(Integer.parseInt(id));
        List<CskaoyanMallGoodsAttribute> attributes = attributeMapper.selectByExample(attributeExample);

        //brand
        CskaoyanMallBrand brand = cskaoyanMallBrandMapper.searchById(id);

        //comment
        CskaoyanMallCommentExample commentExample = new CskaoyanMallCommentExample();
        CskaoyanMallCommentExample.Criteria commentCriteria = commentExample.createCriteria();
        commentCriteria.andValueIdEqualTo(Integer.parseInt(id));
        int count = (int) commentMapper.countByExample(commentExample);
        List<CskaoyanMallComment> commentList = commentMapper.selectByExample(commentExample);
        Comment comment = new Comment();
        comment.setCount(count);
        comment.setData(commentList);

        //groupon
        CskaoyanMallGrouponRulesExample rulesExample = new CskaoyanMallGrouponRulesExample();
        CskaoyanMallGrouponRulesExample.Criteria rulesCriteria = rulesExample.createCriteria();
        rulesCriteria.andGoodsIdEqualTo(Integer.parseInt(id));
        List<CskaoyanMallGrouponRules> rulesList = rulesMapper.selectByExample(rulesExample);

        //info
        CskaoyanMallGoods goods = cskaoyanMallGoodsMapper.selectByPrimaryKey(Integer.parseInt(id));

        //issue
        CskaoyanMallIssueExample issueExample = new CskaoyanMallIssueExample();
        List<CskaoyanMallIssue> issues = issueMapper.selectByExample(issueExample);

        //productList
        CskaoyanMallGoodsProductExample productExample = new CskaoyanMallGoodsProductExample();
        CskaoyanMallGoodsProductExample.Criteria productExampleCriteria = productExample.createCriteria();
        productExampleCriteria.andGoodsIdEqualTo(Integer.parseInt(id));
        List<CskaoyanMallGoodsProduct> products = productMapper.selectByExample(productExample);

        //shareImage
        String shareImage = "";

        //specificationList
        List specificationList = specificationMapper.searchByGoodId(id);

        //userHasCollect
        int userHasCollect = 0;
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if (userId != null) {
            CskaoyanMallCollectExample collectExample = new CskaoyanMallCollectExample();
            CskaoyanMallCollectExample.Criteria collectExampleCriteria = collectExample.createCriteria();
            collectExampleCriteria.andUserIdEqualTo(userId).andValueIdEqualTo(Integer.parseInt(id));
            List<CskaoyanMallCollect> collects = collectMapper.selectByExample(collectExample);
            if(collects != null && collects.size() != 0){
                userHasCollect = collects.get(0).getType();
            }


            CskaoyanMallFootprint footprint = new CskaoyanMallFootprint();
            footprint.setAddTime(new Date());
            footprint.setUpdateTime(new Date());
            footprint.setDeleted(false);
            footprint.setGoodsId(Integer.parseInt(id));
            footprint.setUserId(userId);
            int footprint1 = footprintMapper.insertFootprint(footprint);
        }
        Goodsdetail goodsdetail = new Goodsdetail();
        goodsdetail.setAttribute(attributes);
        goodsdetail.setBrand(brand);
        goodsdetail.setComment(comment);
        goodsdetail.setGroupon(rulesList);
        goodsdetail.setInfo(goods);
        goodsdetail.setIssue(issues);
        goodsdetail.setProductList(products);
        goodsdetail.setShareImage(shareImage);
        goodsdetail.setSpecificationList(specificationList);
        goodsdetail.setUserHasCollect(userHasCollect);

        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setData(goodsdetail);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;

    }
    public int insertCouponUser(Integer userId, Integer couponId) {

        CskaoyanMallCouponUser couponUser = new CskaoyanMallCouponUser();
        //用couponID在coupon表里查优惠券的可使用天数days，然后coupon_user表里的starttime为添加时间，+days为结束时间
        Date startDate = new Date();
        CskaoyanMallCoupon coupon = cskaoyanMallCouponMapper.selectByPrimaryKey(couponId);
        Date endDate = DateAddDayUtil.dateAddDay(startDate, coupon.getDays());

        couponUser.setAddTime(startDate);
        couponUser.setStartTime(startDate);
        couponUser.setEndTime(endDate);
        couponUser.setCouponId(couponId);
        couponUser.setUserId(userId);

        int i = couponUserMapper.insertSelective(couponUser);
        return i;

    }

    @Override
    public List<CskaoyanMallBrand> selectBrandListPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<CskaoyanMallBrand> brandList = brandMapper.selectByExample(new CskaoyanMallBrandExample());
        return brandList;
    }

    @Override
    public int getBrandListTotalpages(int size) {
        int count = (int) brandMapper.countByExample(new CskaoyanMallBrandExample());
        int totalPages = 0;
        if (count % size != 0 ){
            //如果不能整除，则加1
            totalPages = count/size + 1;
        }else {
            totalPages = count/size;
        }
        return totalPages;
    }

    @Override
    public CskaoyanMallBrand selectBrandDetail(int id) {
        CskaoyanMallBrand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }
}
