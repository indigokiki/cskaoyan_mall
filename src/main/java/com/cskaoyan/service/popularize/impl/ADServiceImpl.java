package com.cskaoyan.service.popularize.impl;

import com.cskaoyan.bean.popularize.AD;
import com.cskaoyan.bean.popularize.ADExample;
import com.cskaoyan.bean.popularize.vo.ResManager;
import com.cskaoyan.mapper.popularize.ADMapper;
import com.cskaoyan.service.popularize.IADService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ADServiceImpl  implements IADService {

    @Autowired
    ADMapper adMapper;
    @Override
    public ResManager<AD> findAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        ADExample adExample=new ADExample();
        ADExample.Criteria criteria = adExample.createCriteria();
        criteria.andIdIsNotNull();
        List<AD> ads = adMapper.selectByExample(adExample);
        ResManager<AD> adResManager = new ResManager<>();
        PageInfo<AD> adPageInfo = new PageInfo<>(ads);
        adResManager.setItems(adPageInfo.getList());
        adResManager.setTotal((int) adPageInfo.getTotal());
        return adResManager;
    }

    @Override
    public int update(AD ad) {
        int i = adMapper.updateByPrimaryKey(ad);
        return i;
    }

    @Override
    public void delete(AD ad) {
        adMapper.deleteByPrimaryKey(ad.getId());
    }

    @Override
    public void create(AD ad) {
        adMapper.insert(ad);
    }

    @Override
    public ResManager<AD> list(String name, String content, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        ADExample adExample = new ADExample();
        ADExample.Criteria criteria = adExample.createCriteria();
        if(content==null){
            criteria.andNameLike("%"+name+"%");
        }else if(name==null){
            criteria.andContentLike("%"+content+"%");
        }else{
            criteria.andNameLike("%"+name+"%").andContentLike("%"+content+"%");
        }
        List<AD> list = adMapper.selectByExample(adExample);
        ResManager<AD> resManager = new ResManager<>();
        PageInfo<AD> adPageInfo = new PageInfo<>(list);
        resManager.setTotal((int) adPageInfo.getTotal());
        resManager.setItems(adPageInfo.getList());
        return resManager;
    }
}
