package com.tck.service;

import com.tck.base.BaseData;
import com.tck.entity.Warehouse;

import java.util.List;

/**
 * Created by tck on 2017/7/17.
 */
public interface WarehouseService {
    BaseData<String> addWarehouse(String productName, String remark,Integer userId);

    BaseData<Warehouse> findWarehouseByWarehouseId(Integer warehouseId);

    BaseData<List<Warehouse>> getWarehouseList(Integer userId);
}
