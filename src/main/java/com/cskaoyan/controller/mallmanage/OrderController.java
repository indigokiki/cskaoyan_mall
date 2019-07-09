package com.cskaoyan.controller.mallmanage;

import com.cskaoyan.bean.Result;
import com.cskaoyan.service.mallmanage.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("admin")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("order/list")
    @ResponseBody
    public Result list(int page, int limit, String sort, String order, String orderStatusArray, String userId, String orderSn ){
        return orderService.getAllOrders(page,limit,sort,order,orderStatusArray, userId, orderSn);
    }

    @RequestMapping("order/detail")
    @ResponseBody
    public Result detail(String id){
        return orderService.dedetail(id);
    }
}
