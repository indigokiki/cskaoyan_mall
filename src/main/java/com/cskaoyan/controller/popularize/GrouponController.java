package com.cskaoyan.controller.popularize;

import com.cskaoyan.bean.popularize.vo.ResManager;
import com.cskaoyan.service.popularize.IGrouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("admin")
public class GrouponController {

    @Autowired
    IGrouponService grouponService;

    @RequestMapping("groupon/listRecord")
    @ResponseBody
    public Map<String,Object> listRecord(){
        HashMap<String, Object> map = new HashMap<>();
        ResManager<Map<String,Object>> all = grouponService.findAll();
        map.put("data",all);
        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }
}
