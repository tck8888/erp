package com.tck.service;

import com.tck.base.BaseData;

/**
 * Created by tck on 2017/7/13.
 */
public interface PurchaseOrderService {
    BaseData<String> addOrder(Integer productId, Integer userId, Integer count,String remark);
}
