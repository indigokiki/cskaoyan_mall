package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallCoupon;
import com.cskaoyan.bean.CskaoyanMallCouponExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallCouponMapper {
    long countByExample(CskaoyanMallCouponExample example);

    int deleteByExample(CskaoyanMallCouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallCoupon record);

    int insertSelective(CskaoyanMallCoupon record);

    List<CskaoyanMallCoupon> selectByExample(CskaoyanMallCouponExample example);

    CskaoyanMallCoupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallCoupon record, @Param("example") CskaoyanMallCouponExample example);

    int updateByExample(@Param("record") CskaoyanMallCoupon record, @Param("example") CskaoyanMallCouponExample example);

    int updateByPrimaryKeySelective(CskaoyanMallCoupon record);

    int updateByPrimaryKey(CskaoyanMallCoupon record);
}