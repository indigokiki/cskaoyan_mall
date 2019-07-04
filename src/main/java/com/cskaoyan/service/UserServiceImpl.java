package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.mapper.CskaoyanMallAddressMapper;
import com.cskaoyan.mapper.CskaoyanMallCollectMapper;
import com.cskaoyan.mapper.CskaoyanMallFootprintMapper;
import com.cskaoyan.mapper.CskaoyanMallUserMapper;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/3 18:01
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    CskaoyanMallUserMapper userMapper;
    @Override
    public ResponseVo<Map> findUsers(String username, String mobile) {
        CskaoyanMallUserExample userExample = new CskaoyanMallUserExample();
        username = "%" + username +"%";
        mobile = "%" + mobile + "%";

        userExample.createCriteria().andUsernameLike(username).andMobileLike(mobile);
        List<CskaoyanMallUser> cskaoyanMallUsers = userMapper.selectByExample(userExample);

        ResponseVo<Map> responseVo = new ResponseVo<>();
        Map<String, Object> data = new HashMap<>();

        data.put("total",cskaoyanMallUsers.size());
        data.put("items",cskaoyanMallUsers);

        responseVo.setData(data);
        return responseVo;
    }

    @Autowired
    CskaoyanMallAddressMapper addressMapper;
    @Override
    public List<CskaoyanMallAddress> findAdresses(int userId, String name) {
        CskaoyanMallAddressExample addressExample = new CskaoyanMallAddressExample();
        name = "%" + name + "%";
        addressExample.createCriteria().andUserIdEqualTo(userId).andNameLike(name);
        List<CskaoyanMallAddress> addresses = addressMapper.selectByExample(addressExample);
        return addresses;
    }

    @Autowired
    CskaoyanMallCollectMapper collectMapper;
    @Override
    public List<CskaoyanMallCollect> findCollects(int userId, int valueId) {
        CskaoyanMallCollectExample collectExample = new CskaoyanMallCollectExample();
        collectExample.createCriteria().andUserIdEqualTo(userId).andValueIdEqualTo(valueId);
        List<CskaoyanMallCollect> collects = collectMapper.selectByExample(collectExample);
        return collects;
    }

    @Autowired
    CskaoyanMallFootprintMapper footprintMapper;
    @Override
    public List<CskaoyanMallFootprint> findFootprints(int userId, int goodsId) {
        CskaoyanMallFootprintExample footprintExample = new CskaoyanMallFootprintExample();
        footprintExample.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsId);
        List<CskaoyanMallFootprint> footprints = footprintMapper.selectByExample(footprintExample);
        return footprints;
    }


}
