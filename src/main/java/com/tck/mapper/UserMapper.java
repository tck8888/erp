package com.tck.mapper;

import com.tck.entity.User;
import org.apache.ibatis.annotations.*;


/**
 * Created by admin on 2017/7/11.
 */
public interface UserMapper {


    @Insert("insert into tb_user(username,password) values (#{username},#{password})")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password")
    })
    Boolean regisetr(@Param("username") String username, @Param("password") String password);

    @Select("select * from tb_user where username = #{username} and password=#{password}")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password")
    })
    User login(@Param("username") String username, @Param("password") String password);

    @Select("select * from tb_user where username = #{username}")
    @Results({
            @Result(property = "username", column = "username")
    })
    User findUserByUserName(@Param("username") String username);
}
