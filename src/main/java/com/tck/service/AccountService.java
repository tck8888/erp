package com.tck.service;

import com.tck.base.BaseData;
import com.tck.entity.AccountBean;

import java.util.List;

/**
 * Created by tck on 2017/8/6.
 */
public interface AccountService {
    BaseData<String> addAccount(String accountName, String remark, Integer userId);

    BaseData<List<AccountBean>> getAccountList(Integer userId);
}
