package com.tck.service.impl;

import com.tck.base.BaseData;
import com.tck.base.BaseDataUtils;
import com.tck.base.StatusCode;
import com.tck.base.StatusType;
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

        try {
            Boolean isSuccess = warehouseMapper.addWarehouse(productName, remark);
            if (isSuccess) {
                return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.ADD_SUCCESS.getValue(), StatusType.ADD_SUCCESS.getValue());
            } else {
                return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.WEB_ERROR.getValue(), StatusType.ADD_ERROR.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.WEB_ERROR.getValue(), StatusType.ADD_ERROR.getValue());
        }
    }

    @Override
    public BaseData<Warehouse> findWarehouseByWarehouseId(Integer warehouseId) {
        Warehouse warehouse = null;
        try {
            warehouse = warehouseMapper.findWarehouseByWarehouseId(warehouseId);
            return BaseDataUtils.getInstance().<Warehouse>getBaseData(StatusCode.SUCCESS_CODE, StatusType.SELECT_SUCCESS.getValue(), warehouse);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().<Warehouse>getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.SELECT_ERROR.getValue(), warehouse);
        }
    }
}
