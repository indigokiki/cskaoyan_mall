package com.cskaoyan.service.popularize.impl;

import com.cskaoyan.bean.popularize.Coupon;
import com.cskaoyan.bean.popularize.CouponExample;
import com.cskaoyan.bean.popularize.vo.ResManager;
import com.cskaoyan.mapper.popularize.CouponMapper;
import com.cskaoyan.service.popularize.ICouponService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements ICouponService {

    @Autowired
    CouponMapper couponMapper;
    @Override
    public ResManager<Coupon> findAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteria = couponExample.createCriteria();
        List<Coupon> couponss = couponMapper.findAll();
        ResManager<Coupon> adResManager = new ResManager<>();
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(couponss);
        adResManager.setItems(couponPageInfo.getList());
        adResManager.setTotal((int) couponPageInfo.getTotal());
        return adResManager;
    }

    @Override
    public int update(Coupon coupon) {
        int i = couponMapper.updateByPrimaryKey(coupon);
        return i;
    }

    @Override
    public void delete(Coupon coupon) {
        couponMapper.deleteByPrimaryKey(coupon.getId());
    }

    @Override
    public void create(Coupon coupon) {
        couponMapper.insert(coupon);
    }

    @Override
    public ResManager<Coupon> list(String name, Short status, Short type, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteria = couponExample.createCriteria();
        if (name!=null){
            criteria.andNameLike("%"+name+"%");
        }
        if(status!=null){
            criteria.andStatusEqualTo(status);
        }
        if(type!=null){
            criteria.andTypeEqualTo(type);
        }

        List<Coupon> list = couponMapper.selectByExample(couponExample);
        ResManager<Coupon> resManager = new ResManager<>();
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(list);
        resManager.setTotal((int) couponPageInfo.getTotal());
        resManager.setItems(couponPageInfo.getList());
        return resManager;
    }

    @Override
    public Coupon findOne(Integer id) {
        Coupon coupon = couponMapper.selectByPrimaryKey(id);
        return coupon;
    }
}
