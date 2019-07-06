package com.cskaoyan.service.mallmanage;

import com.cskaoyan.bean.Result;

public interface OrderService {

    Result getAllOrders(int page, int limit, String sort, String order, String orderStatusArray, String userId, String orderSn);

    Result dedetail(String id);
}
