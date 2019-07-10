package com.cskaoyan.service.coreservices;

import com.cskaoyan.bean.CskaoyanMallBrand;
import com.cskaoyan.bean.CskaoyanMallCollect;
import com.cskaoyan.bean.CskaoyanMallCollectExample;
import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.bean.wx.coreservices.MyCollect;
import com.cskaoyan.mapper.CskaoyanMallCollectMapper;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsMapper;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CskaoyanMallCollectServiceImpl implements CskaoyanMallCollectService {
    @Autowired
    CskaoyanMallGoodsMapper cskaoyanMallGoodsMapper;
    @Override
    public ResponseVo getcollectlist(int type, int page, int size) {
        CskaoyanMallCollectExample cskaoyanMallCollectExample = new CskaoyanMallCollectExample();
        CskaoyanMallCollectExample.Criteria criteria = cskaoyanMallCollectExample.createCriteria();
        PageHelper.startPage(page,size);
        List<MyCollect> issues = cskaoyanMallGoodsMapper.selectInGoods();
        Map<Object,Object> map = new HashMap<>();
        map.put("collectList",issues);
        map.put("totalPages",page);
        ResponseVo<Map> responseVo = new ResponseVo<>();
        responseVo.setData(map);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }
}
