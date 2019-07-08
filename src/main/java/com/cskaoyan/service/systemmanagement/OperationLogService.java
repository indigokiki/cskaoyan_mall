package com.cskaoyan.service.systemmanagement;

import com.cskaoyan.util.ResponseVo;

public interface OperationLogService {
    ResponseVo getloglist(int page, int limit);
}
