package com.cskaoyan.service.wx;

import com.cskaoyan.util.ResponseVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/9 14:38
 */
public interface WxGoodsService {
    ResponseVo<Map> count();

    ResponseVo<Map> goodsRelated(int id);

    ResponseVo<Map> goodsDetail(int id, HttpServletRequest request);
}
