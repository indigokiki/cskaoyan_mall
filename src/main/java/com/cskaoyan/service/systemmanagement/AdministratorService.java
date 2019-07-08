package com.cskaoyan.service.systemmanagement;

import com.cskaoyan.bean.CskaoyanMallAdmin;
import com.cskaoyan.util.ResponseVo;

public interface AdministratorService {
    ResponseVo getAdminList(int page, int limit);

    ResponseVo getRoleOptions();

    ResponseVo insertAdmin(CskaoyanMallAdmin cskaoyanMallAdmin);

    ResponseVo updateAdmin(CskaoyanMallAdmin cskaoyanMallAdmin);

    ResponseVo deleteAdmin(CskaoyanMallAdmin cskaoyanMallAdmin);
}
