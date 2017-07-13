package com.tck.service.impl;

import com.tck.base.BaseData;
import com.tck.base.BaseService;
import com.tck.base.StatusCode;
import com.tck.base.StatusType;
import com.tck.entity.User;
import com.tck.mapper.UserMapper;
import com.tck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/7/13.
 */
@Service
public class UserServiceImpl implements UserService, BaseService<User> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseData<User> login(String username, String password) {

        User user = null;
        try {
            user = userMapper.login(username, password);
            return getBaseData(StatusCode.SUCCESS_CODE, StatusType.LOGIN_SUCCESS.getValue(), user);
        } catch (Exception e) {
            e.printStackTrace();
            return getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.LOGIN_ERROR.getValue(), user);
        }
    }

    @Override
    public BaseData<String> register(String username, String password) {
        try {
            User user = userMapper.findUserByUserName(username);
            if (user == null) {
                if (userMapper.regisetr(username, password)) {
                    return getBaseData(StatusCode.SUCCESS_CODE, StatusType.REGISTER_SUCCESS.getValue(), StatusType.REGISTER_SUCCESS.getValue());
                } else {
                    return getBaseData(StatusCode.SUCCESS_CODE, StatusType.REGISTER_ERROR.getValue(), StatusType.REGISTER_ERROR.getValue());
                }
            } else {
                return getBaseData(StatusCode.SUCCESS_CODE, StatusType.REGISTER_ERROR.getValue(), StatusType.PHONE_ALREADY_EXISTENCE.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.REGISTER_ERROR.getValue(), StatusType.REGISTER_ERROR.getValue());
        }
    }


    @Override
    public BaseData<String> getBaseData(int status, String message, String data) {
        BaseData<String> stringBaseData = new BaseData<String>();
        stringBaseData.setStatus(status);
        stringBaseData.setMessgae(message);
        stringBaseData.setData(data);
        return stringBaseData;
    }

    @Override
    public BaseData<User> getBaseData(int status, String message, User data) {
        BaseData<User> stringBaseData = new BaseData<User>();
        stringBaseData.setStatus(status);
        stringBaseData.setMessgae(message);
        stringBaseData.setData(data);
        return stringBaseData;
    }

    @Override
    public BaseData<List<User>> getBaseData(int status, String message, List<User> data) {
        return null;
    }
}
