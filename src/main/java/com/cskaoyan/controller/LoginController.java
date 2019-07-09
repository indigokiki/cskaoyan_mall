package com.cskaoyan.controller;

import com.cskaoyan.bean.CskaoyanMallAdmin;
import com.cskaoyan.bean.LoginAdmin;
import com.cskaoyan.bean.Result;
import com.cskaoyan.bean.mallmanage.Admin;
import com.cskaoyan.service.mallmanage.LoginService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@RequestMapping("/admin")
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/auth/login")
    @ResponseBody
    public Result login(@RequestBody CskaoyanMallAdmin admin){
        String username = admin.getUsername();
        String password = admin.getPassword();
        Result<String> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            Serializable serializable = subject.getSession().getId();
            result.setErrno(0);
            result.setErrmsg("成功");
            result.setData(serializable.toString());
        }catch (Exception e){
            result.setErrno(605);
            result.setErrmsg("用户帐号或密码不正确");

        }
        return result;
    }

    @RequestMapping("auth/info")
    @ResponseBody
    public Result token(String token){
        Admin principal = (Admin) SecurityUtils.getSubject().getPrincipal();
        Result<Admin> result = new Result<>();
        result.setErrmsg("成功");
        result.setErrno(0);
        result.setData(principal);
        return result;
    }

    @RequestMapping("dashboard")
    @ResponseBody
    public Result dashboard(){
        Result<Object> result = new Result<>();
        Map map = loginService.dashboard();
        result.setData(map);
        result.setErrno(0);
        result.setErrmsg("成功");
        return result;
    }

    @RequestMapping("auth/401")
    @ResponseBody
    public Result auth(){
        Result<Object> result = new Result<>();
        result.setErrmsg("请登录");
        result.setErrno(501);
        return result;
    }
}
