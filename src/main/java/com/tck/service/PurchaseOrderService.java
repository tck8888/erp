package com.tck.service;

import com.tck.base.BaseData;
import com.tck.entity.PurchaseOrder;

import java.util.List;

/**
 * Created by tck on 2017/7/13.
 */
public interface PurchaseOrderService {
    BaseData<String> addOrder(Integer productId, Integer userId, Integer warehouseId,Integer count,String remark);

    BaseData<List<PurchaseOrder>> findOrderByUserId(Integer userId);

    BaseData<PurchaseOrder> findOrderByproductId(Integer productId);

    BaseData<String> updateOrderByProductId(Integer id, Integer productId, String remark);
}
