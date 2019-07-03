package com.cskaoyan.service.mallmanagment;


import com.cskaoyan.bean.CskaoyanMallIssue;
import com.cskaoyan.bean.CskaoyanMallIssueExample;
import com.cskaoyan.mapper.CskaoyanMallIssueMapper;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallManagment356ServiceImpl implements MallManagment356Service {

    @Autowired
    CskaoyanMallIssueMapper cskaoyanMallIssueMapper;

    @Override
    public ResponseVo getIssueList(int page, int limit) {
        PageHelper.startPage(page,limit);
        List<CskaoyanMallIssue> issues = cskaoyanMallIssueMapper.selectByExample(new CskaoyanMallIssueExample());
        Page<CskaoyanMallIssue> issuePage = new Page<>();
        issuePage.setItems(issues);
        issuePage.setTotal((int)cskaoyanMallIssueMapper.countByExample(new CskaoyanMallIssueExample()));
        ResponseVo<Page> responseVo = new ResponseVo<>();
        responseVo.setData(issuePage);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }
}
