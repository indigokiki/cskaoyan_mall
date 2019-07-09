package com.cskaoyan.service.wx;

import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsExample;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsMapper;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/9 14:38
 */
@Service
public class WxGoodsServiceImpl implements WxGoodsService {
    @Autowired
    CskaoyanMallGoodsMapper goodsMapper;

    @Override
    public ResponseVo<Map> count() {
        //count
        CskaoyanMallGoodsExample goodsExample = new CskaoyanMallGoodsExample();
        goodsExample.createCriteria().andIdIsNotNull();
        long count = goodsMapper.countByExample(goodsExample);
        //判断，设置errno，errmsg
        HashMap<String, Object> data = new HashMap<>();
        data.put("count",count);
        ResponseVo<Map> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(data);
        return responseVo;
    }

    @Override
    public ResponseVo<Map> goodsRelated(int id) {
        //categoryid
        Integer categoryid = goodsMapper.getCategoryidByGoodsid(id);
        //goodsRelatedList
        PageHelper.startPage(1,6);
        CskaoyanMallGoodsExample goodsExample = new CskaoyanMallGoodsExample();
        goodsExample.createCriteria().andCategoryIdEqualTo(categoryid);
        List<CskaoyanMallGoods> goodsList = goodsMapper.selectByExample(goodsExample);
        //判断，设置errno，errmsg
        HashMap<String, Object> data = new HashMap<>();
        data.put("goodsList",goodsList);
        ResponseVo<Map> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(data);
        return responseVo;
    }
}
