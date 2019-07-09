package com.cskaoyan.service.wx.usercenter;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.goods.CskaoyanMallComment;
import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.bean.wx.usercenter.*;
import com.cskaoyan.mapper.CskaoyanMallFootprintMapper;
import com.cskaoyan.mapper.CskaoyanMallOrderGoodsMapper;
import com.cskaoyan.mapper.CskaoyanMallOrderMapper;
import com.cskaoyan.mapper.goods.CskaoyanMallCommentMapper;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsMapper;
import com.cskaoyan.util.wxutil.BaseRespVo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WxUserOrderServiceImpl implements WxUserOrderService {

    @Autowired
    CskaoyanMallOrderMapper cskaoyanMallOrderMapper;

    @Autowired
    CskaoyanMallOrderGoodsMapper orderGoodsMapper;

    @Autowired
    CskaoyanMallCommentMapper commentMapper;

    @Autowired
    CskaoyanMallFootprintMapper footprintMapper;

    @Autowired
    CskaoyanMallGoodsMapper goodsMapper;

    @Override
    public BaseRespVo getOrderListByShowtype(int userId, int showType, int page, int size) {

        //先取出该用户的对应的订单
        CskaoyanMallOrderExample cskaoyanMallOrderExample = new CskaoyanMallOrderExample();
        CskaoyanMallOrderExample.Criteria criteria = cskaoyanMallOrderExample.createCriteria();
        switch (showType){
            case 0:
                break;
            case 1:
                criteria.andOrderStatusEqualTo((short)101);
                break;
            case 2:
                criteria.andOrderStatusEqualTo((short)201);
                break;
            case 3:
                criteria.andOrderStatusEqualTo((short)301);
                break;
            case 4:
                criteria.andCommentsIsNull().andOrderStatusGreaterThan((short)400);
        }
        criteria.andUserIdEqualTo(userId);
        PageHelper.startPage(page,size);
        List<CskaoyanMallOrder> cskaoyanMallOrders = cskaoyanMallOrderMapper.selectByExample(cskaoyanMallOrderExample);

        //然后开始配置返回给前端的参数，从最小分支往上进行收敛
        List<WxUserOrder> orderList = new ArrayList<>();

        for (CskaoyanMallOrder cskaoyanMallOrder : cskaoyanMallOrders) {

            WxUserOrder wxUserOrder = new WxUserOrder();

            //ResponseVo.data.data.goodsList
            CskaoyanMallOrderGoodsExample orderGoodsExample = new CskaoyanMallOrderGoodsExample();
            CskaoyanMallOrderGoodsExample.Criteria criteria1 = orderGoodsExample.createCriteria();
            criteria1.andOrderIdEqualTo(cskaoyanMallOrder.getId()).andDeletedNotEqualTo(true);
            List<CskaoyanMallOrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
            List<WxGoods> goodsList = new ArrayList<>();
            for (CskaoyanMallOrderGoods orderGood : orderGoods) {
                WxGoods wxGoods = new WxGoods();
                wxGoods.setGoodsName(orderGood.getGoodsName());
                wxGoods.setId(orderGood.getGoodsId());
                wxGoods.setNumber(orderGood.getNumber());
                wxGoods.setPicUrl(orderGood.getPicUrl());
                goodsList.add(wxGoods);
            }
            wxUserOrder.setGoodsList(goodsList);


            //ResponseVo.data.data.handleOption
            WxGoodsHandleOptions handleOption = getHandleOption(cskaoyanMallOrder);
            wxUserOrder.setHandleOption(handleOption);


            wxUserOrder.setActualPrice(cskaoyanMallOrder.getActualPrice().intValue());
            wxUserOrder.setId(cskaoyanMallOrder.getId());
            wxUserOrder.setGroupin(false);

            //ResponseVo.data.data.orderSn 订单号的处理
            String ordersn = getOrderSn(cskaoyanMallOrder);
            wxUserOrder.setOrderSn(ordersn);


            //ResponseVo.data.data.orderStatusText
            String orderStatusText = getOrderStatusText(cskaoyanMallOrder);
            wxUserOrder.setOrderStatusText(orderStatusText);

            orderList.add(wxUserOrder);

        }

        Map<Object,Object> data = new HashMap<>();
        long count = cskaoyanMallOrderMapper.countByExample(cskaoyanMallOrderExample);
        long totalpage = 0;
        if(count % size == 0){
            totalpage = count / size;
        }else{
            totalpage = count / size + 1;
        }
        data.put("count",count);
        data.put("data",orderList);
        data.put("totalPages",totalpage);

        return BaseRespVo.ok(data);
    }



    @Override
    public BaseRespVo getOrderDetail(int orderId, Integer userId) {
        CskaoyanMallOrder order = cskaoyanMallOrderMapper.selectByPrimaryKey(orderId);
        if(!userId.equals(order.getUserId())){
            return BaseRespVo.fail();
        }

        //detail.data.orderGoods
        CskaoyanMallOrderGoodsExample cskaoyanMallOrderGoodsExample = new CskaoyanMallOrderGoodsExample();
        CskaoyanMallOrderGoodsExample.Criteria criteria = cskaoyanMallOrderGoodsExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<CskaoyanMallOrderGoods> orderGoodsList = orderGoodsMapper.selectByExample(cskaoyanMallOrderGoodsExample);
        List<WxOrderGood> orderGoods = new ArrayList<>();
        for (CskaoyanMallOrderGoods ordergood : orderGoodsList) {
            WxOrderGood wxOrderGood = new WxOrderGood();
            try {
                BeanUtils.copyProperties(wxOrderGood,ordergood);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            //这里总是接收的一个String
            //可以现在拿出来（specifications[0]）然后做逻辑（转成多个string后塞进数组），这里先省略
            //String[] specifications = wxOrderGood.getSpecifications();
            //..........
            //wxOrderGood.setSpecifications(new String[]);
            orderGoods.add(wxOrderGood);
        }


        //detail.data.orderInfo
        WxOrderInfo wxOrderInfo = new WxOrderInfo();
        try {
            BeanUtils.copyProperties(wxOrderInfo,order);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        WxGoodsHandleOptions handleOption = getHandleOption(order);
        String orderSn = getOrderSn(order);
        String orderStatusText = getOrderStatusText(order);
        wxOrderInfo.setHandleOption(handleOption);
        wxOrderInfo.setOrderSn(orderSn);
        wxOrderInfo.setOrderStatusText(orderStatusText);


        Map<Object,Object> data = new HashMap<>();
        data.put("orderGoods",orderGoods);
        data.put("orderInfo",wxOrderInfo);
        return BaseRespVo.ok(data);
    }

    @Override
    public BaseRespVo orderRefund(int orderId,Integer userId) {
        CskaoyanMallOrder cskaoyanMallOrder = cskaoyanMallOrderMapper.selectByPrimaryKey(orderId);
        if(!cskaoyanMallOrder.getUserId().equals(userId)){
            return BaseRespVo.fail();
        }
        cskaoyanMallOrder.setOrderStatus((short)202);
        cskaoyanMallOrderMapper.updateByPrimaryKey(cskaoyanMallOrder);
        return BaseRespVo.ok(null);
    }

    @Override
    public BaseRespVo orderCancel(int orderId,Integer userId) {
        CskaoyanMallOrder cskaoyanMallOrder = cskaoyanMallOrderMapper.selectByPrimaryKey(orderId);
        if(!cskaoyanMallOrder.getUserId().equals(userId)){
            return BaseRespVo.fail();
        }
        cskaoyanMallOrder.setOrderStatus((short)102);
        cskaoyanMallOrderMapper.updateByPrimaryKey(cskaoyanMallOrder);
        return BaseRespVo.ok(null);
    }

    @Override
    public BaseRespVo getGoodsByOidNGid(int orderId, int goodsId,Integer userId) {
        CskaoyanMallOrder cskaoyanMallOrder = cskaoyanMallOrderMapper.selectByPrimaryKey(orderId);
        if(!cskaoyanMallOrder.getUserId().equals(userId)){
            return BaseRespVo.fail();
        }

        CskaoyanMallOrderGoodsExample orderGoodsExample = new CskaoyanMallOrderGoodsExample();
        CskaoyanMallOrderGoodsExample.Criteria criteria = orderGoodsExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId).andGoodsIdEqualTo(goodsId);
        List<CskaoyanMallOrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
        WxOrderGood wxOrderGood = new WxOrderGood();
        try {
            BeanUtils.copyProperties(wxOrderGood,orderGoods);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return BaseRespVo.ok(wxOrderGood);
    }

    @Override
    public BaseRespVo insertComment(CskaoyanMallComment comment, int orderGoodsId,Integer userId) {


        Date date = new Date();
        comment.setDeleted(false);
        comment.setAddTime(date);
        comment.setUpdateTime(date);
        //valueid是什么东西？？？
        comment.setValueId(0);
        //type是什么东西？？？
        byte b = 0;
        comment.setType(b);
        comment.setUserId(userId);
        int insert = commentMapper.insertSelective(comment);
        if(1 != insert){
            return BaseRespVo.fail();
        }
        int id = commentMapper.selectLastInsertId();

        CskaoyanMallOrderGoods ordergoods = orderGoodsMapper.selectByPrimaryKey(orderGoodsId);
        ordergoods.setComment(id);
        orderGoodsMapper.updateByPrimaryKey(ordergoods);
        return BaseRespVo.ok(null);
    }

    @Override
    public BaseRespVo deleteOrder(int orderId) {
        CskaoyanMallOrder order = cskaoyanMallOrderMapper.selectByPrimaryKey(orderId);
        order.setDeleted(true);
        cskaoyanMallOrderMapper.updateByPrimaryKey(order);
        return BaseRespVo.ok(null);
    }

    @Override
    public BaseRespVo orderConfirm(int orderId) {
        CskaoyanMallOrder order = cskaoyanMallOrderMapper.selectByPrimaryKey(orderId);
        order.setConfirmTime(new Date());
        order.setOrderStatus((short)401);
        cskaoyanMallOrderMapper.updateByPrimaryKey(order);
        return BaseRespVo.ok(null);
    }

    @Override
    public BaseRespVo getFootPrintList(int page, int size,Integer userId) {
        CskaoyanMallFootprintExample footprintExample = new CskaoyanMallFootprintExample();
        CskaoyanMallFootprintExample.Criteria criteria = footprintExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andDeletedNotEqualTo(true);
        PageHelper.startPage(page,size);
        List<CskaoyanMallFootprint> footprints = footprintMapper.selectByExample(footprintExample);

        if(null == footprints){
            return BaseRespVo.ok(null);
        }
        List<WxFootPrint> footPrintList = new ArrayList<>();
        for (CskaoyanMallFootprint footprint : footprints) {
            WxFootPrint wxFootPrint = new WxFootPrint();
            try {
                BeanUtils.copyProperties(wxFootPrint,footprint);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            Integer goodsId = footprint.getGoodsId();
            CskaoyanMallGoods good = goodsMapper.selectByPrimaryKey(goodsId);
            if(null == good){
                break;
            }
            try {
                BeanUtils.copyProperties(wxFootPrint,good);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            footPrintList.add(wxFootPrint);
        }
        Map<Object,Object> data = new HashMap<>();
        data.put("footprintList",footPrintList);

        long count = footprintMapper.countByExample(footprintExample);
        long totalPages;
        if(count % size == 0){
            totalPages = count / size;
        }else{
            totalPages = count /size + 1;
        }

        data.put("totalPages",totalPages);
        return BaseRespVo.ok(data);
    }

    private String getOrderStatusText(CskaoyanMallOrder cskaoyanMallOrder) {
        String orderStatusText = "";
        switch ((int)cskaoyanMallOrder.getOrderStatus()){
            case 101:
                orderStatusText = "未付款";
                break;
            case 102:
                orderStatusText = "已取消";
                break;
            case 103:
                orderStatusText = "已取消（系统）";
                break;
            case 201:
                orderStatusText = "待发货";
                break;
            case 202:
                orderStatusText = "已退款";
                break;
            case 301:
                orderStatusText = "已发货";
                break;
            case 401:
            case 402:
                orderStatusText = "交易完成";
                break;
        }
        return orderStatusText;
    }

    private String getOrderSn(CskaoyanMallOrder cskaoyanMallOrder) {
        Date addTime = cskaoyanMallOrder.getAddTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = format.format(addTime);
        date = date + cskaoyanMallOrder.getUserId();
        return date;
    }

    private WxGoodsHandleOptions getHandleOption(CskaoyanMallOrder cskaoyanMallOrder) {
        WxGoodsHandleOptions handleOption = new WxGoodsHandleOptions();
        switch ((int)cskaoyanMallOrder.getOrderStatus()){
            case 101:
                handleOption.setCancel(true);
                handleOption.setPay(true);
                break;
            case 102:
            case 103:
                handleOption.setDelete(true);
                break;
            case 201:
                handleOption.setRefund(true);
                break;
            case 202:
                break;
            case 301:
                handleOption.setConfirm(true);
                break;
            case 401:
            case 402:
                handleOption.setRebuy(true);
                handleOption.setDelete(true);
                break;
        }
        if(null == cskaoyanMallOrder.getComments()){
            handleOption.setComment(true);
        }
        return handleOption;
    }
}
