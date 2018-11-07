package com.springtransaction.service;

import com.springtransaction.povo.ClassRoom;
import com.springtransaction.povo.UserInfo;

/**
 * Created by liang on 2018/11/5.
 */
public interface UserInfoManager {
    public void save(UserInfo userInfo, ClassRoom classRoom);
}
