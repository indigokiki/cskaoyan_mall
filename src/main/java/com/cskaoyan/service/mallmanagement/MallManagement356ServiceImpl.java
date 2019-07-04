package com.cskaoyan.service.mallmanagement;


import com.cskaoyan.bean.CskaoyanMallIssue;
import com.cskaoyan.bean.CskaoyanMallIssueExample;
import com.cskaoyan.mapper.CskaoyanMallIssueMapper;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MallManagement356ServiceImpl implements MallManagement356Service {

    @Autowired
    CskaoyanMallIssueMapper cskaoyanMallIssueMapper;

    @Override
    public ResponseVo getIssueList(int page, int limit) {
        CskaoyanMallIssueExample cskaoyanMallIssueExample = new CskaoyanMallIssueExample();
        CskaoyanMallIssueExample.Criteria criteria = cskaoyanMallIssueExample.createCriteria();
        criteria.andDeletedNotEqualTo(true);
        PageHelper.startPage(page,limit);
        List<CskaoyanMallIssue> issues = cskaoyanMallIssueMapper.selectByExample(cskaoyanMallIssueExample);
        Page<CskaoyanMallIssue> issuePage = new Page<>();
        issuePage.setItems(issues);
        issuePage.setTotal((int)cskaoyanMallIssueMapper.countByExample(cskaoyanMallIssueExample));
        ResponseVo<Page> responseVo = new ResponseVo<>();
        responseVo.setData(issuePage);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }

    @Override
    public ResponseVo insertIssue(CskaoyanMallIssue cskaoyanMallIssue) {
        Date date = new Date();
        cskaoyanMallIssue.setAddTime(date);
        cskaoyanMallIssue.setUpdateTime(date);
        cskaoyanMallIssue.setDeleted(false);
        int insert = cskaoyanMallIssueMapper.insert(cskaoyanMallIssue);
        ResponseVo<CskaoyanMallIssue> responseVo = new ResponseVo<>();
        CskaoyanMallIssueExample cskaoyanMallIssueExample = new CskaoyanMallIssueExample();
        CskaoyanMallIssueExample.Criteria criteria = cskaoyanMallIssueExample.createCriteria();
        criteria.andQuestionEqualTo(cskaoyanMallIssue.getQuestion()).andAnswerEqualTo(cskaoyanMallIssue.getAnswer()).andDeletedNotEqualTo(true);
        if(1 == insert){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            List<CskaoyanMallIssue> cskaoyanMallIssues = cskaoyanMallIssueMapper.selectByExample(cskaoyanMallIssueExample);
            responseVo.setData(cskaoyanMallIssues.get(cskaoyanMallIssues.size() - 1));
            return responseVo;
        }
        responseVo.setErrno(500);
        responseVo.setErrmsg("系统错误，请联系管理员");
        return responseVo;
    }

    @Override
    public ResponseVo getIssueListByQuestion(int page, int limit, String question) {
        CskaoyanMallIssueExample issueExampleByquestion = new CskaoyanMallIssueExample();
        CskaoyanMallIssueExample.Criteria criteria = issueExampleByquestion.createCriteria();
        criteria.andQuestionLike("%" + question + "%").andDeletedNotEqualTo(true);
        PageHelper.startPage(page,limit);
        List<CskaoyanMallIssue> issues = cskaoyanMallIssueMapper.selectByExample(issueExampleByquestion);
        Page<CskaoyanMallIssue> issuePage = new Page<>();
        issuePage.setItems(issues);
        issuePage.setTotal((int)cskaoyanMallIssueMapper.countByExample(issueExampleByquestion));
        ResponseVo<Page> responseVo = new ResponseVo<>();
        responseVo.setData(issuePage);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }

    @Override
    public ResponseVo updateIssue(CskaoyanMallIssue cskaoyanMallIssue) {
        cskaoyanMallIssue.setUpdateTime(new Date());
        int i = cskaoyanMallIssueMapper.updateByPrimaryKey(cskaoyanMallIssue);
        ResponseVo<CskaoyanMallIssue> responseVo = new ResponseVo<>();
        if(1 == i){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            responseVo.setData(cskaoyanMallIssue);
            return responseVo;
        }
        responseVo.setErrmsg("更改失败！请联系管理员！");
        responseVo.setErrno(500);
        return responseVo;
    }

    @Override
    public ResponseVo deleteIssue(CskaoyanMallIssue cskaoyanMallIssue) {
        cskaoyanMallIssue.setDeleted(true);
        int i = cskaoyanMallIssueMapper.updateByPrimaryKey(cskaoyanMallIssue);
        ResponseVo<CskaoyanMallIssue> responseVo = new ResponseVo<>();
        if(1 == i){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            responseVo.setData(cskaoyanMallIssue);
            return responseVo;
        }
        responseVo.setErrmsg("删除失败！请联系管理员！");
        responseVo.setErrno(500);
        return responseVo;

    }
}
