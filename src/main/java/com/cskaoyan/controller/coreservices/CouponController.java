package com.cskaoyan.controller.coreservices;

import com.cskaoyan.service.coreservices.CouponsService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
public class CouponController {
    @Autowired
    CouponsService couponsService;
    @RequestMapping("wx/coupon/mylist")
    public ResponseVo couponmylist(int page, int status, int size){
        return couponsService.getcouponlist(page,status,size);
    }
    @RequestMapping("wx/coupon/exchange")
    public ResponseVo couponexchange(@RequestBody Map<Object,Object> code1){
        String code = (String)code1.get("code");
        return couponsService.exchangecoupon(code);
    }
}
