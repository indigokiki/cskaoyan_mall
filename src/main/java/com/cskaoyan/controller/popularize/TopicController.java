package com.cskaoyan.controller.popularize;

import com.cskaoyan.bean.popularize.Topic;
import com.cskaoyan.bean.popularize.vo.ResManager;
import com.cskaoyan.service.popularize.ITopicService;
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
public class TopicController {


    @Autowired
    ITopicService topicService;



    @ResponseBody
    @RequestMapping("/topic/list")
    public Map<String, Object> list(@Param("title") String title, @Param("subtitle") String subtitle,@Param("page") Integer page,@Param("limit") Integer limit) {
        if(title==null&&subtitle==null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("data", topicService.findAll(page,limit));
            map.put("errmsg", "成功");
            map.put("errno", 0);
            return map;
        }else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("errmsg","成功");
            map.put("errno",0);
            ResManager<Topic> resManager =topicService.list(title,subtitle,page,limit);
            map.put("data",resManager);
            return map;
        }
    }

    @RequestMapping("topic/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Topic topic) {
        Map<String, Object> map = new HashMap<>();
        map.put("errmsg", "成功");
        map.put("errno", 0);
        map.put("data", topic);
        topicService.update(topic);
        return map;
    }



    @ResponseBody
    @RequestMapping("topic/create")
    public Map<String,Object> create(@RequestBody Topic topic){
        HashMap<String, Object> map = new HashMap<>();
        topicService.create(topic);
        map.put("errno",0);
        map.put("errmsg","成功");
        map.put("data",topic);
        return map;
    }

    @RequestMapping("topic/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestBody Topic topic){
        HashMap<String, Object> map = new HashMap<>();
        topicService.delete(topic);
        map.put("errno",0);
        map.put("errmsg","成功");
        return map;
    }



}
