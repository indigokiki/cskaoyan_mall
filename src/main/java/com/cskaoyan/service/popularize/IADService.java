package com.cskaoyan.service.popularize;


import com.cskaoyan.bean.popularize.AD;
import com.cskaoyan.bean.popularize.vo.ResManager;

public interface IADService {
    public ResManager<AD> findAll(Integer page, Integer limit);

    public int update(AD ad);

    void delete(AD ad);

    void create(AD ad);

    ResManager<AD> list(String name, String content, Integer page, Integer limit);
}
