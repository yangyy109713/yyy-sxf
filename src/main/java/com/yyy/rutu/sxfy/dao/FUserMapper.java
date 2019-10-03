package com.yyy.rutu.sxfy.dao;

import com.yyy.rutu.sxfy.entity.FUser;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FUser record);

    int insertSelective(FUser record);

    FUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FUser record);

    int updateByPrimaryKey(FUser record);

    FUser selectUserByNameAndPass(@RequestParam("userName") String userName,
                                @RequestParam("password") String password);

    List<FUser> selectUserList();
}