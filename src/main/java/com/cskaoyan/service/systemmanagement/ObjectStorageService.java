package com.cskaoyan.service.systemmanagement;

import com.cskaoyan.util.ResponseVo;

public interface ObjectStorageService {
    ResponseVo getStorageList(int page, int limit);
}
