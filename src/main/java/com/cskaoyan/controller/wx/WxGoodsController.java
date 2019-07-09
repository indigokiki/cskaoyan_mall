package com.cskaoyan.controller.wx;

import com.cskaoyan.service.wx.WxGoodsService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/9 14:35
 */
@Controller
@ResponseBody
public class WxGoodsController {
    @Autowired
    WxGoodsService wxGoodsService;



    @RequestMapping("wx/goods/related")
    public ResponseVo<Map> goodsRelated(int id){
        ResponseVo<Map> responseVo = wxGoodsService.goodsRelated(id);
        return responseVo;
    }

}
