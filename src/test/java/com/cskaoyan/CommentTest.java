package com.cskaoyan;

import com.cskaoyan.bean.wx.CommentData;
import com.cskaoyan.mapper.CskaoyanMallUserMapper;
import com.cskaoyan.mapper.goods.CskaoyanMallCommentMapper;
import com.cskaoyan.util.wxutil.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/10 9:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentTest {
    @Autowired
    CskaoyanMallCommentMapper commentMapper;
    @Test
    public void mytest(){
        List<CommentData> commentData = commentMapper.getCommentData(264);
        System.out.println("commentData = " + commentData);
    }

    @Test
    public void mytest1(){
        int[] userId = commentMapper.getUserId(264);
        System.out.println(userId);

    }

    @Autowired
    CskaoyanMallUserMapper userMapper;
    @Test
    public void mytest2(){
        UserInfo userInfo = userMapper.getUserInfo(2);
        System.out.println("userInfo = " + userInfo);

    }


    @Test
    public void mytest3(){
        List<CommentData> commentData = commentMapper.getCommentData(264);
        System.out.println(commentData);

    }
}
