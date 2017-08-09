package com.tck.controller;

import com.tck.base.BaseData;
import com.tck.entity.PurchaseOrder;
import com.tck.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tck on 2017/7/13.
 */
@RestController
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    /*
    *    private int userId;
    private int accountId;
    private int warehouseId;
    private int totalCount;
    private double totalprice;
    private String productId;
    private String productCount;
    private String date;
    private String remark;*/
    @RequestMapping("/addOrder")
    public BaseData<String> addOrder(@RequestParam("productId") Integer productId,
                                     @RequestParam("userId") Integer userId,
                                     @RequestParam("warehouseId") Integer warehouseId,
                                     @RequestParam("count") Integer count,
                                     @RequestParam("totalPrice") Double totalPrice,
                                     @RequestParam("remark") String remark) {

        return purchaseOrderService.addOrder(productId, userId, warehouseId,count,totalPrice, remark);
    }

    @RequestMapping("/findOrderByUserId")
    public BaseData<List<PurchaseOrder>> findOrderByUserId(@RequestParam("userId") Integer userId) {

        return purchaseOrderService.findOrderByUserId(userId);
    }

    @RequestMapping("/findOrderByProductId")
    public BaseData<PurchaseOrder> findOrderByproductId(@RequestParam("productId") Integer productId) {

        return purchaseOrderService.findOrderByproductId(productId);
    }

    @RequestMapping("/updateOrderByProductId")
    public BaseData<String> updateOrderByProductId(@RequestParam("productId") Integer productId,
                                                   @RequestParam("count") Integer count,
                                                   @RequestParam("remark") String remark) {

        return purchaseOrderService.updateOrderByProductId(productId, count, remark);
    }
}
