package com.cskaoyan.service.goods;

import com.cskaoyan.bean.goods.CskaoyanMallGoodsProduct;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsProductExample;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-04 23:57
 */
@Service
public class GoodsProductServiceImpl implements GoodsProductService {
    @Autowired
    CskaoyanMallGoodsProductMapper productMapper;

    @Override
    public int insertProductSelective(CskaoyanMallGoodsProduct product) {
        int i = productMapper.insertSelective(product);
        return i;
    }

    @Override
    public List<CskaoyanMallGoodsProduct> selectProductsByGoodsid(Integer goodsid) {
        CskaoyanMallGoodsProductExample productExample = new CskaoyanMallGoodsProductExample();
        CskaoyanMallGoodsProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsid).andDeletedEqualTo(false);
        List<CskaoyanMallGoodsProduct> products = productMapper.selectByExample(productExample);
        return products;
    }

    @Override
    public int setDeletedFalseByGoodsid(Integer goodsId) {
        return productMapper.setDeletedFalseByGoodsid(goodsId);
    }

    @Override
    public int updateProducts(List<CskaoyanMallGoodsProduct> products) {
        int i = 0;
        for (CskaoyanMallGoodsProduct product:products){
            i += productMapper.updateByPrimaryKey(product);
        }
        if (i == products.size()){
            return 1;
        }else {
            return 0;
        }

    }
}
