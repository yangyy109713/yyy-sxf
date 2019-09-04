package com.yyy.rutu.sxfy.service.impl;

import com.yyy.rutu.sxfy.entity.FUser;
import com.yyy.rutu.sxfy.mapper.FUserMapper;
import com.yyy.rutu.sxfy.service.FUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class FUserServiceImpl implements FUserService {

    @Autowired
    FUserMapper fUserMapper;

    @Override
    public FUser findUser(int id) {
        return fUserMapper.selectByPrimaryKey(id);
    }
}
