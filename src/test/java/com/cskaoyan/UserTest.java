package com.cskaoyan;

import com.cskaoyan.bean.CskaoyanMallUserExample;
import com.cskaoyan.mapper.CskaoyanMallUserMapper;
import com.cskaoyan.service.UserService;
import com.cskaoyan.util.ResponseVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/3 22:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    CskaoyanMallUserMapper userMapper;

    @Autowired
    UserService userService;
    @Test
    public void mytest(){
       // CskaoyanMallUserExample userExample = new CskaoyanMallUserExample();
        ResponseVo<Map> users = userService.findUsers("2", "1");
        System.out.println(users);
    }
}
