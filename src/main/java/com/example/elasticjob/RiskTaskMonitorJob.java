package com.example.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.example.elasticjob.config.RegistryCenterConfiguration;
import com.example.elasticjob.entity.TRcmTask;
import com.example.elasticjob.jdbqueryutil.QueryTRcmTask;
import com.example.elasticjob.service.TRcmTaskDataService;
import com.example.elasticjob.service.impl.TRcmTaskDataServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 描述: 风险点监控任务
 * </p>
 * <p>
 * 版权所有: 版权所有(C)2013 -2018
 * </p>
 * <p>
 * 公 司: 华泰证券股份有限公司
 * </p>
 * <p>
 * 版本1.0: 2018年12月10日 新建
 * </p>
 *
 * @author 011336
 * @version 1.0
 */
@Service
public class RiskTaskMonitorJob implements SimpleJob {
    private static final ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
    private static final String key = "BOOLEANFLAG";
    static {
        concurrentHashMap.put(key, "false");
    }
    private static final TRcmTaskDataService tRcmTaskDataService=new TRcmTaskDataServiceImpl();
    @Override
    public void execute(ShardingContext shardingContext) {

        switch (shardingContext.getShardingItem()) {

            case 0:
                List<TRcmTask> tRcmTasks = QueryTRcmTask.queryTRcmTask();
                List<RegistryCenterConfiguration> tRcmTaskDatas = tRcmTaskDataService.getTRcmTaskData(tRcmTasks);
                for(RegistryCenterConfiguration registryCenterConfiguration :tRcmTaskDatas){
                    if ("false".equals(registryCenterConfiguration.getKeyflag())){
                        concurrentHashMap.put(key,"false");
                    }
                    System.out.println(registryCenterConfiguration);
                if (concurrentHashMap.get(key) == "false") {
                    System.out.println(concurrentHashMap.get(key));
                    ElasticJobBuildTemplate elasticJobBuildTemplate =
                            new ElasticJobBuildTemplate
                                    (registryCenterConfiguration.getZkAddressList(),
                                            registryCenterConfiguration.getNamespace(),
                                            "xxxl"+registryCenterConfiguration.getJobName(),
                                            registryCenterConfiguration.getJobParameter(),
                                            registryCenterConfiguration.getShareTotalCount(),
                                            registryCenterConfiguration.getShardingItemParameters(),
                                            "com.example.elasticjob.taskelasticjob.MyJobTask0");
                    System.out.println("Job实例"+registryCenterConfiguration.getJobClassName()+"创建完毕============");
                }
                }
                    concurrentHashMap.put(key, "true");
                    break;
        }
        // case n: ...
    }
}



      /*  for (TRcmTask tRcmTask : validateTasks) {
            {//判断job是否存在
                jobAPIService.getJobStatisticsAPI().getJobBriefInfo("RCM_" + tRcmTask.getcTaskName() + "_" + tRcmTask.getcTaskCode());
            /*如果存在,判断是否有效（这个job的生效日期<today<=失效日期

                    如果无效
                            remove/shutdown job
                    如果有效
                        判断配置有没有改动;(和数据的配置比对)
                        如果改动，修改job配置
                    如果没改动，无需处理
            如果不存在，新增job*/




