package com.tck.service;

import com.tck.base.BaseData;
import com.tck.entity.Warehouse;

/**
 * Created by tck on 2017/7/17.
 */
public interface WarehouseService {
    BaseData<String> addWarehouse(String productName, String remark);

    BaseData<Warehouse> findWarehouseByWarehouseId(Integer warehouseId);
}
