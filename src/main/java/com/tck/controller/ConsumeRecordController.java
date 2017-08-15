package com.tck.controller;

import com.tck.base.BaseData;
import com.tck.entity.ConsumeRecord;
import com.tck.service.ConsumeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by admin on 2017/8/15.
 */
@RestController
public class ConsumeRecordController {

    @Autowired
    private ConsumeRecordService consumeRecordService;

    @RequestMapping(value = "/getConsumeRecordList",method = RequestMethod.GET)
    public BaseData<List<ConsumeRecord>> getConsumeRecordList(@RequestParam("accountId") Integer accountId) {

        return consumeRecordService.getConsumeRecordList(accountId);
    }

}
