package com.changgou.ordercentre.service.impl;

import com.changgou.order.feign.OrderFeign;
import com.changgou.order.feign.RecordFeign;
import com.changgou.order.pojo.Order;
import com.changgou.ordercentre.service.OrderCentreService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderCentreServiceImpl implements OrderCentreService {

    @Autowired
    private OrderFeign orderFeign;

    @Autowired
    private RecordFeign recordFeign;

    /**
     * 根据用户名获取我的订单信息
     * @param username
     * @return
     */
    @Override
    public List<Order> myOrder(String username) {
        Result<List<Order>> result = orderFeign.myOrder(username);
        List<Order> orders = result.getData();
        return orders;
    }

    /**
     * 根据用户名获取待付款列表信息
     * @param username
     * @return
     */
    @Override
    public List<Order> notpay(String username) {
        Result<List<Order>> result = orderFeign.notpay(username);
        List<Order> orders = result.getData();
        return orders;
    }

    /**
     * 取消订单
     * @param orderId
     */
    @Override
    public void offOrder(String orderId) {
        Result result = orderFeign.offOrder(orderId);
    }

    /**
     * 我的订单待发货,待收货订单列表
     * @param username
     * @return
     */
    @Override
    public List<Order> deliveryTheGoods(String username) {
        Result<List<Order>> result = orderFeign.deliveryTheGoods(username);
        List<Order> orders = result.getData();
        return orders;
    }


    /**
     * 确认收货
     * @param orderId
     * @return
     */
    @Override
    public int confirm(String orderId) {
        Result confirm = orderFeign.confirm(orderId);
        int i = (int) confirm.getData();
        return i;
    }

    /**
     * 提醒发货
     * @param orderId
     * @return
     */
    @Override
    public int remind(String orderId) {
        Result result = recordFeign.remind(orderId);
        int i = (int) result.getData();
        return i;
    }
}
