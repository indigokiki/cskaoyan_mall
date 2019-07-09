package com.cskaoyan.service.wx;

import com.cskaoyan.util.ResponseVo;

public interface WxIndexService {

    ResponseVo getIndex();

    //yangshuo增-用户优惠券增加
    int insertCouponUser(Integer userId, Integer couponId);



}
