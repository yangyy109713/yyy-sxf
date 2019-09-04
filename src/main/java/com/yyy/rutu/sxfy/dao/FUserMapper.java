package com.yyy.rutu.sxfy.dao;

import com.yyy.rutu.sxfy.entity.FUser;

public interface FUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FUser record);

    int insertSelective(FUser record);

    FUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FUser record);

    int updateByPrimaryKey(FUser record);
}