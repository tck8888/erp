package com.tck.service;

import com.tck.base.BaseData;
import com.tck.entity.ConsumeRecord;

import java.util.List;

/**
 * Created by admin on 2017/8/15.
 */
public interface ConsumeRecordService {
    BaseData<List<ConsumeRecord>> getConsumeRecordList(Integer accountId);
}
