package com.cskaoyan.service.wx;

import com.cskaoyan.bean.CskaoyanMallTopic;
import com.cskaoyan.bean.CskaoyanMallTopicExample;
import com.cskaoyan.bean.mallmanage.Topic;
import com.cskaoyan.mapper.CskaoyanMallTopicMapper;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/9 17:35
 */
@Service
public class WxTopicServiceImpl implements WxTopicService {
    @Autowired
    CskaoyanMallTopicMapper topicMapper;
    @Override
    public ResponseVo<Map> topicDetail(int id) {
        //goods
        String[] goods = topicMapper.getGoodsInTopic(id);
        //topic
        CskaoyanMallTopic topic = topicMapper.selectByPrimaryKey(id);
        //判断，设置errno，errmsg
        HashMap<String, Object> data = new HashMap<>();
        data.put("goods",goods);
        data.put("topic",topic);
        ResponseVo<Map> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(data);
        return responseVo;
    }

    @Override
    public ResponseVo<Map> topicList(int page, int size) {
        //count
        CskaoyanMallTopicExample topicExample = new CskaoyanMallTopicExample();
        long count = topicMapper.countByExample(topicExample);
        //topicList
        PageHelper.startPage(page,size);
        CskaoyanMallTopicExample topicExample1 = new CskaoyanMallTopicExample();
        List<CskaoyanMallTopic> topicList = topicMapper.selectByExample(topicExample1);
        //判断，设置errno，errmsg
        HashMap<String, Object> data = new HashMap<>();
        data.put("count",count);
        data.put("data",topicList);
        ResponseVo<Map> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(data);
        return responseVo;
    }

    @Override
    public ResponseVo<List<Topic>> topicRelated(int id) {
        //data
        List<Topic> data = topicMapper.topicRelated4(id);

        //判断，设置errno，errmsg
        ResponseVo<List<Topic>> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(data);
        return responseVo;
    }
}
