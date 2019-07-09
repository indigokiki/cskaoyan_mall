package com.cskaoyan.service.usermanage;

import com.cskaoyan.util.ResponseVo;

import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/8 17:29
 */

public interface CategoryService {
    public ResponseVo<Map> categoryIndex();
    public ResponseVo<Map> currentCategory(int id);
}
