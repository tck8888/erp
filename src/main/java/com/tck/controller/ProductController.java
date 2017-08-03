package com.tck.controller;

import com.tck.base.BaseData;
import com.tck.entity.Product;
import com.tck.mapper.ProductMapper;
import com.tck.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by admin on 2017/7/11.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public BaseData<List<Product>> findAll(@RequestParam("userId") int userId) {
        return productService.findProductByUserId(userId);
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public BaseData<String> addProduct(@RequestParam("productName") String productName,
                                @RequestParam("productPrice") Double productPrice,
                                @RequestParam("productImage") String productImage,
                                @RequestParam("remark") String remark,
                                @RequestParam("userId") Integer userId) {
        return productService.addProduct(productName,productPrice,productImage,remark,userId);
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
    public BaseData<String> updateProduct(@RequestParam("productName") String productName,
                                       @RequestParam("productPrice") Double productPrice,
                                       @RequestParam("productImage") String productImage,
                                       @RequestParam("remark") String remark,
                                       @RequestParam("productId") Integer productId) {
        return productService.updateProduct(productName,productPrice,productImage,remark,productId);
    }

    @RequestMapping(value = "/findProductById", method = RequestMethod.GET)
    public BaseData<Product> findProductById(@RequestParam("id") Integer id) {
        return productService.findProductById(id);
    }

}
