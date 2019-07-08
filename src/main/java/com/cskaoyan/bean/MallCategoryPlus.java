package com.cskaoyan.bean;

import java.util.List;

public class MallCategoryPlus extends CskaoyanMallCategory {

    private List<CskaoyanMallCategory> children;

    public List<CskaoyanMallCategory> getChildren() {
        return children;
    }

    public void setChildren(List<CskaoyanMallCategory> children) {
        this.children = children;
    }

    public MallCategoryPlus(CskaoyanMallCategory cskaoyanMallCategory) {
        this.setAddTime(cskaoyanMallCategory.getAddTime());
        this.setDeleted(cskaoyanMallCategory.getDeleted());
        this.setDesc(cskaoyanMallCategory.getDesc());
        this.setIconUrl(cskaoyanMallCategory.getIconUrl());
        this.setId(cskaoyanMallCategory.getId());
        this.setKeywords(cskaoyanMallCategory.getKeywords());
        this.setLevel(cskaoyanMallCategory.getLevel());
        this.setName(cskaoyanMallCategory.getName());
        this.setPicUrl(cskaoyanMallCategory.getPicUrl());
        this.setPid(cskaoyanMallCategory.getPid());
        this.setSortOrder(cskaoyanMallCategory.getSortOrder());
        this.setUpdateTime(cskaoyanMallCategory.getUpdateTime());
    }

    public MallCategoryPlus(List<CskaoyanMallCategory> children) {
        this.children = children;
    }

    public MallCategoryPlus(){

    }
}
