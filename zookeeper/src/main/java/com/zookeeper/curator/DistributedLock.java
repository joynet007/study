package com.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by liang on 2018/11/6.
 */

@Component
//@Resource
public class DistributedLock {


    public void exeCurator() throws Exception {

        System.out.println("--nihao!!!-");
//        创建zookeeper的客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.16.234:2181", retryPolicy);
        client.start();
        //创建分布式锁, 锁空间的根节点路径为/curator/lock
        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");
        mutex.acquire();
//        mutex.acquire(10, TimeUnit.SECONDS);

        Thread.sleep(10*1000);
//
        //获得了锁, 进行业务流程
        System.out.println("Enter mutex"+System.currentTimeMillis());
        //完成业务流程, 释放锁
        mutex.release();
        //关闭客户端
        client.close();

    }

    @PostConstruct
    public void exe(){
        for(int i=0;i<3;i++){
            new MainThread().start();
        }
    }


    /**
     *
     */
    public class MainThread extends Thread{

        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName()+"==线程的名称");
            try {
                exeCurator();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }




}
