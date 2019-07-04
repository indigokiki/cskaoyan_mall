package com.cskaoyan.controller.mallmanagement;


import com.cskaoyan.service.mallmanagement.MallManagement3Service;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MallManagement3Controller {

    @Autowired
    MallManagement3Service mallManagement3Service;

    @RequestMapping("category/list")
    public ResponseVo categorylist(){
        return mallManagement3Service.getCategoryList();
    }

    @RequestMapping("category/l1")
    public ResponseVo categoryl1(){
        return mallManagement3Service.getValueNLabel();
    }

}
