package com.cskaoyan.service.systemmanagement;

import com.cskaoyan.bean.CskaoyanMallRole;
import com.cskaoyan.util.ResponseVo;

public interface RoleManagementService {
    ResponseVo getRoleManagementList(int page, int limit);

    ResponseVo deleterole(CskaoyanMallRole cskaoyanMallRole);

    ResponseVo updaterole(CskaoyanMallRole cskaoyanMallRole);

    ResponseVo createrole(CskaoyanMallRole cskaoyanMallRole);
}
