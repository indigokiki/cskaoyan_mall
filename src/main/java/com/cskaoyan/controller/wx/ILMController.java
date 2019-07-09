package com.cskaoyan.controller.wx;

import com.cskaoyan.service.wx.WxTopicService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/9 17:31
 */
@Controller
public class ILMController {
    @Autowired
    WxTopicService topicService;
    @RequestMapping("wx/topic/detail")
    public ResponseVo<Map> topicDetail(int id){
        ResponseVo<Map> responseVo = topicService.topicDetail(id);
        return responseVo;
    }

    @RequestMapping("wx/topic/list")
    public ResponseVo<Map> topicList(int page,int size){
        ResponseVo<Map> responseVo = topicService.topicList(page,size);
        return responseVo;
    }
}
