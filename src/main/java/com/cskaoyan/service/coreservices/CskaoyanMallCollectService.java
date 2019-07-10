package com.cskaoyan.service.coreservices;

import com.cskaoyan.util.ResponseVo;

public interface CskaoyanMallCollectService {
    ResponseVo getcollectlist(int type, int page, int size);
}
