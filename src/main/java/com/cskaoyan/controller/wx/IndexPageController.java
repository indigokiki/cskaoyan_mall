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
import java.util.Map;

@RestController
@RequestMapping("wx")
public class IndexPageController {

    @Autowired
    WxIndexService wxIndexService;

    @RequestMapping("home/index")
    public ResponseVo firstRequest(){
        return wxIndexService.getIndex();
    }


    @RequestMapping("goods/count")
    public ResponseVo count(){
        return wxIndexService.getGoodsCount();
    }

    @RequestMapping("search/index")
    public ResponseVo searchindex(HttpServletRequest request){
        return wxIndexService.searchIndex(request);
    }

    @RequestMapping("search/helper")
    public ResponseVo searchhelper(String keyword){
        return wxIndexService.searchHelper(keyword);
    }

    @RequestMapping("goods/list")
    public ResponseVo goodslist(String keyword, int page, int size, String sort, String order, String categoryId,
                                String brandId,boolean isHot,boolean isNew,HttpServletRequest request){
        return wxIndexService.goodslist(keyword,page,size,sort,order,categoryId,brandId,isHot,isNew,request);
    }


    @RequestMapping("search/clearhistory")
    public ResponseVo searchclearhistory(HttpServletRequest request){
        return wxIndexService.searchclearhistory(request);
    }

    @RequestMapping("coupon/list")
    public ResponseVo couponlist(int page, int size){
        return wxIndexService.couponlist(page,size);
    }


    @RequestMapping("goods/detail")
    public ResponseVo goodsdetail(String id,HttpServletRequest request ) {
        return wxIndexService.goodsdetail(id, request);
    }

    @RequestMapping("cart/goodscount")
    public ResponseVo cartgoodscount( ) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        responseVo.setData(0);
        return responseVo;
    }
    //我的团购
    @RequestMapping("groupon/my")
    public ResponseVo grouponmy(String showType) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        responseVo.setData(0);
        return responseVo;
    }


    //杨朔增-首页给用户增加优惠券
    @RequestMapping("coupon/receive")
    public ResponseVo couponReceive(@RequestBody Map<String,Integer> couponId, HttpServletRequest request){
        //通过请求头获得userId，进而可以获得一切关于user的信息
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        //在coupon_user表里添加用户的对应优惠券
        Integer id = couponId.get("couponId");
        int i = wxIndexService.insertCouponUser(userId,id);

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
