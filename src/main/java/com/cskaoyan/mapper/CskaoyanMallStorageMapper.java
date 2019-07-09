package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallStorage;
import com.cskaoyan.bean.CskaoyanMallStorageExample;
import com.cskaoyan.bean.Picture;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CskaoyanMallStorageMapper {
    long countByExample(CskaoyanMallStorageExample example);

    int deleteByExample(CskaoyanMallStorageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallStorage record);

    int insertSelective(CskaoyanMallStorage record);

    List<CskaoyanMallStorage> selectByExample(CskaoyanMallStorageExample example);

    CskaoyanMallStorage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallStorage record, @Param("example") CskaoyanMallStorageExample example);

    int updateByExample(@Param("record") CskaoyanMallStorage record, @Param("example") CskaoyanMallStorageExample example);

    int updateByPrimaryKeySelective(CskaoyanMallStorage record);

    int updateByPrimaryKey(CskaoyanMallStorage record);

    Integer getMaxId();

    int insertPicture(@Param("pic")Picture picture);

    int delete(@Param("storage") CskaoyanMallStorage cskaoyanMallStorage);
}