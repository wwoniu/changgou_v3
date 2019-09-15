package com.changgou.order.feign;

import com.changgou.order.pojo.Record;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "order")
@RequestMapping("/record")
public interface RecordFeign {
    /**
     * 添加提醒发货记录表
     * @param record
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Record record);


    /**
     * 提醒发货
     * @param orderId
     * @return
     */
    @RequestMapping("/remind")
    public Result remind(@RequestParam String orderId);
}
