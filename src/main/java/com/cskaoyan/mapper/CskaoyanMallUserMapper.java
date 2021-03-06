package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallUser;
import com.cskaoyan.bean.CskaoyanMallUserExample;
import com.cskaoyan.util.wxutil.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CskaoyanMallUserMapper {

    long countByExample(CskaoyanMallUserExample example);

    int deleteByExample(CskaoyanMallUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallUser record);

    int insertSelective(CskaoyanMallUser record);

    List<CskaoyanMallUser> selectByExample(CskaoyanMallUserExample example);

    CskaoyanMallUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallUser record, @Param("example") CskaoyanMallUserExample example);

    int updateByExample(@Param("record") CskaoyanMallUser record, @Param("example") CskaoyanMallUserExample example);

    int updateByPrimaryKeySelective(CskaoyanMallUser record);

    int updateByPrimaryKey(CskaoyanMallUser record);

    UserInfo getUserInfo(@Param("id")int id);
}