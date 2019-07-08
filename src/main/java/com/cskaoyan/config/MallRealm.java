package com.cskaoyan.config;

import com.cskaoyan.bean.CskaoyanMallAdmin;
import com.cskaoyan.bean.CskaoyanMallAdminExample;
import com.cskaoyan.bean.mallmanage.Admin;
import com.cskaoyan.mapper.CskaoyanMallAdminMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("mallRealm")
public class MallRealm extends AuthorizingRealm {

    @Autowired
    CskaoyanMallAdminMapper adminMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Admin admin = (Admin) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(admin.getRoles());
        info.addStringPermissions(admin.getPerms());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        String passwordByName = getPassword(principal);
        Admin admin = getAdmin(principal);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(admin, passwordByName, "realm");
        return info;
    }

    private String getPassword(String principal){
        String password = adminMapper.selectPassword(principal);
        return password;
    }

    private Admin getAdmin(String username){
        Admin admin = adminMapper.getInfoAdd(username);
        String[] roleIds = admin.getRoleIds();
        admin.setRoles(adminMapper.getRoles(roleIds));
        admin.setPerms(adminMapper.getPerms(roleIds));
        return admin;
    }
}
