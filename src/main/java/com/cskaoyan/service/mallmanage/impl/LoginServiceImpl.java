package com.cskaoyan.service.mallmanage.impl;

import com.cskaoyan.bean.CskaoyanMallAdmin;
import com.cskaoyan.bean.CskaoyanMallAdminExample;
import com.cskaoyan.bean.CskaoyanMallOrderExample;
import com.cskaoyan.bean.CskaoyanMallUserExample;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsExample;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsProductExample;
import com.cskaoyan.mapper.CskaoyanMallAdminMapper;
import com.cskaoyan.mapper.CskaoyanMallOrderMapper;
import com.cskaoyan.mapper.CskaoyanMallUserMapper;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsMapper;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsProductMapper;
import com.cskaoyan.service.mallmanage.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    CskaoyanMallAdminMapper adminMapper;

    @Autowired
    CskaoyanMallGoodsMapper goodsMapper;

    @Autowired
    CskaoyanMallGoodsProductMapper productMapper;

    @Autowired
    CskaoyanMallOrderMapper orderMapper;

    @Autowired
    CskaoyanMallUserMapper userMapper;

    @Override
    public CskaoyanMallAdmin login(CskaoyanMallAdmin admin) {
        CskaoyanMallAdminExample adminExample = new CskaoyanMallAdminExample();
        CskaoyanMallAdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(admin.getUsername()).andPasswordEqualTo(admin.getPassword());
        List<CskaoyanMallAdmin> admins = adminMapper.selectByExample(adminExample);
        if(!admins.isEmpty()){
            return admins.get(0);
        }
        return null;
    }

    @Override
    public Map dashboard() {
        CskaoyanMallGoodsExample goodsExample = new CskaoyanMallGoodsExample();
        int goodsTotal = (int) goodsMapper.countByExample(goodsExample);
        CskaoyanMallGoodsProductExample productExample = new CskaoyanMallGoodsProductExample();
        int productTotal = (int) productMapper.countByExample(productExample);
        CskaoyanMallOrderExample orderExample = new CskaoyanMallOrderExample();
        int orderTotal = (int) orderMapper.countByExample(orderExample);
        CskaoyanMallUserExample userExample = new CskaoyanMallUserExample();
        int userTotal = (int) userMapper.countByExample(userExample);
        Map<Object, Object> map = new HashMap<>();
        map.put("goodsTotal",goodsTotal);
        map.put("productTotal",productTotal);
        map.put("orderTotal",orderTotal);
        map.put("userTotal",userTotal);
        return map;
    }
}
