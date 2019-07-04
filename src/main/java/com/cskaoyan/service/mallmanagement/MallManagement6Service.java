package com.cskaoyan.service.mallmanagement;

import com.cskaoyan.bean.CskaoyanMallKeyword;
import com.cskaoyan.util.ResponseVo;

public interface MallManagement6Service {

    ResponseVo getKeywordList(int page,int limit,String sort);

    ResponseVo getKeywordListByKeywordNUrl(int page, int limit, String sort, String keyword, String url);

    ResponseVo insertKeyword(CskaoyanMallKeyword cskaoyanMallKeyword);

    ResponseVo updateKeyword(CskaoyanMallKeyword cskaoyanMallKeyword);

    ResponseVo deleteKeyword(CskaoyanMallKeyword cskaoyanMallKeyword);
}
