package com.cskaoyan.controller.systemmanagement;

import com.cskaoyan.bean.CskaoyanMallAdmin;
import com.cskaoyan.bean.CskaoyanMallRole;
import com.cskaoyan.service.systemmanagement.RoleManagementService;
import com.cskaoyan.util.ResponseVo;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleManagementController {
    @Autowired
    RoleManagementService roleManagementService;

    @RequestMapping("role/list")
    public ResponseVo adminlist(int page, int limit, String sort, String order){
        System.out.println();
        return roleManagementService.getRoleManagementList(page,limit);
    }

    @RequestMapping("role/delete")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo roledelete(@RequestBody CskaoyanMallRole cskaoyanMallRole){
        return roleManagementService.deleterole(cskaoyanMallRole);
    }

    @RequestMapping("role/create")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo rolecreate(@RequestBody CskaoyanMallRole cskaoyanMallRole){
        return roleManagementService.createrole(cskaoyanMallRole);
    }

    @RequestMapping("role/update")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo roleupdate(@RequestBody CskaoyanMallRole cskaoyanMallRole){
        return roleManagementService.updaterole(cskaoyanMallRole);
    }
}
