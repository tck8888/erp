package com.tck.mapper;

import com.tck.entity.PurchaseOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by tck on 2017/7/13.
 */
public interface PurchaseOrderMapper {

    @Insert("insert into tb_purchase_order(user_id,product_id,count,remark) values (#{userId},#{productId},#{count},#{remark})")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "count", column = "count"),
            @Result(property = "remark", column = "remark")
    })
    Boolean addOrder(@Param("productId") Integer productId,
                     @Param("userId") Integer userId,
                     @Param("count") Integer count,
                     @Param("remark") String remark);

    @Select("select * from tb_purchase_order where user_id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "count", column = "count"),
            @Result(property = "remark", column = "remark")
    })
    List<PurchaseOrder> findOrderByUserId(@Param("userId") Integer userId);

    @Select("select * from tb_purchase_order where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "count", column = "count"),
            @Result(property = "remark", column = "remark")
    })
    PurchaseOrder findOrderByproductId(@Param("id") Integer id);

    @Update("update tb_purchase_order set count=#{count},remark=#{remark} where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "count", column = "count"),
            @Result(property = "remark", column = "remark")
    })
    Integer updateOrderByProductId(@Param("id") Integer id,
                                   @Param("count") Integer count,
                                   @Param("remark") String remark);
}
