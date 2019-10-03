package com.yyy.rutu.sxfy.service.impl;

import com.yyy.rutu.sxfy.dao.FUserMapper;
import com.yyy.rutu.sxfy.entity.FUser;
import com.yyy.rutu.sxfy.service.FUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FUserServiceImpl implements FUserService {

    @Autowired(required = false)
    FUserMapper fUserMapper;//报错不影响，如果不想要报错 给@Autowired设置参数(required = false)

    @Override
    public FUser getUser(int id) {
        return fUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public FUser getUserByNameAndPass(String name, String password) {
        FUser fUser = fUserMapper.selectUserByNameAndPass(name, password);
        return fUser;
    }

    @Override
    public List<FUser> getUserList() {
        return fUserMapper.selectUserList();
    }

    @Override
    public int addUser(FUser user) {
       return fUserMapper.insert(user);
    }

    @Override
    public int updateUser(FUser user) {
        return fUserMapper.updateByPrimaryKey(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return fUserMapper.deleteByPrimaryKey(id);
    }
}
