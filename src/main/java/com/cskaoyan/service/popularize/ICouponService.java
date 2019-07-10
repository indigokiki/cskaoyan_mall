package com.cskaoyan.service.popularize;


import com.cskaoyan.bean.popularize.Coupon;
import com.cskaoyan.bean.popularize.vo.ResManager;

public interface ICouponService {
    public ResManager<Coupon> findAll(Integer page, Integer limit);

    public int update(Coupon coupon);

    void delete(Coupon coupon);

    void create(Coupon coupon);

    ResManager<Coupon> list(String name, Short status, Short type, Integer page, Integer limit);

    Coupon findOne(Integer id);
}
