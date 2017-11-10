package com.tck.controller;

import com.tck.base.BaseData;
import com.tck.entity.PurchaseOrder;
import com.tck.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tck on 2017/7/13.
 */
@RestController
@RequestMapping("/saleOrder")
public class SaleOrderController {

    @Autowired
    private SaleOrderService saleOrderService;

    @RequestMapping("/addOrder")
    public BaseData<String> addOrder(@RequestParam("userId") Integer userId,
                                     @RequestParam("warehouseId") Integer warehouseId,
                                     @RequestParam("warehouseName") String warehouseName,
                                     @RequestParam("accountId") Integer accountId,
                                     @RequestParam("accountName") String accountName,
                                     @RequestParam("totalCount") Integer totalCount,
                                     @RequestParam("totalPrice") Double totalPrice,
                                     @RequestParam("productId") String productId,
                                     @RequestParam("productCount") String productCount,
                                     @RequestParam("date") String date,
                                     @RequestParam("remark") String remark) {

        return saleOrderService.addOrder(userId, warehouseId, warehouseName,
                accountId, accountName, totalCount,
                totalPrice, productId, productCount,
                date, remark);
    }

    @RequestMapping("/findOrderByUserId")
    public BaseData<List<PurchaseOrder>> findOrderByUserId(@RequestParam("userId") Integer userId) {

        return saleOrderService.findOrderByUserId(userId);
    }

    @RequestMapping("/findOrderByProductId")
    public BaseData<PurchaseOrder> findOrderByproductId(@RequestParam("productId") Integer productId) {

        return saleOrderService.findOrderByproductId(productId);
    }

    @RequestMapping("/updateOrderByProductId")
    public BaseData<String> updateOrderByProductId(@RequestParam("productId") Integer productId,
                                                   @RequestParam("count") Integer count,
                                                   @RequestParam("remark") String remark) {

        return saleOrderService.updateOrderByProductId(productId, count, remark);
    }
}
