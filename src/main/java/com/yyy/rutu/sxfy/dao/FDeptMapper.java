package com.yyy.rutu.sxfy.dao;

import com.yyy.rutu.sxfy.entity.FDept;

import java.util.List;

public interface FDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FDept record);

    int insertSelective(FDept record);

    FDept selectByPrimaryKey(Integer id);

    List<FDept> selectDeptList();

    int updateByPrimaryKeySelective(FDept record);

    int updateByPrimaryKey(FDept record);
}