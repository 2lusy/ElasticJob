package com.example.elasticjob.taskelasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
/**
 * @Auther: K0370098
 * @Date: 2018/12/13 16:34
 * @Description:
 */
public class MyJobTask0 implements SimpleJob {
    /**
     * 执行作业.
     *
     * @param shardingContext 分片上下文
     */
    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {

            case 0:
                // do something by sharding item 0
                System.out.println(context.getJobName()+"case 0================================");
                break;
            case 1:
                // do something by sharding item 1

                System.out.println(context.getJobName()+"case 1================================");
                break;
            case 2:
                // do something by sharding item 2
                System.out.println(context.getJobName()+"case 2================================");
                break;
            // case n: ...
        }
    }
}
