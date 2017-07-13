package com.tck.service;

import com.tck.base.BaseData;
import com.tck.entity.User;

/**
 * Created by admin on 2017/7/13.
 */
public interface UserService {
    BaseData<User> login(String username, String password);

    BaseData<String> register(String username, String password);

}
