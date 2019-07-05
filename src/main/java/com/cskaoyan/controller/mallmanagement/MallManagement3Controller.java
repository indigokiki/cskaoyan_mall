package com.cskaoyan.controller.mallmanagement;


import com.cskaoyan.bean.CskaoyanMallCategory;
import com.cskaoyan.bean.MallCategoryPlus;
import com.cskaoyan.service.mallmanage.PicService;
import com.cskaoyan.service.mallmanagement.MallManagement3Service;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
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

    @RequestMapping("category/delete")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo categorydelete(@RequestBody MallCategoryPlus mallCategoryPlus){
        return mallManagement3Service.deleteCategory(mallCategoryPlus);
    }

    @RequestMapping("category/create")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo categorycreate(@RequestBody CskaoyanMallCategory cskaoyanMallCategory){
        return mallManagement3Service.insertCategory(cskaoyanMallCategory);
    }

    @RequestMapping("category/update")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseVo categoryupdate(@RequestBody MallCategoryPlus mallCategoryPlus){
        return mallManagement3Service.updateCategory(mallCategoryPlus);
    }


}
