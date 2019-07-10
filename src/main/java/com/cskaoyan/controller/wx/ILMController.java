package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.mallmanage.Topic;
import com.cskaoyan.service.wx.WxCommentService;
import com.cskaoyan.service.wx.WxTopicService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/9 17:31
 */
@RestController
@RequestMapping("/wx")
public class ILMController {
    @Autowired
    WxTopicService topicService;
    @RequestMapping("/topic/detail")
    public ResponseVo<Map> topicDetail(int id){
        ResponseVo<Map> responseVo = topicService.topicDetail(id);
        return responseVo;
    }

    @RequestMapping("/topic/list")
    public ResponseVo<Map> topicList(int page,int size){
        ResponseVo<Map> responseVo = topicService.topicList(page,size);
        return responseVo;
    }

    @RequestMapping("/topic/related")
    public ResponseVo<List<Topic>> topicRelated(int id){
        ResponseVo<List<Topic>> responseVo = topicService.topicRelated(id);
        return responseVo;
    }

    @Autowired
    WxCommentService commentService;
    @RequestMapping("/comment/list")
    public ResponseVo<Map> commentList(int valueId,int type,int showType,int page,int size){
        ResponseVo<Map> responseVo = commentService.commentList(valueId,type,showType,page,size);
        return responseVo;
    }
}
