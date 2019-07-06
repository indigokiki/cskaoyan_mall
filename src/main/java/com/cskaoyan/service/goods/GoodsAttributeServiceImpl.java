package com.cskaoyan.service.goods;

import com.cskaoyan.bean.goods.CskaoyanMallGoodsAttribute;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsAttributeExample;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsAttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-04 23:56
 */
@Service
public class GoodsAttributeServiceImpl implements GoodsAttributeService {
    @Autowired
    CskaoyanMallGoodsAttributeMapper attributeMapper;

    @Override
    public int insertAttributeSelective(CskaoyanMallGoodsAttribute attribute) {
        int i = attributeMapper.insertSelective(attribute);
        return i;
    }

    @Override
    public List<CskaoyanMallGoodsAttribute> selectAttributesByGoodsid(Integer goodsid) {
        CskaoyanMallGoodsAttributeExample attributeExample = new CskaoyanMallGoodsAttributeExample();
        CskaoyanMallGoodsAttributeExample.Criteria criteria = attributeExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsid).andDeletedEqualTo(false);
        List<CskaoyanMallGoodsAttribute> attributes = attributeMapper.selectByExample(attributeExample);
        return attributes;
    }

    @Override
    public int setDeletedFalseByGoodsid(Integer goodsId) {
        return attributeMapper.setDeletedFalseByGoodsid(goodsId);
    }

    @Override
    public int updateAttributes(List<CskaoyanMallGoodsAttribute> attributes) {
        int i = 0;
        for (CskaoyanMallGoodsAttribute attribute : attributes){
            i += attributeMapper.updateByPrimaryKey(attribute);
        }
        if (i == attributes.size()){
            return 1;
        }else {
            return 0;
        }
    }
}
