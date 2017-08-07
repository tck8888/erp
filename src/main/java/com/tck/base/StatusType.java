package com.tck.base;

/**
 * Created by tck on 2017/7/12.
 */
public enum StatusType {

    ADD_SUCCESS("新增成功"),
    ADD_ERROR("新增失败"),
    UPDATE_ERROR("更新失败"),
    UPDATE_SUCCESS("更新成功"),
    SELECT_SUCCESS("查询成功"),
    SELECT_WAREHOUSE_NO_DATA("暂无仓库数据"),
    SELECT_ERROR("查询失败"),
    LOGIN_ERROR("手机或者密码错误"),
    LOGIN_SUCCESS("登陆成功"),
    REGISTER_ERROR("注册失败"),
    PHONE_ALREADY_EXISTENCE("手机号已经存在"),
    REGISTER_SUCCESS("注册成功"),
    ACCOUNT_NO_DATA("暂无当前账户"),
    WEB_ERROR("服务器异常");


    String value;

    StatusType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
