package com.changgou.order.dao;
import com.changgou.order.pojo.Record;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:admin
 * @Description:Record的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface RecordMapper extends Mapper<Record> {
    @Update(value = "update tb_record set is_remind='1' where is_overtime='1' and is_delivery='0'")
    void updateIsRemind();
}
