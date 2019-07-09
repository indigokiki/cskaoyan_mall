package com.cskaoyan.controller.wx;


import com.cskaoyan.service.wx.WxIndexService;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.wxutil.BaseRespVo;
import com.cskaoyan.util.wxutil.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexPageController {

    @Autowired
    WxIndexService wxIndexService;

    @RequestMapping("wx/home/index")
    public ResponseVo firstRequest(){
        return wxIndexService.getIndex();
    }

    //杨朔增-首页给用户增加优惠券
    @RequestMapping("wx/coupon/receive")
    public ResponseVo couponReceive(@RequestBody Integer couponId, HttpServletRequest request){
        //通过请求头获得userId，进而可以获得一切关于user的信息
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
       //在coupon_user表里添加用户的对应优惠券
        int i = wxIndexService.insertCouponUser(userId,couponId);

        ResponseVo<Object> responseVo = new ResponseVo<>();
        if (i == 1){
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");
        }else {
            responseVo.setErrno(740);
            responseVo.setErrmsg("优惠券已领取");
        }

        return responseVo;
    }
}
