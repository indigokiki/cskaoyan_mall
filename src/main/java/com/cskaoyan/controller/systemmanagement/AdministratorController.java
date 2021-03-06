package com.cskaoyan.controller.systemmanagement;


import com.cskaoyan.bean.CskaoyanMallAdmin;
import com.cskaoyan.service.systemmanagement.AdministratorService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;

    @RequestMapping("/admin/admin/list")
    public ResponseVo adminlist(int page,int limit,String sort,String order,String username){
        if(username!=null){
            return administratorService.getAdminListByName(page,limit,username);
        }
        return administratorService.getAdminList(page,limit);
    }

    @RequestMapping("/admin/role/options")
    public  ResponseVo roleoptions(){
        return administratorService.getRoleOptions();
    }

    @RequestMapping("/admin/admin/create")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo admincreate(@RequestBody CskaoyanMallAdmin cskaoyanMallAdmin){
        return administratorService.insertAdmin(cskaoyanMallAdmin);
    }

    @RequestMapping("/admin/admin/update")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo adminupdate(@RequestBody CskaoyanMallAdmin cskaoyanMallAdmin){
        return administratorService.updateAdmin(cskaoyanMallAdmin);
    }

    @RequestMapping("/admin/admin/delete")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo admindelete(@RequestBody CskaoyanMallAdmin cskaoyanMallAdmin){
        return administratorService.deleteAdmin(cskaoyanMallAdmin);
    }
}
