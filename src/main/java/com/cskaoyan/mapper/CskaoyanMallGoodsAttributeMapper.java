package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallGoodsAttribute;
import com.cskaoyan.bean.CskaoyanMallGoodsAttributeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallGoodsAttributeMapper {
    long countByExample(CskaoyanMallGoodsAttributeExample example);

    int deleteByExample(CskaoyanMallGoodsAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallGoodsAttribute record);

    int insertSelective(CskaoyanMallGoodsAttribute record);

    List<CskaoyanMallGoodsAttribute> selectByExample(CskaoyanMallGoodsAttributeExample example);

    CskaoyanMallGoodsAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallGoodsAttribute record, @Param("example") CskaoyanMallGoodsAttributeExample example);

    int updateByExample(@Param("record") CskaoyanMallGoodsAttribute record, @Param("example") CskaoyanMallGoodsAttributeExample example);

    int updateByPrimaryKeySelective(CskaoyanMallGoodsAttribute record);

    int updateByPrimaryKey(CskaoyanMallGoodsAttribute record);
}