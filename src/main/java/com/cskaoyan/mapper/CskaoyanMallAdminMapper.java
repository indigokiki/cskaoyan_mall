package com.cskaoyan.mapper;

import com.cskaoyan.bean.CskaoyanMallAdmin;
import com.cskaoyan.bean.CskaoyanMallAdminExample;
import com.cskaoyan.bean.mallmanage.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallAdminMapper {
    long countByExample(CskaoyanMallAdminExample example);

    int deleteByExample(CskaoyanMallAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallAdmin record);

    int insertSelective(CskaoyanMallAdmin record);

    List<CskaoyanMallAdmin> selectByExample(CskaoyanMallAdminExample example);

    CskaoyanMallAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallAdmin record, @Param("example") CskaoyanMallAdminExample example);

    int updateByExample(@Param("record") CskaoyanMallAdmin record, @Param("example") CskaoyanMallAdminExample example);

    int updateByPrimaryKeySelective(CskaoyanMallAdmin record);

    int updateByPrimaryKey(CskaoyanMallAdmin record);


    String selectPassword(@Param("username") String username);

    Admin getInfoAdd(@Param("username") String username);
    List<String> getRoles(@Param("ids") String[] ids);

    List<String> getPerms(@Param("ids") String[] ids);

    List<CskaoyanMallAdmin> selectByMyExample(CskaoyanMallAdminExample example);

<<<<<<< Updated upstream
=======
    int delete(@Param("admin") CskaoyanMallAdmin cskaoyanMallAdmin);

    Integer selectLastUpdate();
>>>>>>> Stashed changes
}