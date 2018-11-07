package com.elasticjob.demo;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * Created by liang on 2018/10/27.
 */
public class SimpleJobDemo implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext)
    {

        try {
            Thread.sleep(5000);
            print(shardingContext);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void print(ShardingContext shardingContext){
        System.out.println(String.format("------Thread ID: %s, 任务总片数: %s, " +
                        "当前分片项: %s.当前参数: %s,"+
                        "当前任务名称: %s.当前任务参数: %s"
                ,
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()

        ));
    }

}

