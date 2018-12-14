package com.example.elasticjob;


import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * <p>
 * 描述: job 创建模板
 * </p>
 * <p>
 * 版权所有: 版权所有(C)2013 -2018
 * </p>
 * <p>
 * 公 司: 华泰证券股份有限公司
 * </p>
 * <p>
 * 版本1.0: 2018年12月11日 新建
 * </p>
 *
 * @author 011336
 * @version 1.0
 */
public  class  ElasticJobBuildTemplate {
    private  String zkAddressList;
    private  String namespace;
    private  String jobName;
    private  String jobParameter;
    private  Integer shareTotalCount;
    private  String shardingItemParameters;
    private String jobClassName;
    public ElasticJobBuildTemplate(String zkAddressList,String namespace,String jobName, String jobParameter, int shareTotalCount, String shardingItemParameters,String jobClassName){
        this.zkAddressList=zkAddressList;
        this.namespace=namespace;
        this.jobName=jobName;
        this.jobParameter=jobParameter;
        this.shareTotalCount=shareTotalCount;
        this.shardingItemParameters=shardingItemParameters;
        this.jobClassName=jobClassName;
        new JobScheduler(createRegistryCenter(),createJobConfiguration()).init();
    }

    /**
     * 集群注册中心
     * @return
     */
    public CoordinatorRegistryCenter createRegistryCenter() {
        //168.61.2.24:12181,168.61.2.25:12181 这个为zk的地址
        //demo-job 这个为1个zk环境的下的1个namespace 可以有多个 1个namespace下有多个job
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(
                new ZookeeperConfiguration(this.zkAddressList, this.namespace));
        regCenter.init();
        return regCenter;
    }

    /**
     * job0配置分3片
     * @return
     */
    public LiteJobConfiguration createJobConfiguration() {
        // mySimpleTest 为jobname 0/10 * * * * ?为cron表达式  2 分片数量  0=北京,1=上海 分片对应内容  jobParameter 自定义参数
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder(this.jobName, this.jobParameter,this.shareTotalCount).shardingItemParameters(this.shardingItemParameters).build();
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, this.jobClassName);
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true).build();
        return  simpleJobRootConfig;
    }

}
