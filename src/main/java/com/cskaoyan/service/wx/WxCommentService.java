package com.cskaoyan.service.wx;

import com.cskaoyan.util.ResponseVo;

import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/9 23:10
 */
public interface WxCommentService {
    ResponseVo<Map> commentList(int valueId, int type, int showType, int page, int size);
}
