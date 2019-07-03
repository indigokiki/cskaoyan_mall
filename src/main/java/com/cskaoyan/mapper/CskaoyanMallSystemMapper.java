package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallSystem;
import com.cskaoyan.bean.CskaoyanMallSystemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallSystemMapper {
    long countByExample(CskaoyanMallSystemExample example);

    int deleteByExample(CskaoyanMallSystemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallSystem record);

    int insertSelective(CskaoyanMallSystem record);

    List<CskaoyanMallSystem> selectByExample(CskaoyanMallSystemExample example);

    CskaoyanMallSystem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallSystem record, @Param("example") CskaoyanMallSystemExample example);

    int updateByExample(@Param("record") CskaoyanMallSystem record, @Param("example") CskaoyanMallSystemExample example);

    int updateByPrimaryKeySelective(CskaoyanMallSystem record);

    int updateByPrimaryKey(CskaoyanMallSystem record);
}