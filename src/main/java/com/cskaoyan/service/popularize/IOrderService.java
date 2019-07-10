package com.cskaoyan.service.popularize;

import java.util.HashMap;

public interface IOrderService {
    Integer insert(Integer addressId, Integer cartId, Integer couponId, Integer grouponLinkId, Integer grouponRulesId, String message, Integer userId);

    HashMap<String, Object> map(Integer showType, Integer page, Integer size);
}
