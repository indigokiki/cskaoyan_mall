package com.cskaoyan.service.wx;

import com.cskaoyan.util.ResponseVo;

import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/8 17:29
 */

public interface WxCategoryService {
    ResponseVo<Map> categoryIndex();

    ResponseVo<Map> currentCategory(int id);

    ResponseVo<Map> categoryDetail(int id);

    ResponseVo<Map> goodsList(int page, int size, int categoryId);
}
