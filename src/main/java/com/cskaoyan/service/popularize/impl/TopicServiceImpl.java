package com.cskaoyan.service.popularize.impl;

import com.cskaoyan.bean.popularize.Topic;
import com.cskaoyan.bean.popularize.TopicExample;
import com.cskaoyan.bean.popularize.vo.ResManager;
import com.cskaoyan.mapper.popularize.TopicMapper;
import com.cskaoyan.service.popularize.ITopicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements ITopicService {

    @Autowired
    TopicMapper topicMapper;
    @Override
    public ResManager<Topic> findAll(Integer page, Integer limit) {
        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria criteria = topicExample.createCriteria();
        criteria.andIdIsNotNull();
        PageHelper.startPage(page,limit);
        List<Topic> ads = topicMapper.selectByExample(topicExample);
        ResManager<Topic> topicResManager = new ResManager<>();
        PageInfo<Topic> topicPageInfo = new PageInfo<>(ads);
        topicResManager.setItems(topicPageInfo.getList());
        topicResManager.setTotal((int) topicPageInfo.getTotal());
        return topicResManager;
    }

    @Override
    public int update(Topic topic) {
        int i = topicMapper.updateByPrimaryKey(topic);
        return i;
    }

    @Override
    public void delete(Topic topic) {

        topicMapper.deleteByPrimaryKey(topic.getId());
    }

    @Override
    public void create(Topic topic) {
        topicMapper.insert(topic);
    }

    @Override
    public ResManager<Topic> list(String title, String subtitle, Integer page, Integer limit) {
        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria criteria = topicExample.createCriteria();
        if(title!=null){
            criteria.andSubtitleLike("%"+title+"%");
        }
        if(subtitle!=null){
            criteria.andTitleLike("%"+subtitle+"%");
        }
        PageHelper.startPage(page,limit);
        List<Topic> list = topicMapper.selectByExample(topicExample);
        ResManager<Topic> resManager = new ResManager<>();
        PageInfo<Topic> pageInfo = new PageInfo<>(list);
        resManager.setTotal((int) pageInfo.getTotal());
        resManager.setItems(pageInfo.getList());
        return resManager;
    }
}
