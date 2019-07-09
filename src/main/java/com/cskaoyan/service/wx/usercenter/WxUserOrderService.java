package com.cskaoyan.service.wx.usercenter;

import com.cskaoyan.util.wxutil.BaseRespVo;

public interface WxUserOrderService {


    BaseRespVo getOrderListByShowtype(int userId,int showType, int page, int size);
}
