package com.cskaoyan.service.goods;

import com.cskaoyan.bean.goods.CskaoyanMallComment;
import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.bean.goods.GoodsDetail;
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

    //添加goods,要先判断name是否存在
    int insertGoodsSelective(CskaoyanMallGoods goods);
    //添加goods,并返回goodsid;这里goodsid=goodSn
    Integer getGoodsidWhenInsertGoods(CskaoyanMallGoods goods);
    //判断name是否已存在
    Boolean goodsNameExist(String goodsname);
    //判断编号goodsSn是否已存在
    Boolean goodsSnExist(String goodsSn);

    GoodsDetail getGoodsDetailById(Integer id);

    //用id查goods,用于goodsdetail
    CskaoyanMallGoods selectGoodsById(Integer id);

    //用id查categoryids；goods表里的categoryid是子类目，用它的pid查父类目
    int[] getCategoryidsByGoodsid(Integer id);

    //商品评论用，判断回复content是否存在
    Boolean commentContentExist(Integer commentid);
    //商品评论用，增加comment的content
    int updateContentOfComment(Integer commentid, String content);
    //商品评论用，删除comment,不是真的删掉，而是把deleted设为1
    int changeCommentDeleted(CskaoyanMallComment comment);

    //修改good表及相关表的deleted
    int changeGoodsAndRelatedDeleted(CskaoyanMallGoods goods);
    //商品更新用
    int updateGoods(CskaoyanMallGoods goods);
}
