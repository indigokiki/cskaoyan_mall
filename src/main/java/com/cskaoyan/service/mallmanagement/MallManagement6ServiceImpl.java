package com.cskaoyan.service.mallmanagement;


import com.cskaoyan.bean.CskaoyanMallKeyword;
import com.cskaoyan.bean.CskaoyanMallKeywordExample;
import com.cskaoyan.mapper.CskaoyanMallKeywordMapper;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MallManagement6ServiceImpl implements MallManagement6Service {

    @Autowired
    CskaoyanMallKeywordMapper cskaoyanMallKeywordMapper;

    @Override
    public ResponseVo getKeywordList(int page, int limit,String sort) {
        CskaoyanMallKeywordExample cskaoyanMallKeywordExample = new CskaoyanMallKeywordExample();
        CskaoyanMallKeywordExample.Criteria criteria = cskaoyanMallKeywordExample.createCriteria();
        criteria.andDeletedNotEqualTo(true);
        cskaoyanMallKeywordExample.setOrderByClause(sort);
        return getSelectResponesVo(page,limit,cskaoyanMallKeywordExample);
    }

    @Override
    public ResponseVo getKeywordListByKeywordNUrl(int page, int limit, String sort, String keyword, String url) {
        if(null == keyword){
            keyword = "";
        }
        if (null == url){
            url = "";
        }
        CskaoyanMallKeywordExample cskaoyanMallKeywordExample = new CskaoyanMallKeywordExample();
        CskaoyanMallKeywordExample.Criteria criteria = cskaoyanMallKeywordExample.createCriteria();
        criteria.andKeywordLike("%" + keyword + "%").andUrlLike("%" + url + "%").andDeletedNotEqualTo(true);
        cskaoyanMallKeywordExample.setOrderByClause(sort);
        return getSelectResponesVo(page,limit,cskaoyanMallKeywordExample);
    }

    @Override
    public ResponseVo insertKeyword(CskaoyanMallKeyword cskaoyanMallKeyword) {
        Date date = new Date();
        cskaoyanMallKeyword.setAddTime(date);
        cskaoyanMallKeyword.setUpdateTime(date);
        int status = cskaoyanMallKeywordMapper.insertSelective(cskaoyanMallKeyword);
        ResponseVo<CskaoyanMallKeyword> responseVo = new ResponseVo<>();
        if(1 == status){
            cskaoyanMallKeyword.setId(cskaoyanMallKeywordMapper.selectLastUpdate());
        }
        return afterOperation(status,cskaoyanMallKeyword,false);
    }

    @Override
    public ResponseVo updateKeyword(CskaoyanMallKeyword cskaoyanMallKeyword) {
        cskaoyanMallKeyword.setUpdateTime(new Date());
        int status = cskaoyanMallKeywordMapper.updateByPrimaryKey(cskaoyanMallKeyword);
        return afterOperation(status,cskaoyanMallKeyword,false);
    }

    @Override
    public ResponseVo deleteKeyword(CskaoyanMallKeyword cskaoyanMallKeyword) {
        cskaoyanMallKeyword.setDeleted(true);
        int status = cskaoyanMallKeywordMapper.updateByPrimaryKey(cskaoyanMallKeyword);
        return afterOperation(status,cskaoyanMallKeyword,true);
    }

    private ResponseVo getSelectResponesVo(int page, int limit, CskaoyanMallKeywordExample example){
        PageHelper.startPage(page,limit);
        List<CskaoyanMallKeyword> issues = cskaoyanMallKeywordMapper.selectByExample(example);
        Page<CskaoyanMallKeyword> issuePage = new Page<>();
        issuePage.setItems(issues);
        issuePage.setTotal((int)cskaoyanMallKeywordMapper.countByExample(example));
        ResponseVo<Page> responseVo = new ResponseVo<>();
        responseVo.setData(issuePage);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }

    private ResponseVo afterOperation(int status,CskaoyanMallKeyword cskaoyanMallKeyword,boolean isDelete){
        ResponseVo<CskaoyanMallKeyword> responseVo = new ResponseVo<>();
        if(1 == status){
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");
            if(!isDelete){
                responseVo.setData(cskaoyanMallKeyword);
            }
            return responseVo;
        }
        responseVo.setErrmsg("操作失败，请联系管理员");
        responseVo.setErrno(500);
        return responseVo;
    }
}
