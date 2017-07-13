package com.tck.service;

import com.tck.base.BaseData;
import com.tck.entity.Product;

import java.util.List;

/**
 * Created by tck on 2017/7/12.
 */
public interface ProductService {

    BaseData<String> addProduct(String productName, Double productPrice, String productImage, String remark, Integer userId);

    BaseData<List<Product>> findProductByUserId(int userId);

    BaseData<String> updateProduct(String productName, Double productPrice, String productImage, String remark, Integer userId);
}
