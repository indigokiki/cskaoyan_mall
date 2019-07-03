package com.cskaoyan.controller.mallmanagment;

import com.cskaoyan.service.mallmanagment.MallManagment356Service;
import com.cskaoyan.util.ResponseVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MallManagment356Controller {

    @Autowired
    MallManagment356Service mallManagment356Service;

    @RequestMapping("issue/list")
    public ResponseVo issuelist(int page,int limit,String sort,String order){
        return mallManagment356Service.getIssueList(page,limit);
    }

}
