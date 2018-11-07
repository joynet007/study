package com.elasticjob.demo;

import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.beans.Transient;

@Component
public class JobStart {
    
//    public static void main(String[] args) {
//        new JobScheduler(createRegistryCenter(), createJobConfiguration()).init();
//    }
//

    @PostConstruct
    private void exeJob(){
        System.out.println("--系统执行--");
        new JobScheduler(createRegistryCenter(), createJobConfiguration()).init();
//        new JobScheduler(createRegistryCenter(), configuration()).init();
    }

    private CoordinatorRegistryCenter createRegistryCenter() {

//        ZookeeperConfiguration zookeeperConfiguration =  new ZookeeperConfiguration("192.168.80.148:2181", "elastic-job-demo");
        ZookeeperConfiguration zookeeperConfiguration =  new ZookeeperConfiguration("192.168.16.234:2181", "elastic-job-demo-002");
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);

        regCenter.init();

        return regCenter;
    }

    /**
     * 创建一个 simpleJobRootConfig
     * @return
     */
    private static LiteJobConfiguration createJobConfiguration() {
        // 创建作业配置
        // ...
        String shardingItemParameters = "0=Beijing,1=Shanghai,2=Guangzhou,3=nanjing,4=tianjin";
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("demoSimpleJob", "0/15 * * * * ?", 2).shardingItemParameters(shardingItemParameters).build();
        // 定义SIMPLE类型配置
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, SimpleJobDemo.class.getCanonicalName());
        // 定义Lite作业根配置
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();

        return simpleJobRootConfig;
    }

    /**
     * 创建一个 dataflowJobConfig
     * @return
     */
    private static LiteJobConfiguration configuration() {
        // 定义作业核心配置
        String shardingItemParameters = "0=Beijing,1=Shanghai,2=Guangzhou";
//        JobCoreConfiguration dataflowCoreConfig
//                = JobCoreConfiguration.newBuilder("dataflowJob", "0/15 * * * * ?", 1).shardingItemParameters(shardingItemParameters).build();
        int k = 10;
        JobCoreConfiguration dataflowCoreConfig
                = JobCoreConfiguration.newBuilder("dataflowJob", "0/30 * * * * ?", k).build();

        int t = dataflowCoreConfig.getShardingTotalCount();

        System.out.println("*************"+t);
        // 定义DATAFLOW类型配置
        DataflowJobConfiguration dataflowJobConfig = new DataflowJobConfiguration(dataflowCoreConfig, DataFlowDemo.class.getCanonicalName(), false);

        LiteJobConfiguration dataflowJobRootConfig = LiteJobConfiguration.newBuilder(dataflowJobConfig).build();

        return dataflowJobRootConfig;
    }



}