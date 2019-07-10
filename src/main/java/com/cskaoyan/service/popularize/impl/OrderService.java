package com.cskaoyan.service.popularize.impl;

import com.cskaoyan.bean.popularize.Address;
import com.cskaoyan.bean.popularize.Order;
import com.cskaoyan.bean.popularize.OrderExample;
import com.cskaoyan.bean.popularize.User;
import com.cskaoyan.mapper.popularize.AddressMapper;
import com.cskaoyan.mapper.popularize.OrderMapper;
import com.cskaoyan.mapper.popularize.UserMapper;
import com.cskaoyan.service.popularize.IOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    CartService cartService;
    @Override
    public Integer insert(Integer addressId, Integer cartId, Integer couponId, Integer grouponLinkId, Integer grouponRulesId, String message, Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        Address address = addressMapper.selectByPrimaryKey(addressId);
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderSn(UUID.randomUUID().toString().replaceAll("-",""));
        order.setOrderStatus((short) 0);
        order.setConsignee(user.getNickname());
        order.setMobile(user.getMobile());
        order.setAddress(address.getAddress());
        order.setMessage(message);
        Map<String, Object> map = cartService.checkout(cartId, addressId, couponId, grouponRulesId, userId);
        Double goodsTotalPrice =(Double) map.get("goodsTotalPrice");
        order.setGoodsPrice(new BigDecimal(goodsTotalPrice));
        Double freightPrice = (Double) map.get("freightPrice");
        order.setFreightPrice(new BigDecimal(freightPrice));
        Double couponPrice = (Double) map.get("couponPrice");
        order.setCouponPrice(new BigDecimal(couponPrice));
        order.setIntegralPrice(new BigDecimal(0));
        order.setGrouponPrice(new BigDecimal(0));
        Double actualPrice = (Double) map.get("actualPrice");
        order.setOrderPrice(new BigDecimal(actualPrice));
        order.setActualPrice(new BigDecimal(actualPrice));
        order.setPayId(UUID.randomUUID().toString().replaceAll("-",""));
        order.setPayTime(new Date());
        order.setShipSn(UUID.randomUUID().toString().replaceAll("-",""));
        order.setShipChannel("顺丰");
        order.setShipTime(new Date());
        order.setConfirmTime(new Date());
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andOrderStatusEqualTo((short)1);
        long l = orderMapper.countByExample(orderExample);
        order.setComments((short)l);
        order.setEndTime(new Date());
        order.setAddTime(new Date());
        order.setUpdateTime(new Date());
        order.setDeleted(true);

        int insert = orderMapper.insert(order);
        Integer id = order.getId();
        System.out.println(id);
        return id;
    }

    @Override
    public HashMap<String, Object>  map(Integer showType, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if(showType==1||showType==2||showType==3||showType==4){
            criteria.andOrderStatusEqualTo(showType.shortValue());
        }
        List<Order> list = orderMapper.selectByExample(orderExample);
        PageInfo<Order> orderPageInfo = new PageInfo<>(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("count",orderPageInfo.getTotal());
        map.put("data",orderPageInfo.getList());
        map.put("totalPages",orderPageInfo.getPageNum());
        return map;
    }
}
