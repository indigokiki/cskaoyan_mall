package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallOrderGoods;
import com.cskaoyan.bean.CskaoyanMallOrderGoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallOrderGoodsMapper {
    long countByExample(CskaoyanMallOrderGoodsExample example);

    int deleteByExample(CskaoyanMallOrderGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallOrderGoods record);

    int insertSelective(CskaoyanMallOrderGoods record);

    List<CskaoyanMallOrderGoods> selectByExample(CskaoyanMallOrderGoodsExample example);

    CskaoyanMallOrderGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallOrderGoods record, @Param("example") CskaoyanMallOrderGoodsExample example);

    int updateByExample(@Param("record") CskaoyanMallOrderGoods record, @Param("example") CskaoyanMallOrderGoodsExample example);

    int updateByPrimaryKeySelective(CskaoyanMallOrderGoods record);

    int updateByPrimaryKey(CskaoyanMallOrderGoods record);
}