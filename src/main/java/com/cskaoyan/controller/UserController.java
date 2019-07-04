package com.cskaoyan.controller;

import com.cskaoyan.bean.CskaoyanMallAddress;
import com.cskaoyan.bean.CskaoyanMallCollect;
import com.cskaoyan.bean.CskaoyanMallFootprint;
import com.cskaoyan.bean.CskaoyanMallUser;
import com.cskaoyan.service.UserService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/3 17:34
 */
@Controller
@ResponseBody
public class UserController {
    @Autowired
    UserService userService;
    //会员管理
    @RequestMapping("user/list")
    public ResponseVo<Map> getUsers(String username,String mobile) {
        ResponseVo<Map> users = userService.findUsers(username, mobile);

        /*for(CskaoyanMallUser user: users){
            System.out.println(user);
        }*/
        /*ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(data);

        Map<Object, Object> data = new HashMap<>();
        data.put("total",0);
        data.put("items",users);*/

        /*Map<Object, Object> map = new HashMap<>();
        map.put("errno", 0);
        map.put("data", data);
        map.put("errmsg", "成功");*/
        return users;
    }
    //收货地址
    @RequestMapping("address/list")
    public Map getAdresses (int userId,String name){
        List<CskaoyanMallAddress> adresses = userService.findAdresses(userId, name);
        Map<Object, Object> data = new HashMap<>();
        data.put("total",0);
        data.put("items",adresses);

        Map<Object, Object> map = new HashMap<>();
        map.put("errno", 0);
        map.put("data", data);
        map.put("errmsg", "成功");
        return map;
    }
    //会员收藏
    @RequestMapping("collect/list")
    public Map getCollects (int userId,int valueId){

        List<CskaoyanMallCollect> collects = userService.findCollects(userId, valueId);
        Map<Object, Object> data = new HashMap<>();
        data.put("total",0);
        data.put("items",collects);

        Map<Object, Object> map = new HashMap<>();
        map.put("errno", 0);
        map.put("data", data);
        map.put("errmsg", "成功");
        return map;
    }
    //会员足迹
    @RequestMapping("footprint/list")
    public Map getFootprints (int userId,int goodsId){
        List<CskaoyanMallFootprint> footprints = userService.findFootprints(userId, goodsId);
        Map<Object, Object> data = new HashMap<>();
        data.put("total",0);
        data.put("items",footprints);

        Map<Object, Object> map = new HashMap<>();
        map.put("errno", 0);
        map.put("data", data);
        map.put("errmsg", "成功");
        return map;
    }

}
