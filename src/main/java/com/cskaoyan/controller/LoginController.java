package com.cskaoyan.controller;

import com.cskaoyan.bean.LoginAdmin;
import com.cskaoyan.bean.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {


    @RequestMapping("auth/login")
    @ResponseBody
    public Map login(){
        Map<Object, Object> map = new HashMap<>();
        map.put("errno", 0);
        map.put("data", "1f158724-4544-4736-b412-074abd06d66e");
        map.put("errmsg", "成功");
        return map;
    }

    @RequestMapping("auth/info")
    @ResponseBody
    public Result token(String token){
        Result result = new Result();
        result.setErrno(0);
        result.setErrmsg("成功");
        LoginAdmin loginAdmin = new LoginAdmin();
        loginAdmin.setRoles(new String[]{"超级管理员"});
        loginAdmin.setName("admin123");
        loginAdmin.setPerms(new String[]{"*"});
        loginAdmin.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.setData(loginAdmin);
        return result;
    }
}
