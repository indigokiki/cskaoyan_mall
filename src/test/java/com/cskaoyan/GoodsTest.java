package com.cskaoyan;

import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsExample;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsProduct;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsMapper;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-04 16:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GoodsTest {

    @Autowired
    CskaoyanMallGoodsMapper goodsMapper;
    @Autowired
    CskaoyanMallGoodsProductMapper productMapper;

    @Test
    public void test1(){
        CskaoyanMallGoods goods = new CskaoyanMallGoods();
        goods.setId(001);
        String[] strings = {"111", "222", "333"};
        goods.setGallery(strings);
        int i = goodsMapper.insertSelective(goods);
        System.out.println("i = " + i);
    }

    @Test
    public void test2(){

        CskaoyanMallGoods goods = goodsMapper.selectByPrimaryKey(1006002);
        System.out.println("goods = " + goods);
    }

    @Test
    public void test3(){
        CskaoyanMallGoodsProduct product = new CskaoyanMallGoodsProduct();
        String[] strings = {"111", "222", "333"};
        product.setSpecifications(strings);
        product.setId(001);
        int i = productMapper.insertSelective(product);
        System.out.println("i = " + i);
    }

    @Test
    public void test4(){
        CskaoyanMallGoodsProduct product = productMapper.selectByPrimaryKey(1);
        System.out.println("product = " + product);
    }
}
