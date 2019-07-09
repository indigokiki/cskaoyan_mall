package com.cskaoyan.service.wx.usercenter;

import com.cskaoyan.bean.goods.CskaoyanMallComment;
import com.cskaoyan.util.wxutil.BaseRespVo;

public interface WxUserOrderService {


    BaseRespVo getOrderListByShowtype(int userId,int showType, int page, int size);

    BaseRespVo getOrderDetail(int orderId, Integer userId);

    BaseRespVo orderRefund(int orderId,Integer userId);

    BaseRespVo orderCancel(int orderId,Integer userId);

    BaseRespVo getGoodsByOidNGid(int orderId, int goodsId,Integer userId);

    BaseRespVo insertComment(CskaoyanMallComment comment, int orderGoodsId,Integer userId);

    BaseRespVo deleteOrder(int orderId);

    BaseRespVo orderConfirm(int orderId);

    BaseRespVo getFootPrintList(int page, int size,Integer userId);

    BaseRespVo getAddressByUid(Integer userId);
}
