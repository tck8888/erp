package com.tck.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

/**
 * Created by tck on 2017/7/13.
 */
public interface PurchaseOrderMapper {

    @Insert("insert into tb_purchase_order(user_id,product_id,count,remark) values (#{userId},#{productId},#{count},#{remark})")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "count", column = "count"),
            @Result(property = "remark", column = "remark"),
    })
    Boolean addOrder(@Param("productId") Integer productId,
                     @Param("userId")Integer userId,
                     @Param("count") Integer count,
                     @Param("remark")String remark);
}
