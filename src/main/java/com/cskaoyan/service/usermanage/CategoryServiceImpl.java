package com.cskaoyan.service.usermanage;

import com.cskaoyan.bean.CskaoyanMallCategory;
import com.cskaoyan.bean.CskaoyanMallCategoryExample;
import com.cskaoyan.mapper.CskaoyanMallCategoryMapper;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/8 17:31
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CskaoyanMallCategoryMapper categoryMapper;

    public ResponseVo<Map> categoryIndex(){
        //categoryList
        CskaoyanMallCategoryExample categoryExample = new CskaoyanMallCategoryExample();
        categoryExample.createCriteria().andLevelEqualTo("L1");
        List<CskaoyanMallCategory> categoryList = categoryMapper.selectByExample(categoryExample);
        // min  id
        int minId = categoryMapper.selectMinId();
        //currentCategory
        CskaoyanMallCategoryExample categoryExample1 = new CskaoyanMallCategoryExample();
        categoryExample1.createCriteria().andIdEqualTo(minId);
        List<CskaoyanMallCategory> currentCategory = categoryMapper.selectByExample(categoryExample1);
        //currrentSubCategory
        CskaoyanMallCategoryExample categoryExample2 = new CskaoyanMallCategoryExample();
        categoryExample2.createCriteria().andPidEqualTo(minId);
        List<CskaoyanMallCategory> currentSubCategory = categoryMapper.selectByExample(categoryExample2);

        //判断，设置errno，errmsg
        HashMap<String, Object> data = new HashMap<>();
        data.put("categoryList",categoryList);
        data.put("currentCategory",currentCategory);
        data.put("currentSubCategory",currentSubCategory);
        ResponseVo<Map> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(data);
        return responseVo;
    }

    public ResponseVo<Map> currentCategory(int id){
        //currentCategory
        CskaoyanMallCategoryExample categoryExample1 = new CskaoyanMallCategoryExample();
        categoryExample1.createCriteria().andIdEqualTo(id);
        List<CskaoyanMallCategory> currentCategory = categoryMapper.selectByExample(categoryExample1);
        //currrentSubCategory
        CskaoyanMallCategoryExample categoryExample2 = new CskaoyanMallCategoryExample();
        categoryExample2.createCriteria().andPidEqualTo(id);
        List<CskaoyanMallCategory> currentSubCategory = categoryMapper.selectByExample(categoryExample2);

        //判断，设置errno，errmsg
        HashMap<String, Object> data = new HashMap<>();
        data.put("currentCategory",currentCategory);
        data.put("currentSubCategory",currentSubCategory);
        ResponseVo<Map> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(data);
        return responseVo;
    }
}
