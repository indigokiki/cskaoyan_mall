package com.cskaoyan.mapper.goods;

import com.cskaoyan.bean.goods.CskaoyanMallComment;
import com.cskaoyan.bean.goods.CskaoyanMallCommentExample;
import com.cskaoyan.bean.wx.CommentData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CskaoyanMallCommentMapper {
    long countByExample(CskaoyanMallCommentExample example);

    int deleteByExample(CskaoyanMallCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallComment record);

    int insertSelective(CskaoyanMallComment record);

    List<CskaoyanMallComment> selectByExample(CskaoyanMallCommentExample example);

    CskaoyanMallComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallComment record, @Param("example") CskaoyanMallCommentExample example);

    int updateByExample(@Param("record") CskaoyanMallComment record, @Param("example") CskaoyanMallCommentExample example);

    int updateByPrimaryKeySelective(CskaoyanMallComment record);

    int updateByPrimaryKey(CskaoyanMallComment record);

    //新增
    int updateContentByid(@Param("id") Integer commentid, @Param("content") String content);

    int[] getUserId(@Param("valueId") int valueId);

    List<CommentData> getCommentData(@Param("valueId") int valueId);

}