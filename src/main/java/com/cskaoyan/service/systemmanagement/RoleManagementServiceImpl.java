package com.cskaoyan.service.systemmanagement;

import com.cskaoyan.bean.CskaoyanMallRole;
import com.cskaoyan.bean.CskaoyanMallRoleExample;
import com.cskaoyan.mapper.CskaoyanMallRoleMapper;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleManagementServiceImpl implements RoleManagementService {
    @Autowired
    CskaoyanMallRoleMapper cskaoyanMallRoleMapper;

    @Override
    public ResponseVo getRoleManagementList(int page, int limit) {
        CskaoyanMallRoleExample cskaoyanMallRoleExample = new CskaoyanMallRoleExample();
        CskaoyanMallRoleExample.Criteria criteria = cskaoyanMallRoleExample.createCriteria();
        criteria.andDeletedNotEqualTo(true);
        PageHelper.startPage(page,limit);
        List<CskaoyanMallRole> issues = cskaoyanMallRoleMapper.selectByExample(cskaoyanMallRoleExample);
        Page<CskaoyanMallRole> issuePage = new Page<>();
        issuePage.setItems(issues);
        issuePage.setTotal((int)cskaoyanMallRoleMapper.countByExample(cskaoyanMallRoleExample));
        ResponseVo<Page> responseVo = new ResponseVo<>();
        responseVo.setData(issuePage);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }

    @Override
    public ResponseVo deleterole(CskaoyanMallRole cskaoyanMallRole) {
        cskaoyanMallRole.setDeleted(true);
        int status = cskaoyanMallRoleMapper.delete(cskaoyanMallRole);
        return afterOperation(status,cskaoyanMallRole,true);
    }

    @Override
    public ResponseVo updaterole(CskaoyanMallRole cskaoyanMallRole) {
        Date date = new Date();
        cskaoyanMallRole.setAddTime(date);
        cskaoyanMallRole.setUpdateTime(date);
        cskaoyanMallRole.setEnabled(true);
        int status = cskaoyanMallRoleMapper.insertSelective(cskaoyanMallRole);
        ResponseVo<CskaoyanMallRole> responseVo = new ResponseVo<>();
        if(1 == status){
            cskaoyanMallRole.setId(cskaoyanMallRoleMapper.selectLastUpdate());
        }
        return afterOperation(status,cskaoyanMallRole,false);
    }

    @Override
    public ResponseVo createrole(CskaoyanMallRole cskaoyanMallRole) {
        Date date = new Date();
        cskaoyanMallRole.setAddTime(date);
        cskaoyanMallRole.setUpdateTime(date);
        cskaoyanMallRole.setDeleted(false);
        cskaoyanMallRole.setEnabled(true);
        int insert = cskaoyanMallRoleMapper.insert(cskaoyanMallRole);
        ResponseVo<CskaoyanMallRole> responseVo = new ResponseVo<>();
        CskaoyanMallRoleExample cskaoyanMallAdminExample = new CskaoyanMallRoleExample();
        CskaoyanMallRoleExample.Criteria criteria = cskaoyanMallAdminExample.createCriteria();
        criteria.andNameEqualTo(cskaoyanMallRole.getName()).andDescEqualTo(cskaoyanMallRole.getDesc()).andDeletedNotEqualTo(true);
        if(1 == insert){
            cskaoyanMallRole.setId(cskaoyanMallRoleMapper.selectLastUpdate());
        }
        return afterOperation(insert,cskaoyanMallRole,false);
    }

    private ResponseVo afterOperation(int status,CskaoyanMallRole cskaoyanMallRole,boolean isDelete){
        ResponseVo<CskaoyanMallRole> responseVo = new ResponseVo<>();
        if(1 == status){
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");
            if(!isDelete){
                responseVo.setData(cskaoyanMallRole);
            }
            return responseVo;
        }
        responseVo.setErrmsg("操作失败，请联系管理员");
        responseVo.setErrno(500);
        return responseVo;
    }
}
