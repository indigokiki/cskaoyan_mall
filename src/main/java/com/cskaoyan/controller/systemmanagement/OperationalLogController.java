package com.cskaoyan.controller.systemmanagement;

import com.cskaoyan.service.systemmanagement.OperationLogService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationalLogController {
    @Autowired
    OperationLogService operationLogService;
    @RequestMapping("/admin/log/list")
    public ResponseVo loglist(int page, int limit, String sort, String order,String name){
        if(name!=null){
            return operationLogService.getloglist(page,limit,name);
        }
        return operationLogService.getloglist(page,limit);
    }
}
