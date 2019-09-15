package com.changgou.ordercentre.controller;

import com.changgou.order.pojo.Order;
import com.changgou.ordercentre.service.OrderCentreService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderCentre")
@CrossOrigin
public class OrderCentreController {

    @Autowired
    private OrderCentreService orderCentreService;

    /**
     * 查询我的订单
     * @param username
     * @return
     */
    @GetMapping("/myOrder")
    public Result myOrder(@RequestParam String username){
        List<Order> orders = orderCentreService.myOrder(username);
        return new Result(true, StatusCode.OK,"查询我的订单成功",orders);
    }


    /**
     * 我的订单待付款列表
     * @param username
     * @return
     */
    @GetMapping("/notpay")
    public Result notpay(@RequestParam String username){
        List<Order> orders = orderCentreService.notpay(username);
        return new Result(true, StatusCode.OK,"查询待付款列表成功",orders);
    }


    /**
     * 取消订单功能
     * @param orderId 订单号
     * @return
     */
    @RequestMapping("/offOrder")
    public Result offOrder(@RequestParam String orderId){
        orderCentreService.offOrder(orderId);
        return new Result(true, StatusCode.OK,"取消订单成功");
    }

    /**
     * 我的订单待发货,待收货订单列表
     * @param username
     * @return
     */
    @GetMapping("/deliveryTheGoods")
    public Result deliveryTheGoods(@RequestParam String username){
        List<Order> orders = orderCentreService.deliveryTheGoods(username);
        return new Result(true, StatusCode.OK,"查询待发货,待收货订单列表成功",orders);
    }


    /**
     * 确认收货
     * @return
     */
    @RequestMapping("/confirm")
    public Result confirm(@RequestParam String orderId){
        int i = orderCentreService.confirm(orderId);
        return new Result(true, StatusCode.OK,"确认收货成功");
    }


    /**
     * 提醒发货
     * @return
     */
    @RequestMapping("/remind")
    public Result remind(@RequestParam String orderId){
        int i = orderCentreService.remind(orderId);
        return new Result(true, StatusCode.OK,"提醒发货成功");
    }
}
