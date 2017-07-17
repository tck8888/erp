package com.tck.base;

import java.util.List;

/**
 * Created by admin on 2017/7/17.
 */
public class BaseDataUtils {

    private static volatile BaseDataUtils singleton;

    private BaseDataUtils() {
    }

    public static BaseDataUtils getInstance() {
        if (singleton == null) {
            synchronized (BaseDataUtils.class) {
                if (singleton == null) {
                    singleton = new BaseDataUtils();
                }
            }
        }
        return singleton;
    }

    public <T> BaseData<T> getBaseData(int status, String message, T data) {
        BaseData<T> stringBaseData = new BaseData<T>();
        stringBaseData.setStatus(status);
        stringBaseData.setMessgae(message);
        stringBaseData.setData(data);
        return stringBaseData;
    }


}
