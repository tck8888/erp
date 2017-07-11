package com.tck.controller;

import com.tck.entity.Product;
import com.tck.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by admin on 2017/7/11.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Product> findAll(@RequestParam("userId") int userId) {
        return productMapper.findProductByUserId(userId);
    }
}
