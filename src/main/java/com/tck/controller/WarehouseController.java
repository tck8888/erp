package com.tck.controller;

import com.tck.base.BaseData;
import com.tck.entity.Warehouse;
import com.tck.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tck on 2017/7/17.
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping("/addWarehouse")
    public BaseData<String> addWarehouse(@RequestParam("productName") String productName,
                                         @RequestParam("remark") String remark) {
        return warehouseService.addWarehouse(productName, remark);
    }

    @RequestMapping("/findWarehouseByWarehouseId")
    public BaseData<Warehouse> findWarehouseByWarehouseId(@RequestParam("warehouseId") Integer warehouseId) {
        return warehouseService.findWarehouseByWarehouseId(warehouseId);
    }
}
