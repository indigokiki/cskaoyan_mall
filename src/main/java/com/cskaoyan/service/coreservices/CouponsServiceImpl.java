package com.cskaoyan.service.coreservices;

import com.cskaoyan.bean.CskaoyanMallCoupon;
import com.cskaoyan.bean.CskaoyanMallCouponExample;
import com.cskaoyan.bean.CskaoyanMallCouponUser;
import com.cskaoyan.bean.CskaoyanMallCouponUserExample;
import com.cskaoyan.mapper.CskaoyanMallCouponMapper;
import com.cskaoyan.mapper.CskaoyanMallCouponUserMapper;
import com.cskaoyan.mapper.CskaoyanMallUserMapper;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouponsServiceImpl implements CouponsService {
    @Autowired
    CskaoyanMallCouponMapper cskaoyanMallCouponMapper;
    @Autowired
    CskaoyanMallCouponUserMapper cskaoyanMallCouponUserMapper;

    @Override
    public ResponseVo getcouponlist(int page, int status, int size) {
        CskaoyanMallCouponExample cskaoyanMallCouponExample = new CskaoyanMallCouponExample();
        CskaoyanMallCouponExample.Criteria criteria = cskaoyanMallCouponExample.createCriteria();
        criteria.andDeletedNotEqualTo(true);
        PageHelper.startPage(page,size);
        List<CskaoyanMallCoupon> issues = cskaoyanMallCouponMapper.selectByStatusandId(status);
        Map<Object,Object> map = new HashMap<>();
        map.put("data",issues);
        map.put("count",6);
        ResponseVo<Map> responseVo = new ResponseVo<>();
        responseVo.setData(map);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }

    @Override
    public ResponseVo exchangecoupon(String code) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        CskaoyanMallCoupon cskaoyanMallCoupon = new CskaoyanMallCoupon();
        Integer id = cskaoyanMallCouponMapper.selectByCode(code);
        cskaoyanMallCoupon = cskaoyanMallCouponMapper.selectByPrimaryKey(id);
        if(id!=0) {
            cskaoyanMallCouponUserMapper.insertSelective(setUser(cskaoyanMallCoupon));
            responseVo.setErrmsg("兑换成功");
            responseVo.setErrno(0);
            return responseVo;
        }
        responseVo.setErrmsg("优惠券不正确");
        responseVo.setErrno(742);
        return  responseVo;
    }

    private CskaoyanMallCouponUser setUser(CskaoyanMallCoupon cskaoyanMallCoupon) {
        CskaoyanMallCouponUser cskaoyanMallCouponUser = new CskaoyanMallCouponUser();
        cskaoyanMallCouponUser.setId(cskaoyanMallCouponUserMapper.selectLastUpdate());
        cskaoyanMallCouponUser.setUserId(1);
        cskaoyanMallCouponUser.setCouponId(cskaoyanMallCoupon.getId());
        cskaoyanMallCouponUser.setStatus(cskaoyanMallCoupon.getStatus());
        cskaoyanMallCouponUser.setStartTime(cskaoyanMallCoupon.getStartTime());
        cskaoyanMallCouponUser.setEndTime(cskaoyanMallCoupon.getEndTime());
        cskaoyanMallCouponUser.setAddTime(cskaoyanMallCoupon.getAddTime());
        cskaoyanMallCouponUser.setUpdateTime(cskaoyanMallCoupon.getUpdateTime());
        cskaoyanMallCouponUser.setDeleted(cskaoyanMallCoupon.getDeleted());
        return cskaoyanMallCouponUser;
    }

}
