package com.elasticjob.demo;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2018/10/27.
 */
public class DataFlowDemo implements DataflowJob<String> {

    int i=0;


    @Override
    public List<String> fetchData(ShardingContext shardingContext) {

        System.out.println("--zhua qu shu ju --"+Thread.currentThread().getName());

        System.out.println(String.format("------Thread ID: %s, 任务总片数: %s, " +
                        "当前分片项: %s.当前参数: %s,"+
                        "当前任务名称: %s.当前任务参数: %s"
                ,
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()));

        List<String> list = initData();

        return list;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<String> list) {

        for (String str: list) {
            System.out.println("--strstrstr---"+str);
        }

    }


    public List<String> initData(){

       if(i==10){
           return null;
       }
       List<String> list = new ArrayList<>();
       list.add("第 "+i+" 次获取数据 "+Thread.currentThread().getName());

       i= i+1;
       return list;

    }
}
