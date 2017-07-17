package com.tck.service.impl;

import com.tck.base.BaseData;
import com.tck.entity.Warehouse;
import com.tck.mapper.WarehouseMapper;
import com.tck.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tck on 2017/7/17.
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public BaseData<String> addWarehouse(String productName, String remark) {

        Boolean isSuccess = warehouseMapper.addWarehouse(productName, remark);
        return null;
    }

    @Override
    public BaseData<Warehouse> findWarehouseByWarehouseId(Integer warehouseId) {
        Warehouse warehouse = warehouseMapper.findWarehouseByWarehouseId(warehouseId);
        return null;
    }
}
