package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallFeedback;
import com.cskaoyan.bean.CskaoyanMallFeedbackExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallFeedbackMapper {
    long countByExample(CskaoyanMallFeedbackExample example);

    int deleteByExample(CskaoyanMallFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallFeedback record);

    int insertSelective(CskaoyanMallFeedback record);

    List<CskaoyanMallFeedback> selectByExample(CskaoyanMallFeedbackExample example);

    CskaoyanMallFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallFeedback record, @Param("example") CskaoyanMallFeedbackExample example);

    int updateByExample(@Param("record") CskaoyanMallFeedback record, @Param("example") CskaoyanMallFeedbackExample example);

    int updateByPrimaryKeySelective(CskaoyanMallFeedback record);

    int updateByPrimaryKey(CskaoyanMallFeedback record);
}