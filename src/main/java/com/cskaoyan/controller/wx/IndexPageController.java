package com.cskaoyan.controller.wx;


import com.cskaoyan.service.wx.WxIndexService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx")
public class IndexPageController {

    @Autowired
    WxIndexService wxIndexService;

    @RequestMapping("home/index")
    public ResponseVo firstRequest(){
        return wxIndexService.getIndex();
    }

    @RequestMapping("goods/count")
    public ResponseVo count(){
        return wxIndexService.getGoodsCount();
    }

    @RequestMapping("search/index")
    public ResponseVo searchindex(){
        return wxIndexService.getGoodsCount();
    }
}
