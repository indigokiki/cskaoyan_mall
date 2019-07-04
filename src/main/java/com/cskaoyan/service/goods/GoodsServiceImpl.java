package com.cskaoyan.service.goods;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsExample;
import com.cskaoyan.mapper.CskaoyanMallBrandMapper;
import com.cskaoyan.mapper.CskaoyanMallCategoryMapper;
import com.cskaoyan.mapper.CskaoyanMallCommentMapper;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsMapper;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.goods.ValueAndLabel;
import com.cskaoyan.util.goods.ValueLabelAndChildren;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-03 20:32
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    CskaoyanMallGoodsMapper goodsMapper;

    @Autowired
    CskaoyanMallBrandMapper brandMapper;

    @Autowired
    CskaoyanMallCategoryMapper categoryMapper;

    @Autowired
    CskaoyanMallCommentMapper commentMapper;

    @Override
    public Page<CskaoyanMallGoods> selectGoodsPageWithSnOrName(int page, int limit, String goodsSn, String name) {
        Page<CskaoyanMallGoods> goodsPage = new Page<>();

        CskaoyanMallGoodsExample goodsExample = new CskaoyanMallGoodsExample();
        CskaoyanMallGoodsExample.Criteria criteria = goodsExample.createCriteria();
        if (goodsSn != null && goodsSn != ""){
            criteria.andGoodsSnEqualTo(goodsSn);
        }
        if (name != null && name != ""){
            name = "%" + name + "%";
            criteria.andNameLike(name);
        }
        criteria.andDeletedEqualTo(false);
        PageHelper.startPage(page, limit);
        List<CskaoyanMallGoods> goodsList = goodsMapper.selectByExample(goodsExample);
        goodsPage.setItems(goodsList);

        int count = (int) goodsMapper.countByExample(goodsExample);
        goodsPage.setTotal(count);

        return goodsPage;
    }

    @Override
    public int deleteGoods(CskaoyanMallGoods goods) {
        int i = goodsMapper.deleteByPrimaryKey(goods.getId());
        return i;
    }

    @Override
    public int changeGoodsDeleted(CskaoyanMallGoods goods) {
        if (goods.getDeleted() == true){
            goods.setDeleted(false);
        }else {
            goods.setDeleted(true);
        }

        int i = goodsMapper.updateByPrimaryKey(goods);
        return i;
    }

    @Override
    public List<ValueAndLabel> getAllBrandsIdAndName() {
        List<CskaoyanMallBrand> brands = brandMapper.selectByExample(new CskaoyanMallBrandExample());
        List<ValueAndLabel> list = new ArrayList<>();

        for (CskaoyanMallBrand brand : brands){
            ValueAndLabel valueAndLabel = new ValueAndLabel();
            valueAndLabel.setValue(brand.getId());
            valueAndLabel.setLabel(brand.getName());
            list.add(valueAndLabel);
        }

        return list;
    }

    @Override
    public List<ValueLabelAndChildren> getAllCategorysIdAndName() {
        //1.获得所有pid=0的category的value=id，label=name，每一个作为list其中一项的value和name
        List<ValueLabelAndChildren> list = new ArrayList<>();

        //查所有pid=0的valueandname
        CskaoyanMallCategoryExample categoryExample = new CskaoyanMallCategoryExample();
        CskaoyanMallCategoryExample.Criteria categoryCriteria = categoryExample.createCriteria();
        categoryCriteria.andPidEqualTo(0);
        List<CskaoyanMallCategory> categories = categoryMapper.selectByExample(categoryExample);

        for (CskaoyanMallCategory category : categories){
            ValueLabelAndChildren<List> valueLabelAndChildren = new ValueLabelAndChildren<>();
            Integer id = category.getId();
            valueLabelAndChildren.setValue(id);
            valueLabelAndChildren.setLabel(category.getName());

            //2.用每一个pid=0的id,查所有pid=id的category的value=id，label=name，每一个作为children的其中一项valueandname
            List<ValueAndLabel> categorysIdAndNameByPid = getCategorysIdAndNameByPid(id);
            valueLabelAndChildren.setChildren(categorysIdAndNameByPid);

            list.add(valueLabelAndChildren);
        }


        return list;
    }

    @Override
    public List<ValueAndLabel> getCategorysIdAndNameByPid(int pid) {
        CskaoyanMallCategoryExample categoryExample = new CskaoyanMallCategoryExample();
        CskaoyanMallCategoryExample.Criteria categoryCriteria = categoryExample.createCriteria();
        categoryCriteria.andPidEqualTo(pid);

        List<CskaoyanMallCategory> categories = categoryMapper.selectByExample(categoryExample);

        List<ValueAndLabel> list = new ArrayList<>();

        for (CskaoyanMallCategory category : categories){
            ValueAndLabel valueAndLabel = new ValueAndLabel();
            valueAndLabel.setValue(category.getId());
            valueAndLabel.setLabel(category.getName());
            list.add(valueAndLabel);
        }
        return list;
    }

    @Override
    public Page<CskaoyanMallComment> selectCommentsPageWithSnOrName(int page, int limit, Integer userId, Integer valueId) {
        Page<CskaoyanMallComment> commentPage = new Page<>();

        CskaoyanMallCommentExample commentExample = new CskaoyanMallCommentExample();
        CskaoyanMallCommentExample.Criteria commentCriteria = commentExample.createCriteria();
        if (userId != null && userId != 0){
            commentCriteria.andUserIdEqualTo(userId);
        }
        if (valueId != null && valueId != 0){
            commentCriteria.andValueIdEqualTo(valueId);
        }
        commentCriteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, limit);
        List<CskaoyanMallComment> commentList = commentMapper.selectByExample(commentExample);
        commentPage.setItems(commentList);

        int count = (int) commentMapper.countByExample(commentExample);
        commentPage.setTotal(count);

        return commentPage;
    }


}
