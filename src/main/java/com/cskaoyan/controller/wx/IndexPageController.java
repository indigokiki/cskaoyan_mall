package com.cskaoyan.controller.wx;


import com.cskaoyan.service.wx.WxIndexService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseVo goodslist(String keyword, int page, int size, String sort, String order, String categoryId, HttpServletRequest request){
        return wxIndexService.goodslist(keyword,page,size,sort,order,categoryId,request);
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
    public ResponseVo goodsdetail(String id,HttpServletRequest request ){
        return wxIndexService.goodsdetail(id, request);
    }
}
