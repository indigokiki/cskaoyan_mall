package com.cskaoyan.bean.wx;

import com.cskaoyan.util.wxutil.UserInfo;

import java.util.Date;

/**
 * @Author IL-M
 * @Date:2019/7/10 10:08
 */
public class CommentData {
    Date addTime;
    String[] picList;
    String content;
   UserInComment userInfo;

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String[] getPicList() {
        return picList;
    }

    public void setPicList(String[] picList) {
        this.picList = picList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserInComment getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInComment userInfo) {
        this.userInfo = userInfo;
    }
}
