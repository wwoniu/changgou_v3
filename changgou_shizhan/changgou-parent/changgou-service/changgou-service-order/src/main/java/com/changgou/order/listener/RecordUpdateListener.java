package com.changgou.order.listener;

import com.alibaba.fastjson.JSON;
import com.changgou.order.pojo.Record;
import com.changgou.order.service.RecordService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues ="queue.message")
public class RecordUpdateListener {

    @Autowired
    private RecordService recordService;

    @RabbitHandler
    public void recordUpdate(String msg){
        System.out.println("监听到了");
        Record record = JSON.parseObject(msg, Record.class);
        record.setIsRemind("1");
        record.setIsOvertime("1");

        Record byId = recordService.findById(record.getId());
        //先判断有没有收货
        if (byId.getIsDelivery().equals("1")) {
            //已经收货,不用修改
            return;
        }
        //修改提醒发货记录表数据
        recordService.update(record);

    }
}
