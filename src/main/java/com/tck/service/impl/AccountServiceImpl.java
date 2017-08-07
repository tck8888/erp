package com.tck.service.impl;

import com.tck.base.BaseData;
import com.tck.base.BaseDataUtils;
import com.tck.base.StatusCode;
import com.tck.base.StatusType;
import com.tck.entity.AccountBean;
import com.tck.entity.Product;
import com.tck.entity.Warehouse;
import com.tck.mapper.AccountMapper;
import com.tck.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tck on 2017/8/6.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BaseData<String> addAccount(String accountName, String remark, Integer userId) {
        try {
            Boolean isSucess = accountMapper.addAccount(accountName, remark, userId);
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

    @Override
    public BaseData<List<AccountBean>> getAccountList(Integer userId) {
        List<AccountBean> accountBeanList = null;
        try {
            accountBeanList = accountMapper.getAccountList(userId);
            if (accountBeanList == null) {
                return getBaseData(StatusCode.SUCCESS_CODE, StatusType.SELECT_WAREHOUSE_NO_DATA.getValue(), accountBeanList);
            } else {
                return getBaseData(StatusCode.SUCCESS_CODE, StatusType.SELECT_SUCCESS.getValue(), accountBeanList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.SELECT_ERROR.getValue(), accountBeanList);
        }
    }

    @Override
    public BaseData<AccountBean> findAccountById(Integer accountId) {
        AccountBean accountBean = null;
        try {
            accountBean = accountMapper.findAccountById(accountId);
            if (accountBean == null) {
                return BaseDataUtils.getInstance().<AccountBean>getBaseData(StatusCode.SUCCESS_CODE, StatusType.ACCOUNT_NO_DATA.getValue(), accountBean);
            } else {
                return BaseDataUtils.getInstance().<AccountBean>getBaseData(StatusCode.SUCCESS_CODE, StatusType.SELECT_SUCCESS.getValue(), accountBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().<AccountBean>getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.SELECT_ERROR.getValue(), accountBean);
        }
    }

    private BaseData<List<AccountBean>> getBaseData(int status, String message, List<AccountBean> data) {
        BaseData<List<AccountBean>> stringBaseData = new BaseData<List<AccountBean>>();
        stringBaseData.setStatus(status);
        stringBaseData.setMessgae(message);
        stringBaseData.setData(data);
        return stringBaseData;
    }

}
