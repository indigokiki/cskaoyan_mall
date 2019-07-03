package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallOrder;
import com.cskaoyan.bean.CskaoyanMallOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallOrderMapper {
    long countByExample(CskaoyanMallOrderExample example);

    int deleteByExample(CskaoyanMallOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallOrder record);

    int insertSelective(CskaoyanMallOrder record);

    List<CskaoyanMallOrder> selectByExample(CskaoyanMallOrderExample example);

    CskaoyanMallOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallOrder record, @Param("example") CskaoyanMallOrderExample example);

    int updateByExample(@Param("record") CskaoyanMallOrder record, @Param("example") CskaoyanMallOrderExample example);

    int updateByPrimaryKeySelective(CskaoyanMallOrder record);

    int updateByPrimaryKey(CskaoyanMallOrder record);
}