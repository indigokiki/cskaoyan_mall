package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallLog;
import com.cskaoyan.bean.CskaoyanMallLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallLogMapper {
    long countByExample(CskaoyanMallLogExample example);

    int deleteByExample(CskaoyanMallLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallLog record);

    int insertSelective(CskaoyanMallLog record);

    List<CskaoyanMallLog> selectByExample(CskaoyanMallLogExample example);

    CskaoyanMallLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallLog record, @Param("example") CskaoyanMallLogExample example);

    int updateByExample(@Param("record") CskaoyanMallLog record, @Param("example") CskaoyanMallLogExample example);

    int updateByPrimaryKeySelective(CskaoyanMallLog record);

    int updateByPrimaryKey(CskaoyanMallLog record);

    Integer selectLastUpdate();
}