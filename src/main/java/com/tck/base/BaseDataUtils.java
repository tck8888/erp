package com.tck.base;

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

    public BaseData<String> getBaseData(int status, String message, String data) {
        BaseData<String> stringBaseData = new BaseData<String>();
        stringBaseData.setStatus(status);
        stringBaseData.setMessgae(message);
        stringBaseData.setData(data);
        return stringBaseData;
    }
}
