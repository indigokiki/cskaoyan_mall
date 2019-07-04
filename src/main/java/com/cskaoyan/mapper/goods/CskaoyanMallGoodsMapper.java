package com.cskaoyan.mapper.goods;

import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CskaoyanMallGoodsMapper {
    long countByExample(CskaoyanMallGoodsExample example);

    int deleteByExample(CskaoyanMallGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallGoods record);

    int insertSelective(CskaoyanMallGoods record);

    List<CskaoyanMallGoods> selectByExampleWithBLOBs(CskaoyanMallGoodsExample example);

    List<CskaoyanMallGoods> selectByExample(CskaoyanMallGoodsExample example);

    CskaoyanMallGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallGoods record, @Param("example") CskaoyanMallGoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") CskaoyanMallGoods record, @Param("example") CskaoyanMallGoodsExample example);

    int updateByExample(@Param("record") CskaoyanMallGoods record, @Param("example") CskaoyanMallGoodsExample example);

    int updateByPrimaryKeySelective(CskaoyanMallGoods record);

    int updateByPrimaryKeyWithBLOBs(CskaoyanMallGoods record);

    int updateByPrimaryKey(CskaoyanMallGoods record);
}