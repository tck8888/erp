package com.tck.service.impl;

import com.tck.base.BaseData;
import com.tck.base.BaseDataUtils;
import com.tck.base.StatusCode;
import com.tck.base.StatusType;
import com.tck.entity.Product;
import com.tck.entity.ProductInWarehouseCount;
import com.tck.mapper.ProductInWarehouseCountMapper;
import com.tck.mapper.ProductMapper;
import com.tck.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tck on 2017/7/12.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductInWarehouseCountMapper productInWarehouseCountMapper;

    @Override
    public BaseData<String> addProduct(String productName, Double productPrice, String productImage, String remark, Integer userId) {
        try {
            Boolean isSucess = productMapper.addProduct(productName, productPrice, productImage, remark, userId);
            if (isSucess) {
                return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.ADD_SUCCESS.getValue(), StatusType.ADD_SUCCESS.getValue());
            } else {
                return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.WEB_ERROR.getValue(), StatusType.ADD_ERROR.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.WEB_ERROR.getValue(), StatusType.ADD_ERROR.getValue());
        }
    }

    /**
     * 更新操作
     *
     * @param productName
     * @param productPrice
     * @param productImage
     * @param remark
     * @param productId
     * @return
     */
    @Override
    public BaseData<String> updateProduct(String productName, Double productPrice, String productImage, String remark, Integer productId) {
        try {
            Integer isSucess = productMapper.updateProduct(productName, productPrice, productImage, remark, productId);
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

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    @Override
    public BaseData<Product> findProductById(Integer id) {

        Product product = null;
        try {
            product = productMapper.findProductById(id);
            return BaseDataUtils.getInstance().<Product>getBaseData(StatusCode.SUCCESS_CODE, StatusType.SELECT_SUCCESS.getValue(), product);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().<Product>getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.SELECT_ERROR.getValue(), product);
        }
    }

    @Override
    public BaseData<List<Product>> findProductByUserId(int userId) {
        List<Product> productByUserId = null;
        try {
            productByUserId = productMapper.findProductByUserId(userId);
            return getBaseData(StatusCode.SUCCESS_CODE, StatusType.SELECT_SUCCESS.getValue(), productByUserId);
        } catch (Exception e) {
            e.printStackTrace();
            return getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.SELECT_ERROR.getValue(), productByUserId);
        }
    }

    /**
     * 查询有库存的商品
     *
     * @param userId
     * @param warehouseId
     * @return
     */
    @Override
    public BaseData<List<Product>> findAllByStock(int userId, int warehouseId) {
        List<Product> productByUserId = null;
        try {
            List<ProductInWarehouseCount> productWithStock = productInWarehouseCountMapper.getProductWithStock(warehouseId);
            //仓库没有库存数据
            if (productWithStock == null || productWithStock.isEmpty()) {
                return getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.SELECT_ERROR.getValue(), productByUserId);
            }
            productByUserId = productMapper.findProductByUserId(userId);
            //查询所有的商品库数据
            if (productByUserId == null || productByUserId.isEmpty()) {
                return getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.SELECT_ERROR.getValue(), productByUserId);
            }
            List<Product> mProductList = new ArrayList<>();
            for (int i = 0; i < productByUserId.size(); i++) {
                for (int j = 0; j < productWithStock.size(); j++) {
                    if (productByUserId.get(i).getId() == productWithStock.get(j).getProductId()) {
                        productByUserId.get(i).setStock(productWithStock.get(j).getCount());
                        mProductList.add(productByUserId.get(i));
                    }
                }
            }
            return getBaseData(StatusCode.SUCCESS_CODE, StatusType.SELECT_SUCCESS.getValue(), mProductList);
        } catch (Exception e) {
            e.printStackTrace();
            return getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.SELECT_ERROR.getValue(), productByUserId);
        }
    }

    private BaseData<List<Product>> getBaseData(int status, String message, List<Product> data) {
        BaseData<List<Product>> stringBaseData = new BaseData<List<Product>>();
        stringBaseData.setStatus(status);
        stringBaseData.setMessgae(message);
        stringBaseData.setData(data);
        return stringBaseData;
    }

}
