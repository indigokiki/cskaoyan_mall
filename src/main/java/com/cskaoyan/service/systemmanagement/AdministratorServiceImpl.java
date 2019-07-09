package com.cskaoyan.service.systemmanagement;

import com.cskaoyan.bean.CskaoyanMallAdmin;
import com.cskaoyan.bean.CskaoyanMallAdminExample;
import com.cskaoyan.bean.CskaoyanMallLog;
import com.cskaoyan.bean.CskaoyanMallRoleExample;
import com.cskaoyan.bean.systemmanagement.CskaoyanMallMyRole;
import com.cskaoyan.mapper.CskaoyanMallAdminMapper;
import com.cskaoyan.mapper.CskaoyanMallLogMapper;
import com.cskaoyan.mapper.CskaoyanMallRoleMapper;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class AdministratorServiceImpl implements AdministratorService{
    @Autowired
    CskaoyanMallAdminMapper cskaoyanMallAdminMapper;
    @Autowired
    CskaoyanMallRoleMapper cskaoyanMallRoleMapper;
    @Autowired
    CskaoyanMallLogMapper cskaoyanMallLogMapper;

    @Override
    public ResponseVo getAdminList(int page, int limit) {
        CskaoyanMallAdminExample cskaoyanMallAdminExample = new CskaoyanMallAdminExample();
        CskaoyanMallAdminExample.Criteria criteria = cskaoyanMallAdminExample.createCriteria();
        criteria.andDeletedNotEqualTo(true);
        PageHelper.startPage(page,limit);
        List<CskaoyanMallAdmin> issues = cskaoyanMallAdminMapper.selectByMyExample(cskaoyanMallAdminExample);
        Page<CskaoyanMallAdmin> issuePage = new Page<>();
        issuePage.setItems(issues);
        issuePage.setTotal((int)cskaoyanMallAdminMapper.countByExample(cskaoyanMallAdminExample));
        ResponseVo<Page> responseVo = new ResponseVo<>();
        responseVo.setData(issuePage);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }

    @Override
    public ResponseVo getRoleOptions() {
        CskaoyanMallRoleExample cskaoyanMallRoleExample = new CskaoyanMallRoleExample();
        CskaoyanMallRoleExample.Criteria criteria = cskaoyanMallRoleExample.createCriteria();
        criteria.andDeletedNotEqualTo(true);
        List<CskaoyanMallMyRole> issues = cskaoyanMallRoleMapper.getValueandLabel();
        ResponseVo<List> responseVo = new ResponseVo<>();
        responseVo.setData(issues);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }

    @Override
    public ResponseVo insertAdmin(CskaoyanMallAdmin cskaoyanMallAdmin) {
        Date date = new Date();
        cskaoyanMallAdmin.setAddTime(date);
        cskaoyanMallAdmin.setUpdateTime(date);
        int status = cskaoyanMallAdminMapper.insertSelective(cskaoyanMallAdmin);
        if(1 == status){
            cskaoyanMallAdmin.setId(cskaoyanMallAdminMapper.selectLastUpdate());
        }
        setlog(cskaoyanMallAdmin,"新增管理员" );
        return afterOperation(status,cskaoyanMallAdmin,false);
    }

    @Override
    public ResponseVo updateAdmin(CskaoyanMallAdmin cskaoyanMallAdmin) {
        cskaoyanMallAdmin.setUpdateTime(new Date());
        int status = cskaoyanMallAdminMapper.updateByPrimaryKey(cskaoyanMallAdmin);
        setlog(cskaoyanMallAdmin,"修改管理员" );
        return afterOperation(status,cskaoyanMallAdmin,false);
    }

    @Override
    public ResponseVo deleteAdmin(CskaoyanMallAdmin cskaoyanMallAdmin) {
        cskaoyanMallAdmin.setDeleted(true);
        int status = cskaoyanMallAdminMapper.delete(cskaoyanMallAdmin);
        setlog(cskaoyanMallAdmin,"删除管理员" );
        return afterOperation(status,cskaoyanMallAdmin,true);
    }

    @Override
    public ResponseVo getAdminListByName(int page, int limit, String username) {
        CskaoyanMallAdminExample adminExampleByname = new CskaoyanMallAdminExample();
        CskaoyanMallAdminExample.Criteria criteria = adminExampleByname.createCriteria();
        criteria.andUsernameLike("%" + username + "%").andDeletedNotEqualTo(true);
        PageHelper.startPage(page,limit);
        List<CskaoyanMallAdmin> issues = cskaoyanMallAdminMapper.selectByMyExample(adminExampleByname);
        Page<CskaoyanMallAdmin> issuePage = new Page<>();
        issuePage.setItems(issues);
        issuePage.setTotal((int)cskaoyanMallAdminMapper.countByExample(adminExampleByname));
        ResponseVo<Page> responseVo = new ResponseVo<>();
        responseVo.setData(issuePage);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }

    private ResponseVo afterOperation(int status,CskaoyanMallAdmin cskaoyanMallAdmin,boolean isDelete){
        ResponseVo<CskaoyanMallAdmin> responseVo = new ResponseVo<>();
        if(1 == status){
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");
            if(!isDelete){
                responseVo.setData(cskaoyanMallAdmin);
            }
            return responseVo;
        }
        responseVo.setErrmsg("操作失败，请联系管理员");
        responseVo.setErrno(500);
        return responseVo;
    }

    public void setlog(CskaoyanMallAdmin cskaoyanMallAdmin,String action){
        CskaoyanMallLog cskaoyanMallLog =new CskaoyanMallLog();
        Date date = new Date();
        cskaoyanMallLog.setAddTime(date);
        cskaoyanMallLog.setUpdateTime(date);
        cskaoyanMallLog.setAdmin(cskaoyanMallAdmin.getUsername());
        cskaoyanMallLog.setAction(action);
        cskaoyanMallLog.setComment("");
        cskaoyanMallLog.setType(1);
        cskaoyanMallLog.setDeleted(false);
        cskaoyanMallLog.setId(cskaoyanMallLogMapper.selectLastUpdate());
        cskaoyanMallLog.setResult(cskaoyanMallAdmin.getUsername());
        cskaoyanMallLog.setStatus(true);
        cskaoyanMallLog.setIp("192.168.1.1");
        int i = cskaoyanMallLogMapper.insertSelective(cskaoyanMallLog);
    }
}
