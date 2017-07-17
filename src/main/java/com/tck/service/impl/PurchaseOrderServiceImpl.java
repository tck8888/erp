package com.tck.service.impl;

import com.tck.base.BaseData;
import com.tck.base.BaseDataUtils;
import com.tck.base.StatusCode;
import com.tck.base.StatusType;
import com.tck.mapper.PurchaseOrderMapper;
import com.tck.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tck on 2017/7/13.
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public BaseData<String> addOrder(Integer productId, Integer userId, Integer count, String remark) {

        try {
            Boolean isSuccess = purchaseOrderMapper.addOrder(productId, userId, count, remark);
            if (isSuccess) {
                return BaseDataUtils.getInstance().getBaseData(StatusCode.SUCCESS_CODE, StatusType.ADD_SUCCESS.getValue(), StatusType.ADD_SUCCESS.getValue());
            } else {
                return BaseDataUtils.getInstance().getBaseData(StatusCode.SUCCESS_CODE, StatusType.ADD_ERROR.getValue(), StatusType.ADD_ERROR.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.WEB_ERROR.getValue(), StatusType.WEB_ERROR.getValue());
        }
    }


}
