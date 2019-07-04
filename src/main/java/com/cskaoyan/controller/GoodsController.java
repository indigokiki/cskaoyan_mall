package com.cskaoyan.controller;

import com.cskaoyan.bean.CskaoyanMallComment;
import com.cskaoyan.bean.goods.*;
import com.cskaoyan.service.goods.GoodsService;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.goods.ValueAndLabel;
import com.cskaoyan.util.goods.ValueLabelAndChildren;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YangShuo
 * @date 2019-07-03 20:26
 * 负责商品管理模块：1.商品列表 2.商品上架 3.商品评论
 */
@RestController
public class GoodsController {


    //一、商品列表
    @Autowired
    GoodsService goodsService;

    //1./admin/goods/list?page=1&limit=20&sort=add_time&order=desc
    //2.1 按商品编号查找（精确）/goods/list?page=1&limit=20&goodsSn=12&sort=add_time&order=desc
    //2.2 按商品名称查找（模糊） /goods/list?page=1&limit=20&goodsSn=&name=1212&sort=add_time&order=desc
    @RequestMapping("/goods/list")
    public ResponseVo<Page> goodsList(int page, int limit, String goodsSn, String name, String sort, String order) {
        Page<CskaoyanMallGoods> goodsPage = goodsService.selectGoodsPageWithSnOrName(page, limit, goodsSn, name);
        ResponseVo<Page> pageResponseVo = new ResponseVo<>();
        pageResponseVo.setData(goodsPage);
        pageResponseVo.setErrno(0);
        pageResponseVo.setErrmsg("成功");

        return pageResponseVo;
    }

    //3./goods/delete  传递一个goods对象,不是真的删除，只是把deleted这列从0改成1
    @RequestMapping("/goods/delete")
    public ResponseVo goodsDelete(@RequestBody CskaoyanMallGoods goods) {
        int i = goodsService.changeGoodsDeleted(goods);
        ResponseVo<Object> responseVo = new ResponseVo<>();
        if (i == 1) {
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");

        } else {
            responseVo.setErrno(1);
            responseVo.setErrmsg("失败");
        }
        return responseVo;
    }


    //二、商品上架

    //1.进入页面/goods/create,但请求里没有
    /*请求里是通过http://localhost:9526/static/tinymce4.7.5/skins/lightgray/content.min.css获得上架页面的
    不用后端写*/


    //2./goods/catAndBrand，查brandList+categoryList,data里有两张表
    //brandList只查brand的value和label，categoryList第一次查pid=0的，第二次用pid查对应的
    @RequestMapping("/goods/catAndBrand")
    public ResponseVo goodsCatAndBrand() {
        List<ValueAndLabel> brandList = goodsService.getAllBrandsIdAndName();
        List<ValueLabelAndChildren> categoryList = goodsService.getAllCategorysIdAndName();

        Map<String, Object> catAndBrandMap = new HashMap<>();
        catAndBrandMap.put("brandList", brandList);
        catAndBrandMap.put("categoryList", categoryList);

        ResponseVo<Map<String, Object>> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(catAndBrandMap);
        return responseVo;

    }

    //3.商品图片上传，/storage/create
    //传递一个file(binary),存入storage表，并且返回新增的这个对象


    //4.商品上架 /goods/create，传递4种对象(每种传多个对象)：attributes,goods,products,specifications
    @RequestMapping("/goods/create")
    public ResponseVo goodsCreate(@RequestBody GoodsAdd goodsAdd){
        System.out.println("goodsAdd = " + goodsAdd);

        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");

        return responseVo;
    }



    //三、商品评论
    //1.页面展示 /comment/list?page=1&limit=20&sort=add_time&order=desc
    //2.1用户ID查找(精确) /comment/list?page=1&limit=20&userId=1&sort=add_time&order=desc
    //2.2 商品ID查找(精确) /comment/list?page=1&limit=20&userId=&valueId=102&sort=add_time&order=desc
    @RequestMapping("/comment/list")
    public ResponseVo<Page> commentList(int page, int limit, Integer userId, Integer valueId, String sort, String order) {
        Page<CskaoyanMallComment> commentPage = goodsService.selectCommentsPageWithSnOrName(page, limit, userId, valueId);
        ResponseVo<Page> pageResponseVo = new ResponseVo<>();
        pageResponseVo.setData(commentPage);
        pageResponseVo.setErrno(0);
        pageResponseVo.setErrmsg("成功");

        return pageResponseVo;
    }


}
