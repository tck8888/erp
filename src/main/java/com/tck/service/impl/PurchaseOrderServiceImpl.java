package com.tck.service.impl;

import com.tck.base.BaseData;
import com.tck.base.BaseDataUtils;
import com.tck.base.StatusCode;
import com.tck.base.StatusType;
import com.tck.entity.AccountBean;
import com.tck.entity.Product;
import com.tck.entity.ProductInWarehouseCount;
import com.tck.entity.PurchaseOrder;
import com.tck.mapper.AccountMapper;
import com.tck.mapper.ProductInWarehouseCountMapper;
import com.tck.mapper.PurchaseOrderMapper;
import com.tck.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private AccountMapper accountMapper;


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


    @Transactional
    @Override
    public BaseData<String> addOrder(Integer userId, Integer warehouseId, String warehouseName,
                                     Integer accountId, String accountName, Integer totalCount,
                                     Double totalPrice, String productId, String productCount,
                                     String date, String remark) {
        /**
         * 先判断tb_warehouse_product_count里面有没有当前productId和warehouseId
         * 如果没有的话，就是新增一条记录
         * 否则，在原有的记录上修改一下数量
         */
        boolean isSuccess = false;
        if (productId.indexOf(",") == -1) {
            isSuccess = saveProductToWarehouse(Integer.parseInt(productId), Integer.parseInt(productCount), warehouseId);
        } else {
            String[] productIds = productId.split(",");
            String[] productCounts = productCount.split(",");
            for (int i = 0; i < productIds.length; i++) {
                int id = Integer.parseInt(productIds[i]);
                int count = Integer.parseInt(productCounts[i]);
                isSuccess = saveProductToWarehouse(id, count, warehouseId);
                if (!isSuccess) {
                    break;
                }
            }
        }

        if (isSuccess) {
            /**
             * 采购修改账户余额
             */
            Boolean isAccountBalanceSuccuss = updateAccountMoney(accountId, -totalPrice);
            if (isAccountBalanceSuccuss) {
                Boolean isPurchaseOrderAddSuccess = isAddPurchaseSuccess(userId, warehouseId, warehouseName,
                        accountId, accountName, totalCount,
                        totalPrice, productId, productCount,
                        date, remark);
                if (isPurchaseOrderAddSuccess) {
                    return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.ADD_SUCCESS.getValue(), StatusType.ADD_SUCCESS.getValue());
                } else {
                    return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.WEB_ERROR_CODE, "新增采购单失败", StatusType.ADD_ERROR.getValue());
                }
            } else {
                return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.WEB_ERROR_CODE, "修改账户余额失败", StatusType.ADD_ERROR.getValue());
            }
        } else {
            return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.WEB_ERROR_CODE, "保存商品到库存失败", StatusType.ADD_ERROR.getValue());
        }
    }

    public boolean isAddPurchaseSuccess(Integer userId, Integer warehouseId, String warehouseName,
                                        Integer accountId, String accountName, Integer totalCount,
                                        Double totalPrice, String productId, String productCount,
                                        String date, String remark) {
        try {
            return purchaseOrderMapper.addOrder(userId, warehouseId, warehouseName,
                    accountId, accountName, totalCount,
                    totalPrice, productId, productCount,
                    date, remark);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新账户余额
     *
     * @param acccountId
     * @param price
     * @return
     */
    public boolean updateAccountMoney(int acccountId, double price) {

        AccountBean account = null;
        try {
            account = accountMapper.findAccountById(acccountId);
            if (account == null) {
                return false;
            } else {
                return updateAccountMoney(account, price);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新账户余额
     *
     * @param account
     * @param price
     * @return
     */
    public boolean updateAccountMoney(AccountBean account, double price) {
        try {
            return accountMapper.updateAccountBalance(account.getId(), price);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存商品到仓库
     *
     * @param productId
     * @param productCount
     * @param warehouseId
     * @return
     */
    public boolean saveProductToWarehouse(int productId, int productCount, int warehouseId) {
        try {
            ProductInWarehouseCount productInWarehouseCount = productInWarehouseCountMapper.getProductInWarehouseCount(productId, warehouseId);
            if (productInWarehouseCount == null) {
                //没有查到,保存
                return productInWarehouseCountMapper.addProductInWarehouseCount(productCount, warehouseId, productCount);
            } else {
                //查到当前数据库有记录，则更新数据
                return productInWarehouseCountMapper.updateProductInWarehouseCount(productId, warehouseId, productCount) > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
