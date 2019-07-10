package com.cskaoyan.controller.popularize;

import com.cskaoyan.bean.popularize.*;
import com.cskaoyan.controller.util.BaseRespVo;
import com.cskaoyan.mapper.popularize.AddressMapper;
import com.cskaoyan.mapper.popularize.RegionMapper;
import com.cskaoyan.service.popularize.ICartService;
import com.cskaoyan.service.popularize.IOrderService;
import com.cskaoyan.util.wxutil.UserTokenManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class CartController {

    @Autowired
            @Qualifier("myCartservice")
    ICartService cartService;

    @Autowired
    IOrderService orderService;
    @RequestMapping("/cart/index")
    public Map<String,Object> list(HttpServletRequest request){
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        HashMap<String, Object> data = cartService.findTotal(userId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",data);
        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }

    @RequestMapping("cart/checked")
    public Map<String,Object> check(@RequestBody String json,HttpServletRequest request){
        Gson gson = new Gson();
        Map map = gson.fromJson(json, Map.class);
        double isChecked = ((double) map.get("isChecked"));
        List<Double> productIds = (List<Double>) map.get("productIds");
        Integer[] temp=new Integer[productIds.size()];
        for (int i=0;i<productIds.size();i++){
            temp[i]=productIds.get(i).intValue();
        }

        cartService.checked((int)isChecked,temp);

     /*   return list(request);*/
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        HashMap<String, Object> data = cartService.findTotal(userId);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("data",data);
        map2.put("errmsg","成功");
        map2.put("errno",0);
        return map2;
    }


    @RequestMapping("cart/update")
    public Map<String,Object> update(@RequestBody Cart cart){
        HashMap<String, Object> map = new HashMap<>();
        cartService.update(cart);
        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }

    @RequestMapping("cart/checkout")
    //cartId=0&addressId=5&couponId=0&grouponRulesId=0,checkout
    public Map<String,Object> checkOut(Integer cartId,Integer addressId,Integer couponId,Integer grouponRulesId,HttpServletRequest request){
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);

        HashMap<String, Object> map = new HashMap<>();
        Map<String, Object> data = cartService.checkout(cartId, addressId, couponId, grouponRulesId,userId);
        map.put("data",data);

        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }
    //wx/coupon/selectlist?cartId=0&grouponRulesId=0
    @RequestMapping("coupon/selectlist")
    public Map<String,Object> selectCouponlist(Integer cartId,Integer grouponRulesId){
        HashMap<String, Object> map = new HashMap<>();
        List<Coupon> allCoupon = cartService.findAllCoupon();
        map.put("data",allCoupon);

        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }

    //收货地址address/list
   // @RequestMapping("address/list")
    @ResponseBody
    public BaseRespVo addressList(HttpServletRequest request){
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        List<Address> listAddress=cartService.listAddress(userId);
        return  BaseRespVo.ok(listAddress);
    }
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    AddressMapper addressMapper;
    //region/list?pid=0 地区
    @RequestMapping("region/list")
    public BaseRespVo region(Integer pid){
        RegionExample regionExample = new RegionExample();
        regionExample.createCriteria().andPidEqualTo(pid);
        List<Region> regions = regionMapper.selectByExample(regionExample);
        return BaseRespVo.ok(regions);
    }

    //wx/address/save保存收货地址
    @RequestMapping("address/save")
    public BaseRespVo saveAddress(@RequestBody Address address, HttpServletRequest request){
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        address.setUserId(userId);
        addressMapper.insert(address);
        return BaseRespVo.ok(12);
    }

    @RequestMapping("order/submit")
  /*  @Param("addressId") Integer addressId,
    @Param("cartId") Integer cartId,
    @Param("couponId") Integer couponId,
    @Param("grouponLinkId") Integer grouponLinkId,
    @Param("grouponRulesId") Integer grouponRulesId,
    @Param("message") String message,*/
    public BaseRespVo submit(@RequestBody String json,HttpServletRequest request){
        Gson gson = new Gson();
        Map map = gson.fromJson(json, Map.class);
        Integer addressId = ((Double) map.get("addressId")).intValue();
        Integer cartId = ((Double) map.get("cartId")).intValue();
        Integer couponId = ((Double) map.get("couponId")).intValue();
        Integer grouponLinkId = ((Double) map.get("grouponLinkId")).intValue();
        Integer grouponRulesId =((Double) map.get("grouponRulesId")).intValue();
        String message = (String) map.get("message");


        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        Integer orderid = orderService.insert(addressId, cartId, couponId, grouponLinkId, grouponRulesId, message, userId);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("orderId",orderid);
        return BaseRespVo.ok(map1);
    }

    //order/prepay
    @RequestMapping("order/prepay")
    public Map<String,String> prepay(){
        HashMap<String, String> map = new HashMap<>();
        map.put("errmsg","订单不能支付");
        map.put("errno","724");
        return map;
    }
//
//    ///order/list?showType=0&page=1&size=10
//    @RequestMapping("order/list")
//    public BaseRespVo orderList(Integer showType,Integer page,Integer size){
//        HashMap<String, Object> map=orderService.map(showType,page,size);
//
//
//
//        return BaseRespVo.ok(map);
//    }
}
