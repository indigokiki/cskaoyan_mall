package com.cskaoyan.service.mallmanagement;

import com.cskaoyan.bean.CskaoyanMallCategory;
import com.cskaoyan.bean.CskaoyanMallCategoryExample;
import com.cskaoyan.bean.MallCategoryPlus;
import com.cskaoyan.mapper.CskaoyanMallCategoryMapper;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.ValueNLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MallManagement3ServiceImpl implements MallManagement3Service {

    @Autowired
    CskaoyanMallCategoryMapper cskaoyanMallCategoryMapper;

    @Override
    public ResponseVo getCategoryList() {
        /**
         * 先取出L1级的类别，并将它们全部转成子类MallCategoryPlus
         * 以便再往里面塞入L2级类别
         */
        CskaoyanMallCategoryExample l1cskaoyanMallCategoryExample = new CskaoyanMallCategoryExample();
        CskaoyanMallCategoryExample.Criteria l1criteria = l1cskaoyanMallCategoryExample.createCriteria();
        l1criteria.andLevelEqualTo("L1").andDeletedNotEqualTo(true);
        List<CskaoyanMallCategory> l1MallCategories = cskaoyanMallCategoryMapper.selectByExample(l1cskaoyanMallCategoryExample);
        ArrayList<MallCategoryPlus> mallCategoryPluses = new ArrayList<>();
        for (CskaoyanMallCategory l1MallCategory : l1MallCategories) {
            MallCategoryPlus mcp = new MallCategoryPlus(l1MallCategory);
            mallCategoryPluses.add(mcp);
        }

        /**
         * 取出各L1级对应的L2级目录，并塞进加强的javabean里
         */
        for (MallCategoryPlus mallCategoryPlus : mallCategoryPluses) {
            CskaoyanMallCategoryExample l2cskaoyanMallCategoryExample = new CskaoyanMallCategoryExample();
            CskaoyanMallCategoryExample.Criteria l2criteria = l2cskaoyanMallCategoryExample.createCriteria();
            l2criteria.andLevelEqualTo("L2").andPidEqualTo(mallCategoryPlus.getId()).andDeletedNotEqualTo(true);
            List<CskaoyanMallCategory> l2cskaoyanMallCategories = cskaoyanMallCategoryMapper.selectByExample(l2cskaoyanMallCategoryExample);
            mallCategoryPlus.setChildren(l2cskaoyanMallCategories);
        }
        /**
         * 最后把MallCategoryPlus的列表放进ResponseVo里面，返回给前端
         */
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(mallCategoryPluses);
        return responseVo;
    }

    @Override
    public ResponseVo getValueNLabel() {
        List<ValueNLabel> l1ValueNLable = cskaoyanMallCategoryMapper.getL1ValueNLable();
        ResponseVo responseVo = afterOperation(1, l1ValueNLable, false);
        return responseVo;
    }

    @Override
    public ResponseVo deleteCategory(MallCategoryPlus mallCategoryPlus) {
        if(null != mallCategoryPlus.getChildren()){
            List<CskaoyanMallCategory> children = mallCategoryPlus.getChildren();
            for (CskaoyanMallCategory child : children) {
                child.setDeleted(true);
                int i = cskaoyanMallCategoryMapper.updateByPrimaryKey(child);
                if(1 != i){
                    return afterOperation(0,child,true);
                }
            }
        }
        CskaoyanMallCategory categoryl1 = mallCategoryPlus;
        categoryl1.setDeleted(true);
        int i = cskaoyanMallCategoryMapper.updateByPrimaryKey(categoryl1);
        if(1 != i){
            return afterOperation(0,categoryl1,true);
        }
        return afterOperation(1,null,true);
    }

    @Override
    public ResponseVo insertCategory(CskaoyanMallCategory cskaoyanMallCategory) {
        Date date = new Date();
        cskaoyanMallCategory.setAddTime(date);
        cskaoyanMallCategory.setUpdateTime(date);
        cskaoyanMallCategory.setDeleted(false);
        Byte sortorder = cskaoyanMallCategoryMapper.selectMaxSortOrderFromPid(cskaoyanMallCategory.getPid());
        byte sortorder2;
        if(null != sortorder){
            sortorder2 = (byte)(sortorder + 1);
        }else{
            sortorder2 = 0;
        }
        cskaoyanMallCategory.setSortOrder(sortorder2);
        int insert = cskaoyanMallCategoryMapper.insertSelective(cskaoyanMallCategory);
        if(1 == insert){
            cskaoyanMallCategory.setId(cskaoyanMallCategoryMapper.selectLastUpdate());
            return afterOperation(1,cskaoyanMallCategory,false);
        }
        return afterOperation(0,null,false);
    }

    @Override
    public ResponseVo updateCategory(MallCategoryPlus mallCategoryPlus) {
        CskaoyanMallCategory cmc = mallCategoryPlus;
        cmc.setUpdateTime(new Date());
        int i = cskaoyanMallCategoryMapper.updateByPrimaryKey(cmc);
        if(1 == i){
            return afterOperation(1,null,true);
        }
        return afterOperation(0,null,true);
    }

    /*private MallCategoryPlus castToMCP(CskaoyanMallCategory l1MallCategory) {
        MallCategoryPlus mallCategoryPlus = new MallCategoryPlus();
        mallCategoryPlus.setAddTime(l1MallCategory.getAddTime());
        mallCategoryPlus.setDeleted(l1MallCategory.getDeleted());
        mallCategoryPlus.setDesc(l1MallCategory.getDesc());
        mallCategoryPlus.setIconUrl(l1MallCategory.getIconUrl());
        mallCategoryPlus.setId(l1MallCategory.getId());
        mallCategoryPlus.setKeywords(l1MallCategory.getKeywords());
        mallCategoryPlus.setLevel(l1MallCategory.getLevel());
        mallCategoryPlus.setName(l1MallCategory.getName());
        mallCategoryPlus.setPicUrl(l1MallCategory.getPicUrl());
        mallCategoryPlus.setPid(l1MallCategory.getPid());
        mallCategoryPlus.setSortOrder(l1MallCategory.getSortOrder());
        mallCategoryPlus.setUpdateTime(l1MallCategory.getUpdateTime());
        return mallCategoryPlus;
    }*/

    /*private ResponseVo getSelectResponesVo(int page, int limit, CskaoyanMallCategoryExample example){
        PageHelper.startPage(page,limit);
        List<CskaoyanMallCategory> issues = cskaoyanMallCategoryMapper.selectByExample(example);
        Page<CskaoyanMallCategory> issuePage = new Page<>();
        issuePage.setItems(issues);
        issuePage.setTotal((int)cskaoyanMallCategoryMapper.countByExample(example));
        ResponseVo<Page> responseVo = new ResponseVo<>();
        responseVo.setData(issuePage);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }*/

    private ResponseVo afterOperation(int status,Object object,boolean dontSetData){
        ResponseVo<Object> responseVo = new ResponseVo<>();
        if(1 == status){
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");
            if(!dontSetData){
                responseVo.setData(object);
            }
            return responseVo;
        }
        responseVo.setErrmsg("操作失败，请联系管理员");
        responseVo.setErrno(500);
        return responseVo;
    }


}
