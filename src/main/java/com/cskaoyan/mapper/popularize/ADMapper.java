package com.cskaoyan.mapper.popularize;

import com.cskaoyan.bean.popularize.AD;
import com.cskaoyan.bean.popularize.ADExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ADMapper {
    long countByExample(ADExample example);

    int deleteByExample(ADExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AD record);

    int insertSelective(AD record);

    List<AD> selectByExample(ADExample example);

    AD selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AD record, @Param("example") ADExample example);

    int updateByExample(@Param("record") AD record, @Param("example") ADExample example);

    int updateByPrimaryKeySelective(AD record);

    int updateByPrimaryKey(AD record);
}