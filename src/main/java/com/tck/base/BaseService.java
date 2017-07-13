package com.tck.base;

import java.util.List;

/**
 * Created by admin on 2017/7/13.
 */
public interface BaseService<T> {

    BaseData<String> getBaseData(int status, String message, String data);

    BaseData<T> getBaseData(int status, String message, T data);

    BaseData<List<T>> getBaseData(int status, String message, List<T> data);
}
