package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallUserFormid;
import com.cskaoyan.bean.CskaoyanMallUserFormidExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallUserFormidMapper {
    long countByExample(CskaoyanMallUserFormidExample example);

    int deleteByExample(CskaoyanMallUserFormidExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallUserFormid record);

    int insertSelective(CskaoyanMallUserFormid record);

    List<CskaoyanMallUserFormid> selectByExample(CskaoyanMallUserFormidExample example);

    CskaoyanMallUserFormid selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallUserFormid record, @Param("example") CskaoyanMallUserFormidExample example);

    int updateByExample(@Param("record") CskaoyanMallUserFormid record, @Param("example") CskaoyanMallUserFormidExample example);

    int updateByPrimaryKeySelective(CskaoyanMallUserFormid record);

    int updateByPrimaryKey(CskaoyanMallUserFormid record);
}