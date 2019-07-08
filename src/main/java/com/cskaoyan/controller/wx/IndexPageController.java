package com.cskaoyan.controller.wx;


import com.cskaoyan.service.wx.WxIndexService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexPageController {

    @Autowired
    WxIndexService wxIndexService;

    @RequestMapping("wx/home/index")
    public ResponseVo firstRequest(){
        return wxIndexService.getIndex();
    }
}
