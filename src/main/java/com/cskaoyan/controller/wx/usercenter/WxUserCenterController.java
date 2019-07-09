package com.cskaoyan.controller.wx.usercenter;

import com.cskaoyan.service.wx.usercenter.WxUserOrderService;
import com.cskaoyan.util.wxutil.BaseRespVo;
import com.cskaoyan.util.wxutil.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WxUserCenterController {

    @Autowired
    WxUserOrderService wxUserOrderService;

    @RequestMapping("wx/order/list")
    public BaseRespVo orderlist(int showType,int page,int size,HttpServletRequest request){
        //前端写了一个token放在请求头中
        //*************************
        //获得请求头
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        //通过请求头获得userId，进而可以获得一切关于user的信息
        //**************************
        if (userId == null) {
            return BaseRespVo.fail();
        }

        return wxUserOrderService.getOrderListByShowtype(userId,showType,page,size);
    }
}
