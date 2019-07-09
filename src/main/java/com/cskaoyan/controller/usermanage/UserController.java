package com.cskaoyan.controller.usermanage;

import com.cskaoyan.bean.*;
import com.cskaoyan.service.usermanage.UserService;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author IL-M
 * @Date:2019/7/3 17:34
 */
@Controller
@ResponseBody
public class UserController {
    @Autowired
    UserService userService;
    //会员管理
    @RequestMapping("admin/user/list")
    public ResponseVo<Page> getUsers(int page,int limit,String username,String mobile) {
        Page<CskaoyanMallUser> userPage = userService.findUsers(page, limit, username, mobile);
        ResponseVo<Page> responseVo = new ResponseVo<>();

        if(userPage.getTotal() != 0){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            responseVo.setData(userPage);
        }else{
            responseVo.setErrmsg("系统内部错误");
            responseVo.setErrno(502);
        }
        return responseVo;
    }
    //收货地址
    @RequestMapping("admin/address/list")
    public ResponseVo<Page> getAdresses (int page,int limit,String userId,String name){
        Page<CskaoyanMallAddress> addressPage = userService.findAdresses(page, limit,userId, name);
        ResponseVo<Page> responseVo = new ResponseVo<>();
        if(addressPage.getTotal() != 0){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            responseVo.setData(addressPage);
        }else{
            responseVo.setErrmsg("系统内部错误");
            responseVo.setErrno(502);
        }
        return responseVo;
    }
    //会员收藏
    @RequestMapping("admin/collect/list")
    public ResponseVo<Page> getCollects (int page,int limit,String userId,String valueId){
        Page<CskaoyanMallCollect> collectPage = userService.findCollects(page, limit,userId, valueId);
        ResponseVo<Page> responseVo = new ResponseVo<>();
        if(collectPage.getTotal() != 0){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            responseVo.setData(collectPage);
        }else{
            responseVo.setErrmsg("系统内部错误");
            responseVo.setErrno(502);
        }
        return responseVo;
    }
    //会员足迹
    @RequestMapping("admin/footprint/list")
    public ResponseVo<Page> getFootprints (int page,int limit,String userId,String goodsId){
        Page<CskaoyanMallFootprint> footprintPage = userService.findFootprints(page, limit,userId, goodsId);
        ResponseVo<Page> responseVo = new ResponseVo<>();
        if(footprintPage.getTotal() != 0){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            responseVo.setData(footprintPage);
        }else{
            responseVo.setErrmsg("系统内部错误");
            responseVo.setErrno(502);
        }
        return responseVo;
    }

    @RequestMapping("admin/history/list")
    public ResponseVo<Page> getHistories (int page,int limit,String userId,String keyword){
        Page<CskaoyanMallSearchHistory> historiePage = userService.findHistories(page, limit, userId, keyword);
        ResponseVo<Page> responseVo = new ResponseVo<>();
        if(historiePage.getTotal() != 0){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            responseVo.setData(historiePage);
        }else{
            responseVo.setErrmsg("系统内部错误");
            responseVo.setErrno(502);
        }
        return responseVo;
    }

    @RequestMapping("admin/feedback/list")
    public ResponseVo<Page> getFeedback (int page,int limit,String username,String id){
        Page<CskaoyanMallFeedback> feedPage = userService.findFeedback(page, limit, username, id);
        ResponseVo<Page> responseVo = new ResponseVo<>();
        if(feedPage.getTotal() != 0){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            responseVo.setData(feedPage);
        }else{
            responseVo.setErrmsg("系统内部错误");
            responseVo.setErrno(502);
        }
        return responseVo;
    }

}
