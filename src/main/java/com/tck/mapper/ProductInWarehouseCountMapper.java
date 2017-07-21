package com.tck.mapper;

import com.tck.entity.ProductInWarehouseCount;
import org.apache.ibatis.annotations.*;

/**
 * Created by admin on 2017/7/21.
 */
public interface ProductInWarehouseCountMapper {

    @Insert("insert tb_warehouse_product_count(productId,warehouseId,count) values (#{productId},#{warehouseId},#{count})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productId", column = "productId"),
            @Result(property = "warehouseId", column = "warehouseId"),
            @Result(property = "count", column = "count"),
    })
    Boolean addProductInWarehouseCount(@Param("productId") Integer productId,
                                       @Param("warehouseId") Integer warehouseId,
                                       @Param("count") Integer count);

    @Select("select * from tb_warehouse_product_count where productId=#{productId} and warehouseId = #{warehouseId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productId", column = "productId"),
            @Result(property = "warehouseId", column = "warehouseId"),
            @Result(property = "count", column = "count"),
    })
    ProductInWarehouseCount getProductInWarehouseCount(@Param("productId") Integer productId,
                                                       @Param("warehouseId") Integer warehouseId);

    @Update("update tb_warehouse_product_count set count =count +#{count} where productId =#{warehouseId} and productId =#{warehouseId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productId", column = "productId"),
            @Result(property = "warehouseId", column = "warehouseId"),
            @Result(property = "count", column = "count"),
    })
    Integer updateProductInWarehouseCount(@Param("productId") Integer productId,
                                          @Param("warehouseId") Integer warehouseId,
                                          @Param("count") Integer count);
}
