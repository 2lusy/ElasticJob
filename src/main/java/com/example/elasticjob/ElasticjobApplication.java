package com.example.elasticjob;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.elasticjob.repositroy")
public class ElasticjobApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticjobApplication.class, args);
		//job0实例
		new JobScheduler(createRegistryCenter(),createJobConfiguration("jobMonitor","0/6 * * * * ?",1,"0=A",RiskTaskMonitorJob.class.getCanonicalName())).init();

	}

	/**
	 * 集群注册中心
	 * @return
	 */
	public static CoordinatorRegistryCenter createRegistryCenter() {
		//168.61.2.24:12181,168.61.2.25:12181 这个为zk的地址
		//demo-job 这个为1个zk环境的下的1个namespace 可以有多个 1个namespace下有多个job
		CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(
				new ZookeeperConfiguration("168.61.2.25:12181", "GZFWPT-JOBS"));
		regCenter.init();
		return regCenter;
	}

	/**
	 * job0配置分3片
	 * @param jobParameter
	 * @return
	 */
	public static LiteJobConfiguration createJobConfiguration(String jobName,String jobParameter,int shareTotalCount,String shardingItemParameters,String jobClassName) {
		// mySimpleTest 为jobname 0/10 * * * * ?为cron表达式  2 分片数量  0=北京,1=上海 分片对应内容  jobParameter 自定义参数
		JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder(jobName, jobParameter,shareTotalCount).shardingItemParameters(shardingItemParameters).build();
		SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, jobClassName);
		LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true).build();
		return  simpleJobRootConfig;
	}




}
