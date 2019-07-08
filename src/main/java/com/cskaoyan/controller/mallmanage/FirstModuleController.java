package com.cskaoyan.controller.mallmanage;

import com.cskaoyan.bean.CskaoyanMallBrand;
import com.cskaoyan.bean.Picture;
import com.cskaoyan.bean.Result;
import com.cskaoyan.bean.mallmanage.RegionList;
import com.cskaoyan.service.mallmanage.FirstModuleService;
import com.cskaoyan.service.mallmanage.PicService;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class FirstModuleController {

    @Autowired
    FirstModuleService firstModuleService;

    @Autowired
    PicService picService;


    @RequestMapping("region/list")
    @ResponseBody
    public RegionList list(){
        RegionList allRegions = firstModuleService.getAllRegions();
        return allRegions;
    }

    @RequestMapping("brand/list")
    @ResponseBody
    public ResponseVo<Page<CskaoyanMallBrand>> brandList(String sort, int page, int limit, String order, String name, String id){
        ResponseVo<Page<CskaoyanMallBrand>> allBrands = firstModuleService.getAllBrands(sort, page, limit, order, name, id);
        return allBrands;
    }

    @RequestMapping("storage/create")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @ResponseBody
    public Result pic( MultipartFile file) throws IOException {
        Result result = picService.create(file);
        return result;
    }

    @RequestMapping("brand/delete")
    @ResponseBody
    public Result delete(@RequestBody CskaoyanMallBrand brand){
        int delete = firstModuleService.delete(brand);
        Result result = new Result();
        result.setErrno(0);
        result.setErrmsg("成功");
        return result;
    }

    @RequestMapping("brand/create")
    @ResponseBody
    public Result brandcreate(@RequestBody CskaoyanMallBrand brand){

        return firstModuleService.insert(brand);
    }

    @RequestMapping("brand/update")
    @ResponseBody
    public Result update(@RequestBody CskaoyanMallBrand brand){
        return firstModuleService.update(brand);
    }
}
