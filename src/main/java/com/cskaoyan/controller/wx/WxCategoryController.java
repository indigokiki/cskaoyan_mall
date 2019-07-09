package com.cskaoyan.controller.wx;

import com.cskaoyan.service.wx.WxCategoryService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/8 18:01
 */
@Controller
@ResponseBody
public class WxCategoryController {

    @Autowired
    WxCategoryService wxCategoryService;

    @RequestMapping("wx/catalog/index")
    public ResponseVo<Map> categoryList(){
        ResponseVo<Map> responseVo = wxCategoryService.categoryIndex();
        return responseVo;
    }

    @RequestMapping("wx/catalog/current")
    public ResponseVo<Map> categoryList(int id){
        ResponseVo<Map> responseVo = wxCategoryService.currentCategory(id);
        return responseVo;
    }

    @RequestMapping("wx/goods/category")
    public ResponseVo<Map> categoryDetail(int id){
        ResponseVo<Map> responseVo = wxCategoryService.categoryDetail(id);
        return responseVo;
    }

    @RequestMapping("wx/goods/list")
    public ResponseVo<Map> goodsList(int page,int size,int categoryId){
        ResponseVo<Map> responseVo = wxCategoryService.goodsList(page,size,categoryId);
        return responseVo;
    }

}
