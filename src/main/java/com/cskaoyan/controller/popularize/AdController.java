package com.cskaoyan.controller.popularize;

import com.cskaoyan.bean.popularize.AD;
import com.cskaoyan.bean.popularize.vo.ResManager;
import com.cskaoyan.service.popularize.IADService;
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
public class AdController {
    AD ad=new AD();

    @Autowired
    IADService adService;



    @ResponseBody
    @RequestMapping("/ad/list")
    public Map<String, Object> list(@Param("name") String name, @Param("content") String content,@Param("page") Integer page,@Param("limit") Integer limit) {
        if(name==null&&content==null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("data", adService.findAll(page,limit));
            map.put("errmsg", "成功");
            map.put("errno", 0);
            return map;
        }else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("errmsg","成功");
            map.put("errno",0);
            ResManager<AD> resManager =adService.list(name,content,page,limit);
            map.put("data",resManager);
            return map;
        }
    }

    @RequestMapping("ad/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody AD ad) {
        Map<String, Object> map = new HashMap<>();
        map.put("errmsg", "成功");
        map.put("errno", 0);
        map.put("data", ad);
        adService.update(ad);
        return map;
    }



    @ResponseBody
    @RequestMapping("ad/create")
    public Map<String,Object> create(@RequestBody AD ad){
        HashMap<String, Object> map = new HashMap<>();
        adService.create(ad);
        map.put("errno",0);
        map.put("errmsg","成功");
        map.put("data",ad);
        return map;
    }

    @RequestMapping("ad/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestBody AD ad){
        HashMap<String, Object> map = new HashMap<>();
        adService.delete(ad);
        map.put("errno",0);
        map.put("errmsg","成功");
        return map;
    }



}
