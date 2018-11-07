package com.springtransaction.dao;

import com.springtransaction.povo.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

/**
 * Created by liang on 2018/11/5.
 */
@Component
public interface UserInfoMapper {

    @Insert("insert into tb_userinfo (userid,username) values (#{userid},#{username})")
    void insert(UserInfo userInfo);
}
