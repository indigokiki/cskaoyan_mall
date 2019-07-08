package com.cskaoyan.controller.systemmanagement;

import com.cskaoyan.service.systemmanagement.ObjectStorageService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectStorageController {
    @Autowired
    ObjectStorageService objectStorageService;

    @RequestMapping("storage/list")
    public ResponseVo adminlist(int page, int limit, String sort, String order) {
        return objectStorageService.getStorageList(page, limit);
    }
}

