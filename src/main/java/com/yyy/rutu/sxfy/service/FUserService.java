package com.yyy.rutu.sxfy.service;

import com.yyy.rutu.sxfy.entity.FUser;

import java.util.List;

public interface FUserService {

    FUser getUser(int id);//根据id查询用户

    FUser getUserByNameAndPass(String name, String password);

    List<FUser> getUserList();

    int addUser(FUser user);

    int updateUser(FUser user);

    int deleteUser(Integer id);
}
