package com.cskaoyan.service.systemmanagement;

import com.cskaoyan.bean.CskaoyanMallLog;
import com.cskaoyan.bean.CskaoyanMallLogExample;
import com.cskaoyan.mapper.CskaoyanMallLogMapper;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OperationLogServiceImpl implements OperationLogService {
    @Autowired
    CskaoyanMallLogMapper cskaoyanMallLogMapper;
    @Override
    public ResponseVo getloglist(int page, int limit) {
        CskaoyanMallLogExample cskaoyanMalllogExample = new CskaoyanMallLogExample();
        CskaoyanMallLogExample.Criteria criteria = cskaoyanMalllogExample.createCriteria();
        criteria.andDeletedNotEqualTo(true);
        PageHelper.startPage(page,limit);
        List<CskaoyanMallLog> issues = cskaoyanMallLogMapper.selectByExample(cskaoyanMalllogExample);
        Page<CskaoyanMallLog> issuePage = new Page<>();
        issuePage.setItems(issues);
        issuePage.setTotal((int)cskaoyanMallLogMapper.countByExample(cskaoyanMalllogExample));
        ResponseVo<Page> responseVo = new ResponseVo<>();
        responseVo.setData(issuePage);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }
}
