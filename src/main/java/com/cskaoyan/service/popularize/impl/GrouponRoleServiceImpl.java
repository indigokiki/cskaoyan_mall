package com.cskaoyan.service.popularize.impl;

import com.cskaoyan.bean.popularize.Goods;
import com.cskaoyan.bean.popularize.GrouponRole;
import com.cskaoyan.bean.popularize.GrouponRoleExample;
import com.cskaoyan.bean.popularize.vo.ResManager;
import com.cskaoyan.mapper.popularize.GoodsMapper;
import com.cskaoyan.mapper.popularize.GrouponRoleMapper;
import com.cskaoyan.service.popularize.IGrouponRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GrouponRoleServiceImpl implements IGrouponRoleService {

    @Autowired
    GrouponRoleMapper roleMapper;

    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public ResManager<GrouponRole> findAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        GrouponRoleExample grouponRoleExample = new GrouponRoleExample();
        GrouponRoleExample.Criteria criteria = grouponRoleExample.createCriteria();
        criteria.andIdIsNotNull();
        List<GrouponRole> ads = roleMapper.selectByExample(grouponRoleExample);
        ResManager<GrouponRole> topicResManager = new ResManager<>();
        PageInfo<GrouponRole> grouponRolePageInfo = new PageInfo<>(ads);
        topicResManager.setItems(grouponRolePageInfo.getList());
        topicResManager.setTotal((int) grouponRolePageInfo.getTotal());
        return topicResManager;
    }

    @Override
    public int update(GrouponRole groupon) {
        int i = roleMapper.updateByPrimaryKey(groupon);
        return i;
    }

    @Override
    public void delete(GrouponRole groupon) {

        roleMapper.deleteByPrimaryKey(groupon.getId());
    }

    @Override
    public GrouponRole create(GrouponRole groupon) {
        Goods goods = goodsMapper.selectByPrimaryKey(groupon.getGoodsId());
        groupon.setGoodsName(goods.getName());
        groupon.setPicUrl(goods.getPicUrl());
        groupon.setAddTime(new Date());
        groupon.setUpdateTime(new Date());
        groupon.setDeleted(true);
        roleMapper.insert(groupon);
        return groupon;
    }

    @Override
    public ResManager<GrouponRole> list(Integer goodsId, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        GrouponRoleExample grouponRoleExample = new GrouponRoleExample();
        GrouponRoleExample.Criteria criteria = grouponRoleExample.createCriteria();
        if(goodsId!=null){
            criteria.andGoodsIdEqualTo(goodsId);
        }

        List<GrouponRole> list = roleMapper.selectByExample(grouponRoleExample);
        ResManager<GrouponRole> resManager = new ResManager<>();
        PageInfo<GrouponRole> grouponRolePageInfo = new PageInfo<>(list);
        resManager.setTotal((int) grouponRolePageInfo.getTotal());
        resManager.setItems(grouponRolePageInfo.getList());
        return resManager;
    }
}
