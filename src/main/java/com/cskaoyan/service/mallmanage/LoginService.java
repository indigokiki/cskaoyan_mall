package com.cskaoyan.service.mallmanage;

import com.cskaoyan.bean.CskaoyanMallAdmin;

import java.util.Map;

public interface LoginService {

    CskaoyanMallAdmin login(CskaoyanMallAdmin admin);

    Map dashboard();
}
