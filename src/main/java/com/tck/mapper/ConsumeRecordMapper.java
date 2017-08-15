package com.tck.mapper;


import com.tck.entity.ConsumeRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.util.List;

/**
 * Created by admin on 2017/8/15.
 */
@Mapper
public interface ConsumeRecordMapper {

    @Insert("insert into tb_consume_record(consumeType,consumeContent,consumePrice,consumeDate) " +
            "values(#{consumeType},#{consumeContent},#{consumePrice},#{consumeDate})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "consumeType", column = "consumeType"),
            @Result(property = "consumeContent", column = "consumeContent"),
            @Result(property = "consumePrice", column = "consumePrice"),
            @Result(property = "consumeDate", column = "consumeDate")
    })
    List<ConsumeRecord> getConsumeRecordList(Integer accountId);
}
