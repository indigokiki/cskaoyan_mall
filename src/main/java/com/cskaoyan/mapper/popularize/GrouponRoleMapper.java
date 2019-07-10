package com.cskaoyan.mapper.popularize;

import com.cskaoyan.bean.popularize.GrouponRole;
import com.cskaoyan.bean.popularize.GrouponRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GrouponRoleMapper {
    long countByExample(GrouponRoleExample example);

    int deleteByExample(GrouponRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GrouponRole record);

    int insertSelective(GrouponRole record);

    List<GrouponRole> selectByExample(GrouponRoleExample example);

    GrouponRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GrouponRole record, @Param("example") GrouponRoleExample example);

    int updateByExample(@Param("record") GrouponRole record, @Param("example") GrouponRoleExample example);

    int updateByPrimaryKeySelective(GrouponRole record);

    int updateByPrimaryKey(GrouponRole record);
}