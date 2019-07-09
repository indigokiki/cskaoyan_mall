package com.cskaoyan.service.wx;

import com.cskaoyan.util.ResponseVo;

public interface WxIndexService {

    ResponseVo getIndex();
    ResponseVo getGoodsCount();
    ResponseVo searchIndex();

}
