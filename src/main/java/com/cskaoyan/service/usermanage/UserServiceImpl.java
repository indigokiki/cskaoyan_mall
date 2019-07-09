package com.cskaoyan.service.usermanage;

import com.cskaoyan.bean.*;
import com.cskaoyan.mapper.*;
import com.cskaoyan.util.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author IL-M
 * @Date:2019/7/3 18:01
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    CskaoyanMallUserMapper userMapper;
    @Override
    public Page<CskaoyanMallUser> findUsers(int page,int limit,String username, String mobile) {
        CskaoyanMallUserExample userExample = new CskaoyanMallUserExample();
        CskaoyanMallUserExample.Criteria criteria = userExample.createCriteria();

        if(username != null && username != ""){
            username = "%" + username +"%";
            criteria.andUsernameLike(username);
        }
        if(mobile != null && mobile != ""){
            mobile = "%" + mobile + "%";
            criteria.andMobileLike(mobile);
        }

        Page<CskaoyanMallUser> userPage = new Page<>();
        PageHelper.startPage(page,limit);

        List<CskaoyanMallUser> cskaoyanMallUsers = userMapper.selectByExample(userExample);

        userPage.setItems(cskaoyanMallUsers);
        userPage.setTotal(userMapper.selectByExample(userExample).size());
        return userPage;
    }

    @Autowired
    CskaoyanMallAddressMapper addressMapper;
    @Override
    public Page<CskaoyanMallAddress> findAdresses(int page,int limit,String userId, String name) {
        CskaoyanMallAddressExample addressExample = new CskaoyanMallAddressExample();
        CskaoyanMallAddressExample.Criteria criteria = addressExample.createCriteria();
        if(userId != null && userId != ""){
            int id = Integer.parseInt(userId);
            criteria.andUserIdEqualTo(id);
        }
        if(name != null && name != ""){
            name = "%" + name +"%";
            criteria.andNameLike(name);
        }

        Page<CskaoyanMallAddress> addressPage = new Page<>();
        PageHelper.startPage(page,limit);

        List<CskaoyanMallAddress> addresses = addressMapper.selectByExample(addressExample);

        addressPage.setItems(addresses);
        addressPage.setTotal(addressMapper.selectByExample(addressExample).size());
        return addressPage;
    }

    @Autowired
    CskaoyanMallCollectMapper collectMapper;
    @Override
    public Page<CskaoyanMallCollect> findCollects(int page,int limit,String userId, String valueId) {
        CskaoyanMallCollectExample collectExample = new CskaoyanMallCollectExample();
        CskaoyanMallCollectExample.Criteria criteria = collectExample.createCriteria();

        if(userId != null && userId != ""){
            int id = Integer.parseInt(userId);
            criteria.andUserIdEqualTo(id);
        }
        if(valueId != null && valueId != ""){
            int id = Integer.parseInt(valueId);
            criteria.andValueIdEqualTo(id);
        }

        Page<CskaoyanMallCollect> collectPage = new Page<>();
        PageHelper.startPage(page,limit);

        List<CskaoyanMallCollect> collects = collectMapper.selectByExample(collectExample);
        collectPage.setItems(collects);
        collectPage.setTotal(collectMapper.selectByExample(collectExample).size());
        return collectPage;
    }

    @Autowired
    CskaoyanMallFootprintMapper footprintMapper;
    @Override
    public Page<CskaoyanMallFootprint> findFootprints(int page,int limit,String userId, String goodsId) {
        CskaoyanMallFootprintExample footprintExample = new CskaoyanMallFootprintExample();
        CskaoyanMallFootprintExample.Criteria criteria = footprintExample.createCriteria();

        if(userId != null && userId != ""){
            int id = Integer.parseInt(userId);
            criteria.andUserIdEqualTo(id);
        }
        if(goodsId != null && goodsId != ""){
            int id = Integer.parseInt(goodsId);
            criteria.andGoodsIdEqualTo(id);
        }

        Page<CskaoyanMallFootprint> footprintPage = new Page<>();
        PageHelper.startPage(page,limit);

        List<CskaoyanMallFootprint> footprints = footprintMapper.selectByExample(footprintExample);
        footprintPage.setItems(footprints);
        footprintPage.setTotal(footprintMapper.selectByExample(footprintExample).size());
        return footprintPage;
    }

    @Autowired
    CskaoyanMallFeedbackMapper feedbackMapper;
    @Override
    public Page<CskaoyanMallFeedback> findFeedback(int page, int limit, String username, String id) {
        CskaoyanMallFeedbackExample feedbackExample = new CskaoyanMallFeedbackExample();
        CskaoyanMallFeedbackExample.Criteria criteria = feedbackExample.createCriteria();
        if(id != null && id != ""){
            int i = Integer.parseInt(id);
            criteria.andUserIdEqualTo(i);
        }
        if(username != null && username != ""){
            username = "%" + username +"%";
            criteria.andUsernameLike(username);
        }

        Page<CskaoyanMallFeedback> feedbackPage = new Page<>();
        PageHelper.startPage(page,limit);

        List<CskaoyanMallFeedback> feedbacks = feedbackMapper.selectByExample(feedbackExample);
        feedbackPage.setItems(feedbacks);
        feedbackPage.setTotal(feedbackMapper.selectByExample(feedbackExample).size());
        return feedbackPage;
    }

    @Autowired
    CskaoyanMallSearchHistoryMapper historyMapper;
    @Override
    public Page<CskaoyanMallSearchHistory> findHistories(int page, int limit, String userId, String keyword) {
        CskaoyanMallSearchHistoryExample historyExample = new CskaoyanMallSearchHistoryExample();
        CskaoyanMallSearchHistoryExample.Criteria criteria = historyExample.createCriteria();
        if(userId != null && userId != ""){
            int id = Integer.parseInt(userId);
            criteria.andUserIdEqualTo(id);
        }
        if(keyword != null && keyword != ""){
            keyword = "%" + keyword +"%";
            criteria.andKeywordLike(keyword);
        }

        Page<CskaoyanMallSearchHistory> historyPage = new Page<>();
        PageHelper.startPage(page,limit);

        List<CskaoyanMallSearchHistory> histories = historyMapper.selectByExample(historyExample);

        historyPage.setItems(histories);
        historyPage.setTotal(historyMapper.selectByExample(historyExample).size());
        return historyPage;
    }


}
