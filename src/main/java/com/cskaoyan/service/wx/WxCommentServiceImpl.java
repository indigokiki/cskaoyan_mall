package com.cskaoyan.service.wx;

import com.cskaoyan.bean.CskaoyanMallUser;
import com.cskaoyan.bean.goods.CskaoyanMallComment;
import com.cskaoyan.bean.goods.CskaoyanMallCommentExample;
import com.cskaoyan.bean.wx.CommentData;
import com.cskaoyan.mapper.CskaoyanMallUserMapper;
import com.cskaoyan.mapper.goods.CskaoyanMallCommentMapper;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author IL-M
 * @Date:2019/7/9 23:11
 */
@Service
public class WxCommentServiceImpl implements WxCommentService {
    @Autowired
    CskaoyanMallCommentMapper commentMapper;

    @Autowired
    CskaoyanMallUserMapper userMapper;
    @Override
    public ResponseVo<Map> commentList(int valueId, int type, int showType, int page, int size) {
        //type==1判断？
        //count
        CskaoyanMallCommentExample commentExample = new CskaoyanMallCommentExample();
        commentExample.createCriteria().andValueIdEqualTo(valueId);
        int count = (int)commentMapper.countByExample(commentExample);
        //currentPage
        int currentPage = page;
        //userid  data
        //int[] userIds = commentMapper.getUserId(valueId);

        HashMap<String, Object> data = new HashMap<>();
        //ArrayList<Object> list = new ArrayList<>();


            //用userid和valueid查询comment
            List<CommentData> commentData = commentMapper.getCommentData(valueId);
            //用userid查询userinfo
            //Object userInfo = userMapper.selectByPrimaryKey(id);
            //list.add(commentData);


        data.put("count",count);
        data.put("currentPage",currentPage);
        data.put("data",commentData);
        ResponseVo<Map> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(data);
        return responseVo;
    }
}
