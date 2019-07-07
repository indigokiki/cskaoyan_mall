package com.cskaoyan.service.systemmanagement;

import com.cskaoyan.bean.CskaoyanMallAdmin;
import com.cskaoyan.bean.CskaoyanMallAdminExample;
import com.cskaoyan.bean.CskaoyanMallRoleExample;
import com.cskaoyan.bean.systemmanagement.CskaoyanMallMyRole;
import com.cskaoyan.mapper.CskaoyanMallAdminMapper;
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
        System.out.println();
        List<CskaoyanMallMyRole> issues = cskaoyanMallRoleMapper.getValueandLabel();
        Page<CskaoyanMallMyRole> issuePage = new Page<>();
        issuePage.setItems(issues);
        issuePage.setTotal((int)cskaoyanMallRoleMapper.countByExample(cskaoyanMallRoleExample));
        ResponseVo<Page> responseVo = new ResponseVo<>();
        responseVo.setData(issuePage);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }

    @Override
    public ResponseVo insertAdmin(CskaoyanMallAdmin cskaoyanMallAdmin) {
        Date date = new Date();
        cskaoyanMallAdmin.setAddTime(date);
        cskaoyanMallAdmin.setUpdateTime(date);
        cskaoyanMallAdmin.setDeleted(false);
        int insert = cskaoyanMallAdminMapper.insert(cskaoyanMallAdmin);
        ResponseVo<CskaoyanMallAdmin> responseVo = new ResponseVo<>();
        CskaoyanMallAdminExample cskaoyanMallAdminExample = new CskaoyanMallAdminExample();
        CskaoyanMallAdminExample.Criteria criteria = cskaoyanMallAdminExample.createCriteria();
        criteria.andUsernameEqualTo(cskaoyanMallAdmin.getUsername()).andPasswordEqualTo(cskaoyanMallAdmin.getPassword()).andAvatarEqualTo(cskaoyanMallAdmin.getAvatar()).andRoleIdsEqualTo(cskaoyanMallAdmin.getRoleIds()).andDeletedNotEqualTo(true);
        if(1 == insert){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            List<CskaoyanMallAdmin> cskaoyanMallAdmins = cskaoyanMallAdminMapper.selectByExample(cskaoyanMallAdminExample);
            responseVo.setData(cskaoyanMallAdmins.get(cskaoyanMallAdmins.size() - 1));
            return responseVo;
        }
        responseVo.setErrno(500);
        responseVo.setErrmsg("系统错误，请联系管理员");
        return responseVo;
    }

    @Override
    public ResponseVo updateAdmin(CskaoyanMallAdmin cskaoyanMallAdmin) {
        return null;
    }

    @Override
    public ResponseVo deleteAdmin(CskaoyanMallAdmin cskaoyanMallAdmin) {
        return null;
    }

}
