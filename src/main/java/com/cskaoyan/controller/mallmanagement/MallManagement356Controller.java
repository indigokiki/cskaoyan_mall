package com.cskaoyan.controller.mallmanagement;

import com.cskaoyan.bean.CskaoyanMallIssue;
import com.cskaoyan.service.mallmanagement.MallManagement356Service;
import com.cskaoyan.util.ResponseVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MallManagement356Controller {

    @Autowired
    MallManagement356Service mallManagment356Service;


    /*
        第二个大按钮第五个按钮部分
     */
    @RequestMapping("issue/list")
    public ResponseVo issuelist(int page, int limit, String sort, String order, String question, HttpServletRequest httpServletRequest){
        /*if("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())){
            return null;
        }*/
        if(null != question) {
            return mallManagment356Service.getIssueListByQuestion(page,limit,question);
        }
        return mallManagment356Service.getIssueList(page, limit);
    }

    @RequestMapping("issue/create")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo issueinsert(@RequestBody CskaoyanMallIssue cskaoyanMallIssue, HttpServletRequest httpServletRequest){
        /*if("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())){
            return null;
        }*/
        return mallManagment356Service.insertIssue(cskaoyanMallIssue);
    }

    @RequestMapping("issue/update")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo issueupdate(@RequestBody CskaoyanMallIssue cskaoyanMallIssue){
        return mallManagment356Service.updateIssue(cskaoyanMallIssue);
    }

    @RequestMapping("issue/delete")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo issuedelete(@RequestBody CskaoyanMallIssue cskaoyanMallIssue){
        return mallManagment356Service.deleteIssue(cskaoyanMallIssue);
    }

}
