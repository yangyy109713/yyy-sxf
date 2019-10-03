package com.yyy.rutu.sxfy.service;

import com.yyy.rutu.sxfy.entity.FDept;

import java.util.List;

public interface FDeptService {

    List<FDept> getDepts();

    FDept getDept(Integer id);

}
