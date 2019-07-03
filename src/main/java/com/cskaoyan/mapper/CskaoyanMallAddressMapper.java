package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallAddress;
import com.cskaoyan.bean.CskaoyanMallAddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallAddressMapper {
    long countByExample(CskaoyanMallAddressExample example);

    int deleteByExample(CskaoyanMallAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallAddress record);

    int insertSelective(CskaoyanMallAddress record);

    List<CskaoyanMallAddress> selectByExample(CskaoyanMallAddressExample example);

    CskaoyanMallAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallAddress record, @Param("example") CskaoyanMallAddressExample example);

    int updateByExample(@Param("record") CskaoyanMallAddress record, @Param("example") CskaoyanMallAddressExample example);

    int updateByPrimaryKeySelective(CskaoyanMallAddress record);

    int updateByPrimaryKey(CskaoyanMallAddress record);
}