package com.cskaoyan.service.systemmanagement;

import com.cskaoyan.bean.CskaoyanMallStorage;
import com.cskaoyan.bean.Result;
import com.cskaoyan.util.ResponseVo;
import org.springframework.web.multipart.MultipartFile;

public interface ObjectStorageService {
    ResponseVo getStorageList(int page, int limit);

    ResponseVo updatestorage(CskaoyanMallStorage cskaoyanMallStorage);

    ResponseVo deletestorage(CskaoyanMallStorage cskaoyanMallStorage);
}
