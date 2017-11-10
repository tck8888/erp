package com.tck.mapper;

import com.tck.entity.User;
import org.apache.ibatis.annotations.*;


/**
 * Created by admin on 2017/7/11.
 */
@Mapper
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

    @Update("update tb_user set nickname =#{nickname} where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "email", column = "email"),
    })
    Integer updateUserNickName(@Param("id") Integer id, @Param("nickname") String nickname);

    @Update("update tb_user set email =#{email} where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "email", column = "email"),
    })
    Integer updateUserEmail(@Param("id") Integer id, @Param("email") String email);

    @Select("select * from tb_user where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "nickName", column = "nickName"),
            @Result(property = "email", column = "email"),
    })
    User findUserById(@Param("id") Integer id);
}
