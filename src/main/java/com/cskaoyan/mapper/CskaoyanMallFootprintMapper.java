package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallFootprint;
import com.cskaoyan.bean.CskaoyanMallFootprintExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallFootprintMapper {
    long countByExample(CskaoyanMallFootprintExample example);

    int deleteByExample(CskaoyanMallFootprintExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallFootprint record);

    int insertSelective(CskaoyanMallFootprint record);

    List<CskaoyanMallFootprint> selectByExample(CskaoyanMallFootprintExample example);

    CskaoyanMallFootprint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallFootprint record, @Param("example") CskaoyanMallFootprintExample example);

    int updateByExample(@Param("record") CskaoyanMallFootprint record, @Param("example") CskaoyanMallFootprintExample example);

    int updateByPrimaryKeySelective(CskaoyanMallFootprint record);

    int updateByPrimaryKey(CskaoyanMallFootprint record);

    int insertFootprint(@Param("footprint") CskaoyanMallFootprint footprint);
}