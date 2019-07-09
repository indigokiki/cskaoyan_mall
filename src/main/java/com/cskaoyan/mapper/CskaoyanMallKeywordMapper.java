package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallKeyword;
import com.cskaoyan.bean.CskaoyanMallKeywordExample;
import com.cskaoyan.bean.mallmanage.History;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallKeywordMapper {
    long countByExample(CskaoyanMallKeywordExample example);

    int deleteByExample(CskaoyanMallKeywordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallKeyword record);

    int insertSelective(CskaoyanMallKeyword record);

    List<CskaoyanMallKeyword> selectByExample(CskaoyanMallKeywordExample example);

    CskaoyanMallKeyword selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallKeyword record, @Param("example") CskaoyanMallKeywordExample example);

    int updateByExample(@Param("record") CskaoyanMallKeyword record, @Param("example") CskaoyanMallKeywordExample example);

    int updateByPrimaryKeySelective(CskaoyanMallKeyword record);

    int updateByPrimaryKey(CskaoyanMallKeyword record);

    int selectLastUpdate();

    List helper(@Param("key") String keyword);
}