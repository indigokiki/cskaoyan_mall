package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallCategory;
import com.cskaoyan.bean.CskaoyanMallCategoryExample;
import com.cskaoyan.bean.mallmanage.Floor;
import com.cskaoyan.util.ValueNLabel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CskaoyanMallCategoryMapper {
    long countByExample(CskaoyanMallCategoryExample example);

    int deleteByExample(CskaoyanMallCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallCategory record);

    int insertSelective(CskaoyanMallCategory record);

    List<CskaoyanMallCategory> selectByExample(CskaoyanMallCategoryExample example);

    CskaoyanMallCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallCategory record, @Param("example") CskaoyanMallCategoryExample example);

    int updateByExample(@Param("record") CskaoyanMallCategory record, @Param("example") CskaoyanMallCategoryExample example);

    int updateByPrimaryKeySelective(CskaoyanMallCategory record);

    int updateByPrimaryKey(CskaoyanMallCategory record);

    List<ValueNLabel> getL1ValueNLable();

    int selectLastUpdate();

    Byte selectMaxSortOrderFromPid(int pid);

    List<Floor> getIndex();

    int selectMinId();


    List<CskaoyanMallCategory> searchCategory(@Param("name") String name);


    int selectPid(int id);

    CskaoyanMallCategory defaultCategory (@Param("id") int id);

}