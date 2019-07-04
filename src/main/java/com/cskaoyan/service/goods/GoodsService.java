package com.cskaoyan.service.goods;

import com.cskaoyan.bean.CskaoyanMallComment;
import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.goods.ValueAndLabel;
import com.cskaoyan.util.goods.ValueLabelAndChildren;

import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-03 20:31
 */
public interface GoodsService {
    //查看+查找商品+分页
    Page<CskaoyanMallGoods> selectGoodsPageWithSnOrName(int page, int limit, String goodsSn, String name);

    //删除商品
    int deleteGoods(CskaoyanMallGoods goods);
    //修改商品的deleted列
    int changeGoodsDeleted(CskaoyanMallGoods goods);

    //二、商品上架
    //查所有品牌的value=id,label=name，商品上架用
    List<ValueAndLabel> getAllBrandsIdAndName();
    //查所有分类的value=id,label=name，商品上架用
    List<ValueLabelAndChildren> getAllCategorysIdAndName();
    //查某个父类目下的所有分类的value=id,label=name
    List<ValueAndLabel> getCategorysIdAndNameByPid(int pid);


    Page<CskaoyanMallComment> selectCommentsPageWithSnOrName(int page, int limit, Integer userId, Integer valueId);
}
