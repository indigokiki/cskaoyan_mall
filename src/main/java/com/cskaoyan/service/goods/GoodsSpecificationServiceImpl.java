package com.cskaoyan.service.goods;

import com.cskaoyan.bean.goods.CskaoyanMallGoodsSpecification;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsSpecificationExample;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YangShuo
 * @date 2019-07-04 23:58
 */
@Service
public class GoodsSpecificationServiceImpl implements GoodsSpecificationService {
    @Autowired
    CskaoyanMallGoodsSpecificationMapper specificationMapper;

    @Override
    public int insertSpecificationSelective(CskaoyanMallGoodsSpecification specification) {
        int i = specificationMapper.insertSelective(specification);
        return i;
    }

    @Override
    public List<CskaoyanMallGoodsSpecification> selectSpecificationsByGoodsid(Integer goodsid) {
        CskaoyanMallGoodsSpecificationExample specificationExample = new CskaoyanMallGoodsSpecificationExample();
        CskaoyanMallGoodsSpecificationExample.Criteria criteria = specificationExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsid).andDeletedEqualTo(false);
        List<CskaoyanMallGoodsSpecification> specifications = specificationMapper.selectByExample(specificationExample);
        return specifications;
    }

    @Override
    public int setDeletedFalseByGoodsid(Integer goodsId) {
        return specificationMapper.setDeletedFalseByGoodsid(goodsId);
    }

    @Override
    public int updateSpecifications(List<CskaoyanMallGoodsSpecification> specifications) {
        int i = 0;
        for (CskaoyanMallGoodsSpecification specification:specifications){
            i += specificationMapper.updateByPrimaryKey(specification);
        }
        if (i == specifications.size()){
            return 1;
        }else {
            return 0;
        }
    }
}
