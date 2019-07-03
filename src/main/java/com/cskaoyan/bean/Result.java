package com.cskaoyan.bean;

public class Result {

    private int errno;

    private LoginAdmin data;

    private String errmsg;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public LoginAdmin getData() {
        return data;
    }

    public void setData(LoginAdmin data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
