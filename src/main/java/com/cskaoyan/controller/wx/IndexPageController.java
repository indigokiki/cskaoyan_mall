package com.cskaoyan.controller.wx;


import com.cskaoyan.bean.CskaoyanMallBrand;
import com.cskaoyan.service.wx.WxIndexService;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.wxutil.BaseRespVo;
import com.cskaoyan.util.wxutil.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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

    //杨朔增-首页-品牌商直供
    //1.list
    @RequestMapping("brand/list")
    public ResponseVo brandList(int page, int size){

        List<CskaoyanMallBrand> brandList = wxIndexService.selectBrandListPage(page,size);
        int totalPages = wxIndexService.getBrandListTotalpages(size);

        HashMap<String, Object> map = new HashMap<>();
        map.put("brandList",brandList);
        map.put("totalPages",totalPages);

        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setData(map);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }

    //2.detail
    @RequestMapping("brand/detail")
    public ResponseVo brandDetail(int id){
        CskaoyanMallBrand brand = wxIndexService.selectBrandDetail(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("brand",brand);

        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setData(map);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }


    //yangshuo增-添加购物车 {goodsId: 1057036, number: 1, productId: 71}
    @RequestMapping("cart/add")
    public ResponseVo cartAdd(HttpServletRequest request, @RequestBody Map<String,Object> map){
        //获取userid
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);

        //获取传递的json数据
        Integer goodsId = (Integer) map.get("goodsId");
        Integer number = (Integer) map.get("number");
        Integer productId = (Integer) map.get("productId");

        //将goods加入购物车
        int i = wxIndexService.cartAdd(userId, goodsId, productId, number);

        //并查询该用户购物车内的goods个数
        int count = wxIndexService.countUserCartGoods(userId);

        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setData(count);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }

    //yangshuo增-添加或删除收藏 collect/addordelete {type: 0, valueId: 1130037}
    @RequestMapping("collect/addordelete")
    public ResponseVo collectAddOrDelete(@RequestBody Map<String,Integer> map,HttpServletRequest request){
        //获取userid
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);


        //获取传递的json数据；type=0时valueid是商品id
        Integer type =  map.get("type");
        Integer valueId = map.get("valueId");
        //增或删
        String addOrDelete = wxIndexService.collectAddOrDelete(userId,type,valueId);

        HashMap<String, String> typeMap = new HashMap<>();
        typeMap.put("type",addOrDelete);

        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setData(typeMap);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }

}
