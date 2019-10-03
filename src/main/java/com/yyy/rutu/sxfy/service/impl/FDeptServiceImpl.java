package com.yyy.rutu.sxfy.service.impl;

import com.yyy.rutu.sxfy.dao.FDeptMapper;
import com.yyy.rutu.sxfy.entity.FDept;
import com.yyy.rutu.sxfy.service.FDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FDeptServiceImpl implements FDeptService {

    @Autowired(required = false)
    FDeptMapper fDeptMapper;

    @Override
    public List<FDept> getDepts() {
        return fDeptMapper.selectDeptList();
    }

    @Override
    public FDept getDept(Integer id) {
        return fDeptMapper.selectByPrimaryKey(id);
    }
}
