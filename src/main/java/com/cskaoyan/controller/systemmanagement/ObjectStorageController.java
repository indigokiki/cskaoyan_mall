package com.cskaoyan.controller.systemmanagement;

import com.cskaoyan.bean.CskaoyanMallStorage;
import com.cskaoyan.bean.Result;
import com.cskaoyan.service.systemmanagement.ObjectStorageService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ObjectStorageController {
    @Autowired
    ObjectStorageService objectStorageService;

    @RequestMapping("/admin/storage/list")
    public ResponseVo adminlist(int page, int limit, String sort, String order) {
        return objectStorageService.getStorageList(page, limit);
    }

    @RequestMapping("/admin/storage/delete")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo roledelete(@RequestBody CskaoyanMallStorage cskaoyanMallStorage){
        return objectStorageService.deletestorage(cskaoyanMallStorage);
    }

    @RequestMapping("/admin/storage/update")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo roleupdate(@RequestBody CskaoyanMallStorage cskaoyanMallStorage){
        return objectStorageService.updatestorage(cskaoyanMallStorage);
    }
}

