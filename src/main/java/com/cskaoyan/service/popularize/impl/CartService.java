package com.cskaoyan.service.popularize.impl;

import com.cskaoyan.bean.popularize.System;
import com.cskaoyan.bean.popularize.*;
import com.cskaoyan.mapper.popularize.*;
import com.cskaoyan.service.popularize.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("myCartservice")
public class CartService implements ICartService {

    @Autowired

    CartMapper cartMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    SystemMapper systemMapper;

    @Autowired
    GrouponRoleMapper grouponRoleMapper;
    @Override
    public List<Cart> list(Integer userId) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return cartMapper.selectByExample(cartExample);
    }

    @Override
    public HashMap<String, Object> findTotal(Integer userId) {
        HashMap<String, Object> map = new HashMap<>();
        List<Cart> list = list(userId);
        CartTotal cartTotal = new CartTotal();
        Integer checkedGoodsCount=0;
        Double checkedGoodsAmount=0.0;
        Integer goodsCount=0;
        Double goodsAmount=0.0;
        for (Cart cart : list) {
            goodsCount+=cart.getNumber();
            goodsAmount = goodsAmount + cart.getPrice() * cart.getNumber();
            if(cart.getChecked()==true){
                checkedGoodsCount+=cart.getNumber();
                checkedGoodsAmount+=cart.getPrice()*cart.getNumber();
            }
        }
        cartTotal.setCheckedGoodsAmount(checkedGoodsAmount);
        cartTotal.setCheckedGoodsCount(checkedGoodsCount);
        cartTotal.setGoodsAmount(goodsAmount);
        cartTotal.setGoodsCount(goodsCount);
        map.put("cartList",list);
        map.put("cartTotal",cartTotal);
        return map;
    }

    @Override
    public void checked(Integer checked, Integer[] productId) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        List<Integer> list = Arrays.asList(productId);
        criteria.andProductIdIn(list);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        for (Cart cart : carts) {
            cart.setChecked(checked==1?true:false);
            cartMapper.updateByPrimaryKey(cart);
        }

    }

    @Override
    public void update(Cart cart) {
        Cart cart1 = cartMapper.selectByPrimaryKey(cart.getId());
        cart1.setNumber(cart.getNumber());
        cartMapper.updateByPrimaryKey(cart1);
    }

    @Override
    public Map<String,Object> checkout(Integer cartId,Integer addressId,Integer couponId,Integer grouponRulesId,Integer userId){
        HashMap<String, Object> map = new HashMap<>();

        //购物车
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andCheckedEqualTo(true).andUserIdEqualTo(userId);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        map.put("checkedGoodsList",carts);
        Double goodsTotalPrice=0.0;
        for (Cart cart : carts) {
            goodsTotalPrice+=cart.getPrice()*cart.getNumber();
        }
        map.put("goodsTotalPrice",goodsTotalPrice);
        //地址
        Address address = addressMapper.selectByPrimaryKey(addressId);
        map.put("checkedAddress",address);
        map.put("addressId",addressId);
        //优惠券
        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteriaCoupon = couponExample.createCriteria();
        criteriaCoupon.andMinLessThanOrEqualTo(new BigDecimal(goodsTotalPrice))
                .andStatusEqualTo((short)0);
        List<Coupon> coupons = couponMapper.selectByExample(couponExample);
        map.put("availableCouponLength",coupons.size());
        map.put("couponId",couponId);
        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        Double couponPrice=0.0;
        if(coupon!=null){
            couponPrice=coupon.getDiscount().doubleValue();
        }
        map.put("couponPrice",couponPrice);

        //运费
        SystemExample systemExample = new SystemExample();
        SystemExample.Criteria criteriaSys = systemExample.createCriteria();
        criteriaSys.andKeyNameEqualTo("cskaoyan_mall_express_freight_value");
        List<System> systems = systemMapper.selectByExample(systemExample);
        Double keyValue = Double.parseDouble(systems.get(0).getKeyValue());
        map.put("freightPrice",goodsTotalPrice>=88?0.0:keyValue);

        //团购
        map.put("grouponRulesId",grouponRulesId);
        GrouponRole grouponRole = grouponRoleMapper.selectByPrimaryKey(grouponRulesId);
        Double discount=0.0;
        if(grouponRole!=null){

            discount = grouponRole.getDiscount().doubleValue();
        }
        map.put("grouponPrice",discount);

        Double orderTotalPrice=goodsTotalPrice+(goodsTotalPrice>=88?0.0:keyValue)-discount-couponPrice;
        map.put("actualPrice",orderTotalPrice);
        map.put("orderTotalPrice",orderTotalPrice);
        return map;
    }

    @Override
    public List<Coupon> findAllCoupon() {
        //购物车
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andCheckedEqualTo(true);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        Double goodsTotalPrice=0.0;
        for (Cart cart : carts) {
            goodsTotalPrice+=cart.getPrice()*cart.getNumber();
        }
        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteriaCoupon = couponExample.createCriteria();
        criteriaCoupon.andMinLessThanOrEqualTo(new BigDecimal(goodsTotalPrice))
                .andStatusEqualTo((short)0);
        List<Coupon> coupons = couponMapper.selectByExample(couponExample);

        return coupons;
    }

    @Override
    public List<Address> listAddress(Integer userId) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(userId);
        List<Address> list = addressMapper.selectByExample(addressExample);
        return list;
    }
}
