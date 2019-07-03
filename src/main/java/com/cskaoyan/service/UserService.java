package com.cskaoyan.service;

import com.cskaoyan.bean.CskaoyanMallAddress;
import com.cskaoyan.bean.CskaoyanMallCollect;
import com.cskaoyan.bean.CskaoyanMallFootprint;
import com.cskaoyan.bean.CskaoyanMallUser;
import com.cskaoyan.mapper.CskaoyanMallUserMapper;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/3 18:00
 */
public interface UserService {
    ResponseVo<Map> findUsers(String username, String mobile);
    List<CskaoyanMallAddress> findAdresses(int userId, String name);
    List<CskaoyanMallCollect> findCollects(int userId, int valueId);
    List<CskaoyanMallFootprint> findFootprints(int userId, int goodsId);




}
