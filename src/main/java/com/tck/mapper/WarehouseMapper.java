package com.tck.mapper;

import com.tck.entity.Warehouse;
import org.apache.ibatis.annotations.*;

/**
 * Created by tck on 2017/7/17.
 */
public interface WarehouseMapper {

    @Insert("insert into tb_warehouse(productName,remark) values(#{productName},#{remark})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productName", column = "productName"),
            @Result(property = "remark", column = "remark")
    })
    Boolean addWarehouse(@Param("productName")String productName, @Param("remark")String remark);

    @Select("select * from tb_warehouse where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productName", column = "productName"),
            @Result(property = "remark", column = "remark")
    })
    Warehouse findWarehouseByWarehouseId(@Param("id") Integer id);
}
