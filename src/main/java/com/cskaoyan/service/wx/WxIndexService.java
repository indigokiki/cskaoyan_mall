package com.cskaoyan.service.wx;

import com.cskaoyan.bean.CskaoyanMallBrand;
import com.cskaoyan.util.ResponseVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface WxIndexService {

    ResponseVo getIndex();


    ResponseVo getGoodsCount();

    ResponseVo searchIndex(HttpServletRequest request);

    ResponseVo searchHelper(String keyword);

    ResponseVo goodslist(String keyword, int page, int size, String sort, String order, String categoryId,
                         String brandId,boolean isHot,boolean isNew,HttpServletRequest request);

    ResponseVo searchclearhistory(HttpServletRequest request);

    ResponseVo couponlist(int page, int size);

    ResponseVo goodsdetail(String id, HttpServletRequest request);



    //yangshuo增-用户优惠券增加
    int insertCouponUser(Integer userId, Integer couponId);

    //yangshuo增-首页品牌商直供点击进入的列表
    List<CskaoyanMallBrand> selectBrandListPage(int page, int size);
    //yangshuo增-品牌商总页数
    int getBrandListTotalpages(int size);
    //yangshuo增-某个品牌详情
    CskaoyanMallBrand selectBrandDetail(int id);
}
