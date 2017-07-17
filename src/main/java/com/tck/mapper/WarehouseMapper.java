package com.tck.mapper;

import com.tck.entity.Warehouse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Created by tck on 2017/7/17.
 */
public interface WarehouseMapper {

    Boolean addWarehouse(String productName, String remark);

    @Select("select * from tb_warehouse where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productName", column = "productName"),
            @Result(property = "productId", column = "productId"),
            @Result(property = "productCount", column = "productCount"),
            @Result(property = "remark", column = "remark"),
    })
    Warehouse findWarehouseByWarehouseId(@Param("id") Integer id);
}
