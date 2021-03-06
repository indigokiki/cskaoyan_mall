package com.cskaoyan.mapper.goods;

import com.cskaoyan.bean.goods.CskaoyanMallGoodsAttribute;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsAttributeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

    //新增
    int setDeletedFalseByGoodsid(@Param("goodsid") Integer goodsId);
}