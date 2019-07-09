package com.cskaoyan.controller.popularize;

import com.cskaoyan.bean.popularize.Coupon;
import com.cskaoyan.bean.popularize.CouponUser;
import com.cskaoyan.bean.popularize.CouponUserExample;
import com.cskaoyan.bean.popularize.vo.ResManager;
import com.cskaoyan.mapper.popularize.CouponUserMapper;
import com.cskaoyan.service.popularize.ICouponService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class CouponController {


    @Autowired
    ICouponService couponService;

    @Autowired
    CouponUserMapper userMapper;
    @ResponseBody
    @RequestMapping("coupon/list")
    public Map<String, Object> list(@Param("name") String name, @Param("type") Short type,@Param("status") Short status,@Param("page") Integer page,@Param("limit") Integer limit) {
        if(name==null&&type==null&&status==null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("data", couponService.findAll(page,limit));
            map.put("errmsg", "成功");
            map.put("errno", 0);
            return map;
        }else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("errmsg","成功");
            map.put("errno",0);
            ResManager<Coupon> resManager =couponService.list(name,status,type,page,limit);
            map.put("data",resManager);
            return map;
        }
    }

    @RequestMapping("coupon/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Coupon coupon) {
        Map<String, Object> map = new HashMap<>();
        map.put("errmsg", "成功");
        map.put("errno", 0);
        map.put("data", coupon);
        couponService.update(coupon);
        return map;
    }



    @ResponseBody
    @RequestMapping("coupon/create")
    public Map<String,Object> create(@RequestBody Coupon coupon){
        HashMap<String, Object> map = new HashMap<>();
        couponService.create(coupon);
        map.put("errno",0);
        map.put("errmsg","成功");
        map.put("data",coupon);
        return map;
    }

    @RequestMapping("coupon/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestBody Coupon coupon){
        HashMap<String, Object> map = new HashMap<>();
        couponService.delete(coupon);
        map.put("errno",0);
        map.put("errmsg","成功");
        return map;
    }

    @ResponseBody
    @RequestMapping("coupon/read")
    public Map<String,Object> read(@Param("id") Integer id){
        HashMap<String, Object> map = new HashMap<>();
        Coupon coupon=couponService.findOne(id);
        map.put("errno",0);
        map.put("errmsg","成功");
        map.put("data",coupon);
        return map;
    }

    @ResponseBody
    @RequestMapping("coupon/listuser")
    public Map<String,Object> listuser(@Param("couponId") Integer couponId){
        HashMap<String, Object> map = new HashMap<>();
        CouponUserExample couponUserExample = new CouponUserExample();
        CouponUserExample.Criteria criteria = couponUserExample.createCriteria();
        criteria.andCouponIdEqualTo(couponId);
        List<CouponUser> couponUsers = userMapper.selectByExample(couponUserExample);
        ResManager<CouponUser> couponUserResManager = new ResManager<>();
        couponUserResManager.setTotal(couponUsers.size());
        couponUserResManager.setItems(couponUsers);
        map.put("data",couponUserResManager);
        map.put("errno",0);
        map.put("errmsg","成功");
        return map;
    }

}
