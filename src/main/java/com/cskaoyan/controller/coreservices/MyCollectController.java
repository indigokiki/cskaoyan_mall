package com.cskaoyan.controller.coreservices;

import com.cskaoyan.service.coreservices.CskaoyanMallCollectService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyCollectController {
    @Autowired
    CskaoyanMallCollectService cskaoyanMallCollectService;
    @RequestMapping("wx/collect/list")
    public ResponseVo couponmylist(int type, int page, int size){
        return cskaoyanMallCollectService.getcollectlist(type,page,size);
    }
}
