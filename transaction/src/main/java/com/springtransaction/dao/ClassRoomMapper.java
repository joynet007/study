package com.springtransaction.dao;

import com.springtransaction.povo.ClassRoom;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

/**
 * Created by liang on 2018/11/5.
 */
@Component
public interface ClassRoomMapper {

    @Insert("insert into tb_classroom (id,classname) values (#{id},#{classname})")
    void insert(ClassRoom classRoom);
}
