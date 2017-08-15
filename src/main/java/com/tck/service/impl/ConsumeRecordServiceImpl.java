package com.tck.service.impl;

import com.tck.base.BaseData;
import com.tck.base.BaseDataUtils;
import com.tck.base.StatusCode;
import com.tck.base.StatusType;
import com.tck.entity.ConsumeRecord;
import com.tck.entity.Product;
import com.tck.mapper.ConsumeRecordMapper;
import com.tck.service.ConsumeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/8/15.
 */
@Service
public class ConsumeRecordServiceImpl implements ConsumeRecordService {
    @Autowired
    private ConsumeRecordMapper consumeRecordMapper;

    @Override
    public BaseData<List<ConsumeRecord>> getConsumeRecordList(Integer accountId) {
        List<ConsumeRecord> consumeRecordList = null;
        try {
            consumeRecordList = consumeRecordMapper.getConsumeRecordList(accountId);
            if (consumeRecordList != null) {
                return getBaseData(StatusCode.SUCCESS_CODE, StatusType.SELECT_SUCCESS.getValue(), consumeRecordList);
            } else {
                return getBaseData(StatusCode.SUCCESS_CODE, StatusType.SELECT_CONSUME_NO_DATA.getValue(),consumeRecordList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getBaseData(StatusCode.WEB_ERROR_CODE, StatusType.SELECT_ERROR.getValue(), consumeRecordList);
        }

    }

    private BaseData<List<ConsumeRecord>> getBaseData(int status, String message, List<ConsumeRecord> data) {
        BaseData<List<ConsumeRecord>> stringBaseData = new BaseData<List<ConsumeRecord>>();
        stringBaseData.setStatus(status);
        stringBaseData.setMessgae(message);
        stringBaseData.setData(data);
        return stringBaseData;
    }
}
