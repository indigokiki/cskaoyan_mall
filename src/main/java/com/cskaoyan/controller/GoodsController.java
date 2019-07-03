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
    @RequestMapping("/goods/list")
    public ResponseVo<Page> goodsList(int page, int limit, String sort, String order){
        Page<CskaoyanMallGoods> goodsPage = goodsService.selectGoodsPage();
        ResponseVo<Page> pageResponseVo = new ResponseVo<>();
        pageResponseVo.setData(goodsPage);
        pageResponseVo.setErrno(0);
        pageResponseVo.setErrmsg("成功");

        return pageResponseVo;
    }
}
