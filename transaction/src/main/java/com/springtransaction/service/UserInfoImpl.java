package com.springtransaction.service;

import com.springtransaction.dao.ClassRoomMapper;
import com.springtransaction.dao.UserInfoMapper;
import com.springtransaction.povo.ClassRoom;
import com.springtransaction.povo.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by liang on 2018/11/5.
 */
@Component
public class UserInfoImpl implements UserInfoManager {

    @Resource
    ClassRoomMapper classRoomMapper;
    @Resource
    UserInfoMapper userInfoMapper;

//*******事务没有生效的原因********
//    第一种：在针对事务的类中抛出RuntimeException异常，而不是抛出Exception。
//    第二种： 不能在方法中使用try catch抛出异常，不然不会回滚
//    第三种：
//    mysql默认存储引擎为MyISAM是不支持事务的，  
//    需要设置为InnoDB模式，通过show engines; 命令看到  
//    ********** 错误的方式 *************
//    @Transactional
//    public void save(UserInfo userInfo, ClassRoom classRoom){
//        try {
//            userInfoMapper.insert(userInfo);
//            classRoomMapper.insert(classRoom);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//    }


    @Transactional
    public void save(UserInfo userInfo, ClassRoom classRoom) throws RuntimeException{
        userInfoMapper.insert(userInfo);
        classRoomMapper.insert(classRoom);
    }

}
