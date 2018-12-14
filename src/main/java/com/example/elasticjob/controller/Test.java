package com.example.elasticjob.controller;

import com.alibaba.fastjson.JSON;
import com.example.elasticjob.config.RegistryCenterConfiguration;
import com.example.elasticjob.entity.TRcmTask;
import com.example.elasticjob.jdbqueryutil.QueryTRcmTask;
import com.example.elasticjob.repositroy.TRcmTaskRepository;
import com.example.elasticjob.service.TRcmTaskDataService;
import com.example.elasticjob.service.impl.TRcmTaskDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: K0370098
 * @Date: 2018/12/12 20:06
 * @Description:
 */
@RestController
public class Test {
    @Autowired
    private TRcmTaskRepository tRcmTaskRepository;
    private static final TRcmTaskDataService tRcmTaskDataService=new TRcmTaskDataServiceImpl();
    @GetMapping(value = "/test")
    public List<TRcmTask> test() {
        List<TRcmTask> all = tRcmTaskRepository.findAll();
       /* List<TRcmTask> tRcmTasks = QueryTRcmTask.queryTRcmTask();
        List<RegistryCenterConfiguration> tRcmTaskDatas = tRcmTaskDataService.getTRcmTaskData(tRcmTasks);
        for(RegistryCenterConfiguration registryCenterConfiguration :tRcmTaskDatas){
            System.out.println(registryCenterConfiguration);

        }*/
        return all;
    }
}
