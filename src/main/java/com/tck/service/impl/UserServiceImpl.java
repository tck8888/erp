package com.tck.service.impl;

import com.tck.base.BaseData;
import com.tck.base.BaseDataUtils;
import com.tck.base.StatusCode;
import com.tck.base.StatusType;
import com.tck.entity.User;
import com.tck.mapper.UserMapper;
import com.tck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by admin on 2017/7/13.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public BaseData<User> login(String username, String password) {
        User user = null;
        try {
            user = userMapper.login(username, password);
            if (user == null) {
                return BaseDataUtils.getInstance().<User>getBaseData(StatusCode.SUCCESS_CODE, StatusType.LOGIN_ERROR.getValue(), user);
            } else {
                return BaseDataUtils.getInstance().<User>getBaseData(StatusCode.SUCCESS_CODE, StatusType.LOGIN_SUCCESS.getValue(), user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().<User>getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.WEB_ERROR.getValue(), user);
        }
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public BaseData<String> register(String username, String password) {
        try {
            User user = userMapper.findUserByUserName(username);
            if (user == null) {
                if (userMapper.regisetr(username, password)) {
                    return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.REGISTER_SUCCESS.getValue(), StatusType.REGISTER_SUCCESS.getValue());
                } else {
                    return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.REGISTER_ERROR.getValue(), StatusType.REGISTER_ERROR.getValue());
                }
            } else {
                return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, StatusType.REGISTER_ERROR.getValue(), StatusType.PHONE_ALREADY_EXISTENCE.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.REGISTER_ERROR.getValue(), StatusType.REGISTER_ERROR.getValue());
        }
    }

    @Override
    public BaseData<String> update(int userId, String name, String type) {
        try {
            if (type.equals("nickname")) {
                if (userMapper.updateUserNickName(userId, name) > 0) {
                    return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, "修改成功", "修改成功");
                } else {
                    return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, "修改失败", "修改失败");
                }
            }
            if (type.equals("email")) {
                if (userMapper.updateUserEmail(userId, name) > 0) {
                    return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, "修改成功", "修改成功");
                } else {
                    return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, "修改失败", "修改失败");
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.WEB_ERROR_CODE, "修改失败", "修改失败");
        }
    }

    @Override
    public BaseData<User> findUserById(Integer userId) {
        User user = null;
        try {
            user = userMapper.findUserById(userId);
            if (user == null) {
                return BaseDataUtils.getInstance().<User>getBaseData(StatusCode.SUCCESS_CODE, StatusType.LOGIN_ERROR.getValue(), user);
            } else {
                return BaseDataUtils.getInstance().<User>getBaseData(StatusCode.SUCCESS_CODE, StatusType.LOGIN_SUCCESS.getValue(), user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().<User>getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.WEB_ERROR.getValue(), user);
        }
    }

}
