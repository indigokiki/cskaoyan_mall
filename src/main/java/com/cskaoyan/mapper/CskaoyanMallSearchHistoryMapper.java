package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallSearchHistory;
import com.cskaoyan.bean.CskaoyanMallSearchHistoryExample;
import com.cskaoyan.bean.mallmanage.History;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallSearchHistoryMapper {
    long countByExample(CskaoyanMallSearchHistoryExample example);

    int deleteByExample(CskaoyanMallSearchHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallSearchHistory record);

    int insertSelective(CskaoyanMallSearchHistory record);

    List<CskaoyanMallSearchHistory> selectByExample(CskaoyanMallSearchHistoryExample example);

    CskaoyanMallSearchHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallSearchHistory record, @Param("example") CskaoyanMallSearchHistoryExample example);

    int updateByExample(@Param("record") CskaoyanMallSearchHistory record, @Param("example") CskaoyanMallSearchHistoryExample example);

    int updateByPrimaryKeySelective(CskaoyanMallSearchHistory record);

    int updateByPrimaryKey(CskaoyanMallSearchHistory record);

    List<History> searchHistory(@Param("userid") String userid);

    int insertSearch(@Param("his") CskaoyanMallSearchHistory history);

    int deleteHistory(@Param("userid") String userid);
}