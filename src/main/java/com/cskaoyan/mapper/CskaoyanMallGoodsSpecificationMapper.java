package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallGoodsSpecification;
import com.cskaoyan.bean.CskaoyanMallGoodsSpecificationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallGoodsSpecificationMapper {
    long countByExample(CskaoyanMallGoodsSpecificationExample example);

    int deleteByExample(CskaoyanMallGoodsSpecificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallGoodsSpecification record);

    int insertSelective(CskaoyanMallGoodsSpecification record);

    List<CskaoyanMallGoodsSpecification> selectByExample(CskaoyanMallGoodsSpecificationExample example);

    CskaoyanMallGoodsSpecification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallGoodsSpecification record, @Param("example") CskaoyanMallGoodsSpecificationExample example);

    int updateByExample(@Param("record") CskaoyanMallGoodsSpecification record, @Param("example") CskaoyanMallGoodsSpecificationExample example);

    int updateByPrimaryKeySelective(CskaoyanMallGoodsSpecification record);

    int updateByPrimaryKey(CskaoyanMallGoodsSpecification record);
}