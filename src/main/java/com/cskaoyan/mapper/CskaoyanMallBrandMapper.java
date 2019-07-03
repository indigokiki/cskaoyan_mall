package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallBrand;
import com.cskaoyan.bean.CskaoyanMallBrandExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallBrandMapper {
    long countByExample(CskaoyanMallBrandExample example);

    int deleteByExample(CskaoyanMallBrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallBrand record);

    int insertSelective(CskaoyanMallBrand record);

    List<CskaoyanMallBrand> selectByExample(CskaoyanMallBrandExample example);

    CskaoyanMallBrand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallBrand record, @Param("example") CskaoyanMallBrandExample example);

    int updateByExample(@Param("record") CskaoyanMallBrand record, @Param("example") CskaoyanMallBrandExample example);

    int updateByPrimaryKeySelective(CskaoyanMallBrand record);

    int updateByPrimaryKey(CskaoyanMallBrand record);
}