package com.changgou.order.timer;

import com.changgou.order.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RecordUpdateTimer {

    @Autowired
    private RecordService recordService;

    @Scheduled(cron = "0 0 0 1/1 * ?")//"0 0 0 1/1 * ? "每天0点更新提醒发货记录表
    public void update(){
        System.out.println("更新了");
        //将所有已经收款未发货的提醒发货记录表更新为可提醒发货状态
        recordService.updateIsRemind();
    }
}
