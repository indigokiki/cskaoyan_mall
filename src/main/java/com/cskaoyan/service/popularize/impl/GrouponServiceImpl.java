package com.cskaoyan.service.popularize.impl;

import com.cskaoyan.bean.popularize.*;
import com.cskaoyan.bean.popularize.vo.ResManager;
import com.cskaoyan.mapper.popularize.GoodsMapper;
import com.cskaoyan.mapper.popularize.GrouponMapper;
import com.cskaoyan.mapper.popularize.GrouponRoleMapper;
import com.cskaoyan.service.popularize.IGrouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GrouponServiceImpl implements IGrouponService {

    @Autowired
    GrouponMapper grouponMapper;

    @Autowired
    GrouponRoleMapper roleMapper;

    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public ResManager<Map<String,Object>> findAll() {
        GrouponExample grouponExample = new GrouponExample();
        GrouponExample.Criteria criteria = grouponExample.createCriteria();
        List<Groupon> groupons = grouponMapper.selectByExample(grouponExample);
        ResManager<Map<String,Object>> resManager = new ResManager<>();
        ArrayList<Map<String,Object>> res = new ArrayList<>();
        for (Groupon groupon : groupons) {
            HashMap<String, Object> map = new HashMap<>();
            GrouponRoleExample grouponRoleExample = new GrouponRoleExample();
            GrouponRoleExample.Criteria criteriaRole = grouponRoleExample.createCriteria();
            criteriaRole.andIdEqualTo(groupon.getRulesId());
            GrouponRole grouponRoles = roleMapper.selectByExample(grouponRoleExample).get(0);
            Goods goods = goodsMapper.selectByPrimaryKey(grouponRoles.getGoodsId());
            map.put("goods",goods);
            map.put("groupon",groupon);
            map.put("rules",grouponRoles);

            ArrayList<Object> subGroupons = new ArrayList<>();
           map.put("subGroupons",subGroupons);
            res.add(map);
        }
        resManager.setItems(res);
        resManager.setTotal(res.size());
        return resManager;
    }
}
