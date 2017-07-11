package com.tck.mapper;

import com.tck.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by admin on 2017/7/11.
 */
public interface UserMapper {

    @Select("select * from tb_user")
    List<User> findAll();
}
