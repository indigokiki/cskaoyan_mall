package com.cskaoyan.controller.popularize;

import com.cskaoyan.bean.popularize.GrouponRole;
import com.cskaoyan.bean.popularize.vo.ResManager;
import com.cskaoyan.service.popularize.IGrouponRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class GrouponRoleController {


    @Autowired
    IGrouponRoleService roleService;



    @ResponseBody
    @RequestMapping("/groupon/list")
    public Map<String, Object> list(@Param("goodsId") Integer goodsId,@Param("page")Integer page,@Param("limit") Integer limit) {
        if(goodsId==null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("data", roleService.findAll(page,limit));
            map.put("errmsg", "成功");
            map.put("errno", 0);
            return map;
        }else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("errmsg","成功");
            map.put("errno",0);
            ResManager<GrouponRole> resManager =roleService.list(goodsId,page,limit);
            map.put("data",resManager);
            return map;
        }
    }

    @RequestMapping("groupon/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody GrouponRole grouponRole) {
        Map<String, Object> map = new HashMap<>();
        map.put("errmsg", "成功");
        map.put("errno", 0);
        map.put("data", grouponRole);
        roleService.update(grouponRole);
        return map;
    }



    @ResponseBody
    @RequestMapping("groupon/create")
    public Map<String,Object> create(@RequestBody GrouponRole grouponRole){
        HashMap<String, Object> map = new HashMap<>();
        GrouponRole grouponRole1 = roleService.create(grouponRole);
        map.put("errno",0);
        map.put("errmsg","成功");
        map.put("data",grouponRole1);
        return map;
    }

    @RequestMapping("groupon/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestBody GrouponRole grouponRole){
        HashMap<String, Object> map = new HashMap<>();
        roleService.delete(grouponRole);
        map.put("errno",0);
        map.put("errmsg","成功");
        return map;
    }



}
