package com.cskaoyan.service.systemmanagement;

import com.cskaoyan.bean.CskaoyanMallCategory;
import com.cskaoyan.bean.CskaoyanMallStorage;
import com.cskaoyan.bean.CskaoyanMallStorageExample;
import com.cskaoyan.bean.Result;
import com.cskaoyan.mapper.CskaoyanMallStorageMapper;
import com.cskaoyan.service.mallmanage.PicService;
import com.cskaoyan.service.mallmanage.impl.PicServiceImpl;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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

    @Autowired
    PicService picService;

    @Override
    public ResponseVo updatestorage(CskaoyanMallStorage cskaoyanMallStorage) {
        cskaoyanMallStorage.setUpdateTime(new Date());
        int status = cskaoyanMallStorageMapper.updateByPrimaryKey(cskaoyanMallStorage);
        ResponseVo<Page> responseVo = new ResponseVo<>();
        if(1 == status){
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");
            return responseVo;
        }
        responseVo.setErrmsg("操作失败，请联系管理员");
        responseVo.setErrno(500);
        return responseVo;
    }

    @Override
    public ResponseVo deletestorage(CskaoyanMallStorage cskaoyanMallStorage) {
        cskaoyanMallStorage.setDeleted(true);
        int status = cskaoyanMallStorageMapper.delete(cskaoyanMallStorage);
        ResponseVo<Page> responseVo = new ResponseVo<>();
        if(1 == status){
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");
            return responseVo;
        }
        responseVo.setErrmsg("操作失败，请联系管理员");
        responseVo.setErrno(500);
        return responseVo;
    }
}

