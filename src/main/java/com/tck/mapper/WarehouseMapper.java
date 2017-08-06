package com.tck.mapper;

import com.tck.entity.Warehouse;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by tck on 2017/7/17.
 */
public interface WarehouseMapper {

    @Insert("insert into tb_warehouse(productName,remark,user_id) values(#{productName},#{remark},#{userId})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productName", column = "productName"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "userId", column = "user_id")
    })
    Boolean addWarehouse(@Param("productName") String productName, @Param("remark") String remark, @Param("userId") Integer userId);

    @Select("select * from tb_warehouse where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productName", column = "productName"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "userId", column = "user_id")
    })
    Warehouse findWarehouseByWarehouseId(@Param("id") Integer id);

    @Select("select * from tb_warehouse where user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productName", column = "productName"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "userId", column = "user_id")
    })
    List<Warehouse> getWarehouseList(@Param("userId") Integer userId);
}
