package com.cskaoyan.service.systemmanagement;

import com.cskaoyan.bean.CskaoyanMallStorage;
import com.cskaoyan.bean.CskaoyanMallStorageExample;
import com.cskaoyan.mapper.CskaoyanMallStorageMapper;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectStorageServiceImpl implements ObjectStorageService {
    @Autowired
    CskaoyanMallStorageMapper cskaoyanMallStorageMapper;
    @Override
    public ResponseVo getStorageList(int page, int limit) {
        CskaoyanMallStorageExample cskaoyanMalllogExample = new CskaoyanMallStorageExample();
        CskaoyanMallStorageExample.Criteria criteria = cskaoyanMalllogExample.createCriteria();
        criteria.andDeletedNotEqualTo(true);
        PageHelper.startPage(page, limit);
        List<CskaoyanMallStorage> issues = cskaoyanMallStorageMapper.selectByExample(cskaoyanMalllogExample);
        Page<CskaoyanMallStorage> issuePage = new Page<>();
        issuePage.setItems(issues);
        issuePage.setTotal((int) cskaoyanMallStorageMapper.countByExample(cskaoyanMalllogExample));
        ResponseVo<Page> responseVo = new ResponseVo<>();
        responseVo.setData(issuePage);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }
}
