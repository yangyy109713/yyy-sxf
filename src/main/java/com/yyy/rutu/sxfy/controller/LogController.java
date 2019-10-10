package com.yyy.rutu.sxfy.controller;


import com.yyy.rutu.sxfy.entity.LogEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@RestController
public class LogController {

    @Resource
    private PagingAndSortingRepository<LogEntity, String> logEntityStringPagingAndSortingRepository;

    @GetMapping("/sxfy/{id}")
    public LogEntity getLogById(@PathVariable("id") String id){
        Optional<LogEntity> logEntityOptional = logEntityStringPagingAndSortingRepository.findById(id);
        return logEntityOptional.orElse(null);
    }

    @PostMapping("/sxfy")
    public LogEntity addLog(@RequestBody LogEntity logEntity){
        logEntity.setId("20191005001");
        logEntity.setName("system");
        logEntity.setOperation("addLog");
        logEntity.setTime(new Date(System.currentTimeMillis()));

        return logEntityStringPagingAndSortingRepository.save(logEntity);
    }
}
