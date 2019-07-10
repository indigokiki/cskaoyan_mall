package com.cskaoyan.controller.wx.usercenter;

import com.cskaoyan.bean.Result;
import com.cskaoyan.bean.goods.CskaoyanMallComment;
import com.cskaoyan.bean.wx.usercenter.MyJsonUtil;
import com.cskaoyan.service.mallmanage.PicService;
import com.cskaoyan.service.wx.usercenter.WxUserOrderService;
import com.cskaoyan.util.wxutil.BaseRespVo;
import com.cskaoyan.util.wxutil.UserTokenManager;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WxUserCenterController {

    @Autowired
    WxUserOrderService wxUserOrderService;

    @Autowired
    PicService picService;

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
    @RequestMapping("wx/order/detail")
    public BaseRespVo orderdetail(int orderId,HttpServletRequest request){

        //验证用户id，防止不安全参数
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if (userId == null) {
            return BaseRespVo.fail();
        }

        return wxUserOrderService.getOrderDetail(orderId,userId);
    }

    @RequestMapping("wx/order/refund")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BaseRespVo orderrefund(Integer orderId,HttpServletRequest request){
        //验证用户id，防止不安全参数
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if (userId == null) {
            return BaseRespVo.fail();
        }
        return wxUserOrderService.orderRefund(orderId,userId);
    }

    @RequestMapping("wx/order/cancel")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BaseRespVo ordercancel(@RequestBody MyJsonUtil json,HttpServletRequest request){
        //验证用户id，防止不安全参数
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if (userId == null) {
            return BaseRespVo.fail();
        }
        int orderId = json.getOrderId();
        return wxUserOrderService.orderCancel(orderId,userId);
    }


    @RequestMapping("wx/order/goods")
    public BaseRespVo ordergoods(Integer orderId,Integer goodsId,HttpServletRequest request){

        //验证用户id，防止不安全参数
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if (userId == null) {
            return BaseRespVo.fail();
        }
        return wxUserOrderService.getGoodsByOidNGid(orderId,goodsId,userId);
    }

    @RequestMapping("wx/storage/upload")
    public Result storageupload(MultipartFile file){
        return picService.create(file);
    }

    @RequestMapping("wx/order/comment")
    public BaseRespVo ordercomment(CskaoyanMallComment comment,Integer orderGoodsId,HttpServletRequest request){
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if (userId == null) {
            return BaseRespVo.fail();
        }
        return wxUserOrderService.insertComment(comment,orderGoodsId,userId);
    }

    @RequestMapping("wx/order/delete")
    public BaseRespVo orderdelete(@RequestBody MyJsonUtil json,HttpServletRequest request){
        //验证用户id，防止不安全参数
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if (userId == null) {
            return BaseRespVo.fail();
        }
        int orderId = json.getOrderId();
        return wxUserOrderService.deleteOrder(orderId);
    }

    @RequestMapping("wx/order/confirm")
    public BaseRespVo ordercomfirm(@RequestBody MyJsonUtil json, HttpServletRequest request){
        //验证用户id，防止不安全参数
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if (userId == null) {
            return BaseRespVo.fail();
        }
        int orderId = json.getOrderId();
        return wxUserOrderService.orderConfirm(orderId);
    }

    @RequestMapping("wx/footprint/list")
    public BaseRespVo footprintlist(int page,int size,HttpServletRequest request){
        //验证用户id，防止不安全参数
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if (userId == null) {
            return BaseRespVo.fail();
        }

        return wxUserOrderService.getFootPrintList(page,size,userId);
    }

    @RequestMapping("wx/address/list")
    public BaseRespVo addresslist(){
        return BaseRespVo.fail();
    }


}
