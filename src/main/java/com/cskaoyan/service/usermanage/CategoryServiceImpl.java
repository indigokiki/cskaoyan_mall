package com.cskaoyan.service.usermanage;

import com.cskaoyan.bean.CskaoyanMallCategory;
import com.cskaoyan.bean.CskaoyanMallCategoryExample;
import com.cskaoyan.bean.goods.CskaoyanMallGoods;
import com.cskaoyan.bean.goods.CskaoyanMallGoodsExample;
import com.cskaoyan.mapper.CskaoyanMallCategoryMapper;
import com.cskaoyan.mapper.goods.CskaoyanMallGoodsMapper;
import com.cskaoyan.util.Page;
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

    @Override
    public ResponseVo<Map> categoryDetail(int id) {
        //pid
        int pid = categoryMapper.selectPid(id);
        //brotherCategory
        CskaoyanMallCategoryExample categoryExample = new CskaoyanMallCategoryExample();
        categoryExample.createCriteria().andPidEqualTo(pid);
        List<CskaoyanMallCategory> brotherCategory = categoryMapper.selectByExample(categoryExample);
        //currentCategory
        CskaoyanMallCategoryExample categoryExample1 = new CskaoyanMallCategoryExample();
        categoryExample1.createCriteria().andIdEqualTo(id);
        List<CskaoyanMallCategory> currentCategory = categoryMapper.selectByExample(categoryExample1);
        //parentCategory
        CskaoyanMallCategoryExample categoryExample2 = new CskaoyanMallCategoryExample();
        categoryExample2.createCriteria().andIdEqualTo(pid);
        List<CskaoyanMallCategory> parentCategory = categoryMapper.selectByExample(categoryExample2);
        //判断，设置errno，errmsg
        HashMap<String, Object> data = new HashMap<>();
        data.put("brotherCategory",brotherCategory);
        data.put("currentCategory",currentCategory);
        data.put("parentCategory",parentCategory);

        ResponseVo<Map> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(data);
        return responseVo;
    }

    @Autowired
    CskaoyanMallGoodsMapper goodsMapper;
    @Override
    public ResponseVo<Map> goodsList(int page, int size, int categoryId) {
        //count
        CskaoyanMallGoodsExample goodsExample = new CskaoyanMallGoodsExample();
        goodsExample.createCriteria().andCategoryIdEqualTo(categoryId);
        long count = goodsMapper.countByExample(goodsExample);
        //goodsList  page size
        CskaoyanMallGoodsExample goodsExample1 = new CskaoyanMallGoodsExample();
        goodsExample1.createCriteria().andCategoryIdEqualTo(categoryId);
        List<CskaoyanMallGoods> goodsList = goodsMapper.selectByExample(goodsExample1);

        //filterCategoryList
        List<CskaoyanMallGoods> filterCategoryList = goodsMapper.getGoodsByCategoryId(categoryId);
        //判断，设置errno，errmsg
        HashMap<String, Object> data = new HashMap<>();
        data.put("count",count);
        data.put("goodsList",goodsList);
        data.put("filterCategoryList",filterCategoryList);

        ResponseVo<Map> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(data);
        return responseVo;
    }
}
