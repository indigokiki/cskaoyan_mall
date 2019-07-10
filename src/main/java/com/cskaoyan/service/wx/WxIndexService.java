package com.cskaoyan.service.wx;

import com.cskaoyan.util.ResponseVo;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

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




}
