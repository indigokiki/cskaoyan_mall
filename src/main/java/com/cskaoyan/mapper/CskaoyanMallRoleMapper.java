package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallRole;
import com.cskaoyan.bean.CskaoyanMallRoleExample;
import com.cskaoyan.bean.systemmanagement.CskaoyanMallMyRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallRoleMapper {
    long countByExample(CskaoyanMallRoleExample example);

    int deleteByExample(CskaoyanMallRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallRole record);

    int insertSelective(CskaoyanMallRole record);

    List<CskaoyanMallRole> selectByExample(CskaoyanMallRoleExample example);

    CskaoyanMallRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallRole record, @Param("example") CskaoyanMallRoleExample example);

    int updateByExample(@Param("record") CskaoyanMallRole record, @Param("example") CskaoyanMallRoleExample example);

    int updateByPrimaryKeySelective(CskaoyanMallRole record);

    int updateByPrimaryKey(CskaoyanMallRole record);

    List<CskaoyanMallMyRole> getValueandLabel();

    Integer selectLastUpdate();

    int delete(@Param("role") CskaoyanMallRole cskaoyanMallRole);
}