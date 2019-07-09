package com.cskaoyan.mapper.goods;

import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsExample;
import com.cskaoyan.bean.mallmanage.FloorGood;
import com.cskaoyan.bean.mallmanage.SearchGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

    //新增
    //用name查goodsid
    Integer getGoodsidByName(@Param("name") String name);

    Integer getCategoryidByGoodsid(@Param("id") Integer id);


    //李岩：wx端商品查询；
    List<FloorGood> searchGoods(@Param("name")String name,@Param("sort") String sort,@Param("order") String order,@Param("categoryId") String categoryId);

    List<CskaoyanMallGoods> getGoodsByCategoryId(int categoryId);

}