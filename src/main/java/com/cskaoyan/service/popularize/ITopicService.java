package com.cskaoyan.service.popularize;


import com.cskaoyan.bean.popularize.Topic;
import com.cskaoyan.bean.popularize.vo.ResManager;

public interface ITopicService {
    public ResManager<Topic> findAll(Integer page, Integer limit);

    public int update(Topic topic);

    void delete(Topic topic);

    void create(Topic topic);

    ResManager<Topic> list(String title, String subtitle, Integer page, Integer limit);
}
