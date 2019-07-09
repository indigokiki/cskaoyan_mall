package com.cskaoyan.service.popularize;


import com.cskaoyan.bean.popularize.GrouponRole;
import com.cskaoyan.bean.popularize.vo.ResManager;

public interface IGrouponRoleService {
    public ResManager<GrouponRole> findAll(Integer page, Integer limit);

    public int update(GrouponRole grouponRole);

    void delete(GrouponRole grouponRole);

    GrouponRole create(GrouponRole grouponRole);

    ResManager<GrouponRole> list(Integer goodsId, Integer page, Integer limit);
}
