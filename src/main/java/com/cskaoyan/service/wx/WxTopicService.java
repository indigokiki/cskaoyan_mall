package com.cskaoyan.service.wx;

import com.cskaoyan.bean.mallmanage.Topic;
import com.cskaoyan.util.ResponseVo;

import java.util.List;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/9 17:35
 */
public interface WxTopicService {
    ResponseVo<Map> topicDetail(int id);

    ResponseVo<Map> topicList(int page, int size);

    ResponseVo<List<Topic>> topicRelated(int id);
}
