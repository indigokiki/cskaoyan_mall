package com.cskaoyan.service.mallmanagement;

import com.cskaoyan.bean.CskaoyanMallCategory;
import com.cskaoyan.bean.MallCategoryPlus;
import com.cskaoyan.util.ResponseVo;

public interface MallManagement3Service {
    ResponseVo getCategoryList();

    ResponseVo getValueNLabel();

    ResponseVo deleteCategory(MallCategoryPlus mallCategoryPlus);

    ResponseVo insertCategory(CskaoyanMallCategory cskaoyanMallCategory);

    ResponseVo updateCategory(MallCategoryPlus mallCategoryPlus);
}
