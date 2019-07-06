package com.cskaoyan.service.mallmanagement;

import com.cskaoyan.bean.CskaoyanMallIssue;
import com.cskaoyan.util.ResponseVo;

public interface MallManagement5Service {

    ResponseVo getIssueList(int page,int limit,String sort);

    ResponseVo insertIssue(CskaoyanMallIssue cskaoyanMallIssue);

    ResponseVo getIssueListByQuestion(int page,int limit,String question,String sort);

    ResponseVo updateIssue(CskaoyanMallIssue cskaoyanMallIssue);

    ResponseVo deleteIssue(CskaoyanMallIssue cskaoyanMallIssue);
}
