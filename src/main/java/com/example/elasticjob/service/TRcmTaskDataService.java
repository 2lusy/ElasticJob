package com.example.elasticjob.service;

import com.example.elasticjob.config.RegistryCenterConfiguration;
import com.example.elasticjob.entity.TRcmTask;

import java.util.List;

/**
 * @Auther: K0370098
 * @Date: 2018/12/12 14:37
 * @Description:
 */
public interface TRcmTaskDataService {
    List<RegistryCenterConfiguration> getTRcmTaskData(List<TRcmTask> tRcmTasks);

}
