package com.changgou.order.feign;

import com.changgou.order.pojo.Order;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "order")
@RequestMapping("/order")
public interface OrderFeign {

    /**
     * 查询我的订单信息
     * @param username
     * @return
     */
    @GetMapping("/myOrder")
    public Result<List<Order>> myOrder(@RequestParam String username);

    /**
     * 查询待付款列表
     * @param username
     * @return
     */
    @GetMapping("/notpay")
    public Result<List<Order>> notpay(@RequestParam String username);

    /**
     * 取消订单
     * @param orderId
     */
    @RequestMapping("/offOrder")
    public Result offOrder(@RequestParam String orderId);


    /**
     * 我的订单待发货,待收货订单列表
     * @param username
     * @return
     */
    @GetMapping("/deliveryTheGoods")
    public Result<List<Order>> deliveryTheGoods(@RequestParam String username);

    /**
     * 确认收货
     * @return
     */
    @RequestMapping("/confirm")
    public Result confirm(@RequestParam String orderId);

}
