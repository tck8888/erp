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
    SELECT_ERROR("查询失败"),
    WEB_ERROR("服务器异常");


    String value;

    StatusType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
