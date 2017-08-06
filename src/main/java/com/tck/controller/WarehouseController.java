package com.tck.controller;

import com.tck.base.BaseData;
import com.tck.entity.Warehouse;
import com.tck.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tck on 2017/7/17.
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(value = "/addWarehouse")
    public BaseData<String> addWarehouse(@RequestParam("productName") String productName,
                                         @RequestParam("remark") String remark,
                                         @RequestParam("userId") Integer userId) {
        return warehouseService.addWarehouse(productName, remark,userId);
    }

    @RequestMapping(value = "/findWarehouseByWarehouseId")
    public BaseData<Warehouse> findWarehouseByWarehouseId(@RequestParam("warehouseId") Integer warehouseId) {
        return warehouseService.findWarehouseByWarehouseId(warehouseId);
    }

    @RequestMapping(value = "/getWarehouseList")
    public BaseData<List<Warehouse>> getWarehouseList(@RequestParam("userId") Integer userId){

        return warehouseService.getWarehouseList(userId);
    }

}
