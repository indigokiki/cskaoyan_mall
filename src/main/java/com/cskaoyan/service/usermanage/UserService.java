package com.cskaoyan.service.usermanage;

import com.cskaoyan.bean.*;
import com.cskaoyan.mapper.CskaoyanMallUserMapper;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/3 18:00
 */
public interface UserService {
    Page<CskaoyanMallUser> findUsers(int page, int limit, String username, String mobile);
    Page<CskaoyanMallAddress> findAdresses(int page,int limit,String userId, String name);
    Page<CskaoyanMallCollect> findCollects(int page,int limit,String userId, String valueId);
    Page<CskaoyanMallFootprint> findFootprints(int page,int limit,String userId, String goodsId);
    Page<CskaoyanMallSearchHistory> findHistories(int page, int limit, String userId, String keyword);
    Page<CskaoyanMallFeedback> findFeedback(int page, int limit, String username, String id);



}
