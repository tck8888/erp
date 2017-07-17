package com.tck.controller;

import com.tck.base.BaseData;
import com.tck.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tck on 2017/7/13.
 */
@RestController
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @RequestMapping("/addOrder")
    public BaseData<String> addOrder(@RequestParam("productId") Integer productId,
                                     @RequestParam("userId") Integer userId,
                                     @RequestParam("count") Integer count,
                                     @RequestParam("remark") String remark) {

        return purchaseOrderService.addOrder(productId, userId, count, remark);
    }
}
