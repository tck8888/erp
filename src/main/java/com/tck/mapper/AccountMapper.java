package com.tck.mapper;

import com.tck.entity.AccountBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by tck on 2017/8/6.
 */
public interface AccountMapper {

    @Insert("insert into tb_account(accountName,remark,userId) values(#{accountName},#{remark},#{userId})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "accountName", column = "accountName"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "userId", column = "userId")
    })
    Boolean addAccount(@Param("accountName") String accountName,
                       @Param("remark") String remark,
                       @Param("userId") Integer userId);

    @Select("select * from tb_account where userId = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "accountName", column = "accountName"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "userId", column = "userId")
    })
    List<AccountBean> getAccountList(@Param("userId") Integer userId);
}
