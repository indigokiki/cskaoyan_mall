package com.cskaoyan.service.mallmanagement;

import com.cskaoyan.bean.CskaoyanMallIssue;
import com.cskaoyan.util.ResponseVo;

public interface MallManagement356Service {

    ResponseVo getIssueList(int page,int limit);

    ResponseVo insertIssue(CskaoyanMallIssue cskaoyanMallIssue);

    ResponseVo getIssueListByQuestion(int page,int limit,String question);

    ResponseVo updateIssue(CskaoyanMallIssue cskaoyanMallIssue);

    ResponseVo deleteIssue(CskaoyanMallIssue cskaoyanMallIssue);
}
