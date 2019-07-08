package com.cskaoyan.controller.mallmanagement;

import com.cskaoyan.bean.CskaoyanMallKeyword;
import com.cskaoyan.service.mallmanagement.MallManagement6Service;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MallManagement6Controller {

    @Autowired
    MallManagement6Service mallManagement6Service;

    @RequestMapping("keyword/list")
    public ResponseVo keywordlist(int page,int limit,String sort,String order,String keyword,String url){
        if(null != keyword || null != url){
            return mallManagement6Service.getKeywordListByKeywordNUrl(page,limit,sort,keyword,url);
        }
        return mallManagement6Service.getKeywordList(page,limit,sort);
    }

    @RequestMapping("keyword/create")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo keywordcreate(@RequestBody CskaoyanMallKeyword cskaoyanMallKeyword){
        return mallManagement6Service.insertKeyword(cskaoyanMallKeyword);
    }

    @RequestMapping("keyword/update")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo keywordupdate(@RequestBody CskaoyanMallKeyword cskaoyanMallKeyword){
        return mallManagement6Service.updateKeyword(cskaoyanMallKeyword);
    }

    @RequestMapping("keyword/delete")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo keyworddelete(@RequestBody CskaoyanMallKeyword cskaoyanMallKeyword){
        return mallManagement6Service.deleteKeyword(cskaoyanMallKeyword);
    }
}
