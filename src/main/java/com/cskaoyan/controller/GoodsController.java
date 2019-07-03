package com.cskaoyan.controller;

import com.cskaoyan.bean.CskaoyanMallGoods;
import com.cskaoyan.service.GoodsService;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseVo<Page> goodsList(int page, int limit, String goodsSn, String name, String sort, String order){
        Page<CskaoyanMallGoods> goodsPage = goodsService.selectGoodsPageWithSnOrName(page, limit, goodsSn, name);
        ResponseVo<Page> pageResponseVo = new ResponseVo<>();
        pageResponseVo.setData(goodsPage);
        pageResponseVo.setErrno(0);
        pageResponseVo.setErrmsg("成功");

        return pageResponseVo;
    }

    //3./goods/delete  传递一个goods对象
    @RequestMapping("/goods/delete")
    public ResponseVo goodsDelete(CskaoyanMallGoods goods){
        int i = goodsService.deleteGoods(goods);
        ResponseVo<Object> responseVo = new ResponseVo<>();
        if (i == 1){
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");

        }else {
            responseVo.setErrno(1);
            responseVo.setErrmsg("失败");
        }
        return responseVo;
    }



}
