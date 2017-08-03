package com.tck.service.impl;

import com.tck.base.BaseData;
import com.tck.base.BaseDataUtils;
import com.tck.base.StatusCode;
import com.tck.base.StatusType;
import com.tck.entity.Product;
import com.tck.mapper.ProductMapper;
import com.tck.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tck on 2017/7/12.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

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
            return  BaseDataUtils.getInstance().<Product>getBaseData(StatusCode.SUCCESS_CODE, StatusType.SELECT_SUCCESS.getValue(), product);
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

    private BaseData<List<Product>> getBaseData(int status, String message, List<Product> data) {
        BaseData<List<Product>> stringBaseData = new BaseData<List<Product>>();
        stringBaseData.setStatus(status);
        stringBaseData.setMessgae(message);
        stringBaseData.setData(data);
        return stringBaseData;
    }

}
