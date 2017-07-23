package com.tck.service.impl;

import com.tck.base.BaseData;
import com.tck.base.BaseDataUtils;
import com.tck.base.StatusCode;
import com.tck.base.StatusType;
import com.tck.entity.Product;
import com.tck.entity.ProductInWarehouseCount;
import com.tck.entity.PurchaseOrder;
import com.tck.mapper.ProductInWarehouseCountMapper;
import com.tck.mapper.PurchaseOrderMapper;
import com.tck.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tck on 2017/7/13.
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private ProductInWarehouseCountMapper productInWarehouseCountMapper;

    @Override
    public BaseData<String> addOrder(Integer productId, Integer userId, Integer warehouseId, Integer count, String remark) {

        try {
            /**
             * 先判断tb_warehouse_product_count里面有没有当前productId和warehouseId
             * 如果没有的话，就是新增一条记录
             * 否则，在原有的记录上修改一下数量
             */
            ProductInWarehouseCount productInWarehouseCount = productInWarehouseCountMapper.getProductInWarehouseCount(productId, warehouseId);
            if (productInWarehouseCount == null) {
                Boolean isSave = productInWarehouseCountMapper.addProductInWarehouseCount(productId, warehouseId, count);
                Boolean isSuccess = purchaseOrderMapper.addOrder(productId, userId, warehouseId, count, remark);
                if (isSuccess && isSave) {
                    return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.ADD_SUCCESS.getValue(), StatusType.ADD_SUCCESS.getValue());
                } else {
                    return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.ADD_ERROR.getValue(), StatusType.ADD_ERROR.getValue());
                }
            } else {
                Integer integer = productInWarehouseCountMapper.updateProductInWarehouseCount(productId, warehouseId, count);
                Boolean isSuccess = purchaseOrderMapper.addOrder(productId, userId, warehouseId, count, remark);
                if (integer > 0 && isSuccess) {
                    return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.ADD_SUCCESS.getValue(), StatusType.ADD_SUCCESS.getValue());
                }else {
                    return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.ADD_ERROR.getValue(), StatusType.ADD_ERROR.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.WEB_ERROR.getValue(), StatusType.WEB_ERROR.getValue());
        }
    }

    @Override
    public BaseData<List<PurchaseOrder>> findOrderByUserId(Integer userId) {
        List<PurchaseOrder> purchaseOrderList = null;
        try {
            purchaseOrderList = purchaseOrderMapper.findOrderByUserId(userId);
            return getBaseData(StatusCode.SUCCESS_CODE, StatusType.SELECT_SUCCESS.getValue(), purchaseOrderList);
        } catch (Exception e) {
            e.printStackTrace();
            return getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.WEB_ERROR.getValue(), purchaseOrderList);
        }
    }

    private BaseData<List<PurchaseOrder>> getBaseData(int status, String message, List<PurchaseOrder> data) {
        BaseData<List<PurchaseOrder>> stringBaseData = new BaseData<List<PurchaseOrder>>();
        stringBaseData.setStatus(status);
        stringBaseData.setMessgae(message);
        stringBaseData.setData(data);
        return stringBaseData;
    }

    @Override
    public BaseData<PurchaseOrder> findOrderByproductId(Integer productId) {

        PurchaseOrder purchaseOrder = null;
        try {
            purchaseOrder = purchaseOrderMapper.findOrderByproductId(productId);
            return BaseDataUtils.getInstance().<PurchaseOrder>getBaseData(StatusCode.SUCCESS_CODE, StatusType.SELECT_SUCCESS.getValue(), purchaseOrder);
        } catch (Exception e) {
            e.printStackTrace();

            return BaseDataUtils.getInstance().<PurchaseOrder>getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.WEB_ERROR.getValue(), purchaseOrder);
        }

    }

    @Override
    public BaseData<String> updateOrderByProductId(Integer productId, Integer count, String remark) {

        try {
            Integer isSucess = purchaseOrderMapper.updateOrderByProductId(productId, count, remark);
            if (isSucess > 0) {
                return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.UPDATE_SUCCESS.getValue(), StatusType.UPDATE_SUCCESS.getValue());
            } else {
                return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.UPDATE_ERROR.getValue(), StatusType.UPDATE_ERROR.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.WEB_ERROR.getValue(), StatusType.UPDATE_ERROR.getValue());
        }
    }


}
