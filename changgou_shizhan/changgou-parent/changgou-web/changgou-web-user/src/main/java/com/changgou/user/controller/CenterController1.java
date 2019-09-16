package com.changgou.user.controller;

import com.changgou.order.feign.OrderFeign;
import com.changgou.order.pojo.Order;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/center")
public class CenterController1 {
    @Autowired
    private OrderFeign orderFeign;

    @RequestMapping("/getmyOrder")
    public Result<List<Order>> getMyOrder(String username){
        Result<List<Order>> listResult = orderFeign.myOrder(username);
        return listResult;
    }
    @RequestMapping("/notpay")
    public Result<List<Order>> getNotpay(String username){
        Result<List<Order>> listResult = orderFeign.notpay(username);
        return listResult;
    }
    @RequestMapping("/deliveryTheGoods")
    public Result<List<Order>> getDeliveryTheGoods(String username){
        Result<List<Order>> listResult = orderFeign.deliveryTheGoods(username);
        return listResult;
    }
    @RequestMapping("/offOrder")
    public Result offOrder(String orderId){
        Result result = orderFeign.offOrder(orderId);
        return result;
    }
    @RequestMapping("/confirm")
    public Result confirm(String orderId){
        Result result = orderFeign.confirm(orderId);
        return result;
    }
}
