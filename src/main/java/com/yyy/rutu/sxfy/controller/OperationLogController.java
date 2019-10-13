package com.yyy.rutu.sxfy.controller;


import com.yyy.rutu.sxfy.elastic.OperationLogElasticsearchRepository;
import com.yyy.rutu.sxfy.elastic.OperationLogRepository;
import com.yyy.rutu.sxfy.entity.LogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
public class OperationLogController {

    @Autowired
    //private OperationLogElasticsearchRepository logElasticsearchRepository;
    private OperationLogRepository operationLogRepository;


    //根据索引查询
    @GetMapping(value = "/sxfy/{id}")
    public LogEntity getLogById(@PathVariable("id") String id){
        Long lid = Long.valueOf(id);
        Optional<LogEntity> logEntityOptional = operationLogRepository.findById(lid);
        return logEntityOptional.orElse(null);
    }

    //新增索引：有参数logEntity
    //Postman测试时，无传参，访问不成功
    @PostMapping(value = "/sxfy/EsLog")
    public LogEntity addLog(@RequestBody LogEntity logEntity){
        logEntity.setId("20191012001");
        logEntity.setName("system");
        logEntity.setOperation("add Es Log");
        logEntity.setTime(new Date(System.currentTimeMillis()));
        return operationLogRepository.save(logEntity);
    }


    @PostMapping(value = "/sxfy/testEsLog")
    public LogEntity addLog(){
        LogEntity logEntity = new LogEntity();
        logEntity.setId("20191012001");
        logEntity.setName("system");
        logEntity.setOperation("add Test Es Log");
        logEntity.setTime(new Date(System.currentTimeMillis()));
        return operationLogRepository.save(logEntity);
    }
}
