package com.yyy.rutu.sxfy.service.impl;

import com.yyy.rutu.sxfy.entity.FUser;
import com.yyy.rutu.sxfy.dao.FUserMapper;
import com.yyy.rutu.sxfy.service.FUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class FUserServiceImpl implements FUserService {

    @Autowired(required = false)
    FUserMapper fUserMapper;//报错不影响，如果不想要报错 给@Autowired设置参数(required = false)

    @Override
    public FUser findUser(int id) {
        return fUserMapper.selectByPrimaryKey(id);
    }
}
