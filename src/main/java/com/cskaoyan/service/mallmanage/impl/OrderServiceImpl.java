package com.cskaoyan.service.mallmanage.impl;

import com.cskaoyan.bean.*;
import com.cskaoyan.mapper.CskaoyanMallOrderGoodsMapper;
import com.cskaoyan.mapper.CskaoyanMallOrderMapper;
import com.cskaoyan.mapper.CskaoyanMallUserMapper;
import com.cskaoyan.service.mallmanage.OrderService;
import com.cskaoyan.util.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CskaoyanMallOrderMapper orderMapper;

    @Autowired
    CskaoyanMallOrderGoodsMapper orderGoodsMapper;

    @Autowired
    CskaoyanMallUserMapper userMapper;

    @Override
    public Result getAllOrders(int page, int limit, String sort, String order, String orderStatusArray, String userId, String orderSn) {
        CskaoyanMallOrderExample orderExample = new CskaoyanMallOrderExample();
        CskaoyanMallOrderExample.Criteria criteria = orderExample.createCriteria();
        if(orderStatusArray != null){
            criteria.andOrderStatusEqualTo((short) Integer.parseInt(orderStatusArray));
        }
        if(userId != null){
            criteria.andUserIdEqualTo(Integer.parseInt(userId));
        }
        if(orderSn != null){
            criteria.andOrderSnEqualTo(orderSn);
        }
        PageHelper.startPage(page, limit);
        List<CskaoyanMallOrder> orders = orderMapper.selectByExample(orderExample);
        int count = (int) orderMapper.countByExample(orderExample);
        Page<CskaoyanMallOrder> orderPage = new Page<>();
        orderPage.setTotal(count);
        orderPage.setItems(orders);
        Result<Page> result = new Result<>();
        result.setErrmsg("成功");
        result.setErrno(0);
        result.setData(orderPage);
        return result;
    }

    @Override
    public Result dedetail(String id) {
        CskaoyanMallOrderExample orderExample = new CskaoyanMallOrderExample();
        CskaoyanMallOrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andIdEqualTo(Integer.parseInt(id));
        List<CskaoyanMallOrder> orders = orderMapper.selectByExample(orderExample);
        CskaoyanMallOrder order = orders.get(0);
        CskaoyanMallUserExample userExample = new CskaoyanMallUserExample();
        CskaoyanMallUserExample.Criteria criteria1 = userExample.createCriteria();
        criteria1.andIdEqualTo(order.getUserId());
        List<CskaoyanMallUser> users = userMapper.selectByExample(userExample);
        CskaoyanMallUser user = users.get(0);
        CskaoyanMallOrderGoodsExample goodsExample = new CskaoyanMallOrderGoodsExample();
        CskaoyanMallOrderGoodsExample.Criteria criteria2 = goodsExample.createCriteria();
        criteria2.andOrderIdEqualTo(order.getId());
        List<CskaoyanMallOrderGoods> orderGoods = orderGoodsMapper.selectByExample(goodsExample);
        Map<Object, Object> map = new HashMap<>();
        map.put("order", order);
        map.put("orderGoods", orderGoods);
        map.put("user", user);
        Result<Object> result = new Result<>();
        result.setData(map);
        result.setErrno(0);
        result.setErrmsg("成功");

        return result;
    }
}
