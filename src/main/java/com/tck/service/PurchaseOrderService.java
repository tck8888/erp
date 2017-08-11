package com.tck.service;

import com.tck.base.BaseData;
import com.tck.entity.PurchaseOrder;

import java.util.List;

/**
 * Created by tck on 2017/7/13.
 */
public interface PurchaseOrderService {

    BaseData<List<PurchaseOrder>> findOrderByUserId(Integer userId);

    BaseData<PurchaseOrder> findOrderByproductId(Integer productId);

    BaseData<String> updateOrderByProductId(Integer id, Integer productId, String remark);

    BaseData<String> addOrder(Integer userId, Integer warehouseId, String warehouseName,
                              Integer accountId, String accountName, Integer totalCount,
                              Double totalPrice, String productId, String productCount,
                              String date, String remark);
}
