package com.changgou.ordercentre.service;

import com.changgou.order.pojo.Order;

import java.util.List;

public interface OrderCentreService {
    List<Order> myOrder(String username);

    List<Order> notpay(String username);

    void offOrder(String orderId);

    List<Order> deliveryTheGoods(String username);

    int confirm(String orderId);

    int remind(String orderId);
}
