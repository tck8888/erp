package com.tck.mapper;

import com.tck.entity.Product;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * Created by admin on 2017/7/11.
 */
public interface ProductMapper {

    @Select("select * from tb_product where user_id = #{userId}")
    @Results({
            @Result(property = "productName",column = "product_name" ),
            @Result(property = "productPrice",column = "product_price" ),
            @Result(property = "productImage",column = "product_image" ),
            @Result(property = "remark",column = "remark" ),
            @Result(property = "userId",column = "user_id" ),
    })
    List<Product> findProductByUserId(int userId);
}
