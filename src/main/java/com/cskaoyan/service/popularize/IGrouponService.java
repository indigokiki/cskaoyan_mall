package com.cskaoyan.service.popularize;

import com.cskaoyan.bean.popularize.vo.ResManager;

import java.util.Map;

public interface IGrouponService {
    ResManager<Map<String,Object>> findAll();
}
