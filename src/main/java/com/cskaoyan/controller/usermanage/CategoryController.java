package com.cskaoyan.controller.usermanage;

import com.cskaoyan.bean.CskaoyanMallUser;
import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.service.usermanage.CategoryService;
import com.cskaoyan.util.Page;
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
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("wx/catalog/index")
    public ResponseVo<Map> categoryList(){
        ResponseVo<Map> responseVo = categoryService.categoryIndex();
        return responseVo;
    }

    @RequestMapping("wx/catalog/current")
    public ResponseVo<Map> categoryList(int id){
        ResponseVo<Map> responseVo = categoryService.currentCategory(id);
        return responseVo;
    }

    @RequestMapping("wx/goods/category")
    public ResponseVo<Map> categoryDetail(int id){
        ResponseVo<Map> responseVo = categoryService.categoryDetail(id);
        return responseVo;
    }

    @RequestMapping("wx/goods/list")
    public ResponseVo<Map> goodsList(int page,int size,int categoryId){
        ResponseVo<Map> responseVo = categoryService.goodsList(page,size,categoryId);
        return responseVo;
    }

}
