package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallCollect;
import com.cskaoyan.bean.CskaoyanMallCollectExample;
import com.cskaoyan.bean.wx.coreservices.MyCollect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CskaoyanMallCollectMapper {
    long countByExample(CskaoyanMallCollectExample example);

    int deleteByExample(CskaoyanMallCollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallCollect record);

    int insertSelective(CskaoyanMallCollect record);

    List<CskaoyanMallCollect> selectByExample(CskaoyanMallCollectExample example);

    CskaoyanMallCollect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallCollect record, @Param("example") CskaoyanMallCollectExample example);

    int updateByExample(@Param("record") CskaoyanMallCollect record, @Param("example") CskaoyanMallCollectExample example);

    int updateByPrimaryKeySelective(CskaoyanMallCollect record);

    int updateByPrimaryKey(CskaoyanMallCollect record);

}