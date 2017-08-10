package com.tck.mapper;

import com.tck.entity.PurchaseOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by tck on 2017/7/13.
 */
public interface PurchaseOrderMapper {

    @Insert("insert into tb_purchase_order(user_id,product_id,warehouse_id,count,remark) values (#{userId},#{productId},#{warehouseId},#{count},#{remark})")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "warehouseId", column = "warehouse_id"),
            @Result(property = "count", column = "count"),
            @Result(property = "totalPrice", column = "total_price"),
            @Result(property = "remark", column = "remark")
    })
    Boolean addOrder(@Param("productId") Integer productId,
                     @Param("userId") Integer userId,
                     @Param("warehouseId") Integer warehouseId,
                     @Param("count") Integer count,
                     @Param("totalPrice") Double totalPrice,
                     @Param("remark") String remark);

    @Select("select * from tb_purchase_order where user_id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "warehouseId", column = "warehouse_id"),
            @Result(property = "count", column = "count"),
            @Result(property = "totalPrice", column = "total_price"),
            @Result(property = "remark", column = "remark")
    })
    List<PurchaseOrder> findOrderByUserId(@Param("userId") Integer userId);

    @Select("select * from tb_purchase_order where id = #{id}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "warehouseId", column = "warehouse_id"),
            @Result(property = "count", column = "count"),
            @Result(property = "totalPrice", column = "total_price"),
            @Result(property = "remark", column = "remark")
    })
    PurchaseOrder findOrderByproductId(@Param("id") Integer id);

    @Update("update tb_purchase_order set count=#{count},remark=#{remark} where id = #{id}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "warehouseId", column = "warehouse_id"),
            @Result(property = "count", column = "count"),
            @Result(property = "totalPrice", column = "total_price"),
            @Result(property = "remark", column = "remark")
    })
    Integer updateOrderByProductId(@Param("id") Integer id,
                                   @Param("count") Integer count,
                                   @Param("remark") String remark);

    @Insert("insert into tb_purchase_order(userId,warehouseId,warehouseName," +
            "accountId,accountName,totalCount," +
            "totalPrice,productId,productCount" +
            ",date,remark) " +
            "values (#{userId},#{warehouseId},#{warehouseName}," +
            "#{accountId},#{accountName},#{totalCount}," +
            "#{totalPrice},#{productId},#{productCount},#{date},#{remark})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "warehouseId", column = "warehouseId"),
            @Result(property = "warehouseName", column = "warehouseName"),
            @Result(property = "accountId", column = "accountId"),
            @Result(property = "accountName", column = "accountName"),
            @Result(property = "totalCount", column = "totalCount"),
            @Result(property = "totalPrice", column = "totalPrice"),
            @Result(property = "productId", column = "productId"),
            @Result(property = "productCount", column = "productCount"),
            @Result(property = "date", column = "date"),
            @Result(property = "remark", column = "remark")
    })
    Boolean addOrder(@Param("userId") Integer userId,
                     @Param("warehouseId") Integer warehouseId,
                     @Param("warehouseName") String warehouseName,
                     @Param("accountId") Integer accountId,
                     @Param("accountName") String accountName,
                     @Param("totalCount") Integer totalCount,
                     @Param("totalPrice") Double totalPrice,
                     @Param("productId") String productId,
                     @Param("productCount") String productCount,
                     @Param("date") String date,
                     @Param("remark") String remark);
}
