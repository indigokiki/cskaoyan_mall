package com.cskaoyan.service.coreservices;

import com.cskaoyan.util.ResponseVo;

public interface CouponsService {
    ResponseVo getcouponlist(int page, int status, int size);

    ResponseVo exchangecoupon(String code);
}
