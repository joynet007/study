/**
 * Created by liang on 2018/11/5.
 *
 * 介绍zookeeper 分布式锁的基本原理
 * 一、首先要说明ZooKeeper的特性， 数据在在Zookeeper中的存储是采用树形级联节点的方式存储。
 * 二、zookeeper 之间可以同步数据确保数据一致性
 * 三、zookeeper 可以监听节点的事件（删除）
 *
 * 基于ZooKeeper分布式锁的流程
   1）在zookeeper指定节点（locks）下创建临时顺序节点node_n
   2）获取locks下所有子节点children
   3）对子节点按节点自增序号从小到大排序
   4）判断本节点是不是第一个子节点，若是，则获取锁；若不是，则监听比该节点小的那个节点的删除事件
   5）若监听事件生效，则回到第二步重新进行判断，直到获取到锁
 *
 *
 * Curator 这个是zookeeper 的一个客户端的操作包，
 * **************目前zookeeper使用版本是 3.4.13  ,  Curator 的版本是 2.7.1  这个一定需要注意，否则客户端和zookeeper出现不兼容**************
 */
package com.zookeeper.curator;