package com.cskaoyan.service.mallmanage;

import com.cskaoyan.bean.CskaoyanMallBrand;
import com.cskaoyan.bean.mallmanage.RegionList;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;

public interface FirstModuleService {

    RegionList getAllRegions();

    ResponseVo<Page<CskaoyanMallBrand>> getAllBrands(String sort, int page, int limit, String order, String name, String id);

    int delete(CskaoyanMallBrand brand);
}
