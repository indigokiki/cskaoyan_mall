package com.cskaoyan.service.wx.usercenter;

import com.cskaoyan.bean.CskaoyanMallOrder;
import com.cskaoyan.bean.CskaoyanMallOrderExample;
import com.cskaoyan.mapper.CskaoyanMallOrderMapper;
import com.cskaoyan.util.wxutil.BaseRespVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxUserOrderServiceImpl implements WxUserOrderService {

    @Autowired
    CskaoyanMallOrderMapper cskaoyanMallOrderMapper;

    @Override
    public BaseRespVo getOrderListByShowtype(int userId, int showType, int page, int size) {
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
                criteria.andCommentsIsNull();
        }
        criteria.andUserIdEqualTo(userId);
        PageHelper.startPage(page,size);
        List<CskaoyanMallOrder> cskaoyanMallOrders = cskaoyanMallOrderMapper.selectByExample(cskaoyanMallOrderExample);
        return BaseRespVo.ok(cskaoyanMallOrders);
    }
}
