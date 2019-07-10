package com.cskaoyan.service.popularize;

import com.cskaoyan.bean.popularize.Address;
import com.cskaoyan.bean.popularize.Cart;
import com.cskaoyan.bean.popularize.Coupon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICartService {

    public List<Cart> list(Integer userId);

    public HashMap<String, Object> findTotal(Integer userId);

    void checked(Integer checked, Integer[] productId);

    void update(Cart cart);
    public Map<String,Object> checkout(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId, Integer userId);

    List<Coupon> findAllCoupon();


    List<Address> listAddress(Integer userId);
}
