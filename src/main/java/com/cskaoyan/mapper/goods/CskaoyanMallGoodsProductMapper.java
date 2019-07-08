package com.cskaoyan.mapper.goods;

import com.cskaoyan.bean.goods.CskaoyanMallGoodsProduct;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsProductExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CskaoyanMallGoodsProductMapper {
    long countByExample(CskaoyanMallGoodsProductExample example);

    int deleteByExample(CskaoyanMallGoodsProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallGoodsProduct record);

    int insertSelective(CskaoyanMallGoodsProduct record);

    List<CskaoyanMallGoodsProduct> selectByExample(CskaoyanMallGoodsProductExample example);

    CskaoyanMallGoodsProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallGoodsProduct record, @Param("example") CskaoyanMallGoodsProductExample example);

    int updateByExample(@Param("record") CskaoyanMallGoodsProduct record, @Param("example") CskaoyanMallGoodsProductExample example);

    int updateByPrimaryKeySelective(CskaoyanMallGoodsProduct record);

    int updateByPrimaryKey(CskaoyanMallGoodsProduct record);

    int setDeletedFalseByGoodsid(@Param("goodsid") Integer goodsId);
}