package com.cskaoyan.service.mallmanage.impl;

import com.cskaoyan.bean.CskaoyanMallBrand;
import com.cskaoyan.bean.CskaoyanMallBrandExample;
import com.cskaoyan.bean.mallmanage.Region;
import com.cskaoyan.bean.mallmanage.RegionList;
import com.cskaoyan.mapper.CskaoyanMallBrandMapper;
import com.cskaoyan.mapper.CskaoyanMallRegionMapper;
import com.cskaoyan.service.mallmanage.FirstModuleService;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirstModuleServiceImpl implements FirstModuleService {
    @Autowired
    CskaoyanMallRegionMapper cskaoyanMallRegionMapper;

    @Autowired
    CskaoyanMallBrandMapper cskaoyanMallBrandMapper;
    @Override
    public RegionList getAllRegions() {
        List<Region> allRegions = cskaoyanMallRegionMapper.getAllRegions();
        RegionList regionList = new RegionList();
        regionList.setErrno(0);
        regionList.setErrmsg("成功");
        regionList.setData(allRegions);

        return regionList;
    }

    @Override
    public ResponseVo<Page<CskaoyanMallBrand>> getAllBrands(String sort, int page, int limit, String order, String name, String id) {
        PageHelper.startPage(page, limit);
        List<CskaoyanMallBrand> brands = cskaoyanMallBrandMapper.getAllBrands(sort, order,name,id);
        int count = (int) cskaoyanMallBrandMapper.countAllBrands(name,id);
        Page<CskaoyanMallBrand> data = new Page<>();
        data.setItems(brands);
        data.setTotal(count);
        ResponseVo<Page<CskaoyanMallBrand>> vo = new ResponseVo<>();
        vo.setData(data);
        vo.setErrno(0);
        vo.setErrmsg("成功");
        return vo;
    }

    @Override
    public int delete(CskaoyanMallBrand brand) {
        brand.setDeleted(true);
        int update = cskaoyanMallBrandMapper.delete(brand);
        return update;
    }
}
