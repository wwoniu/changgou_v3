package com.changgou.order.service;

import com.changgou.order.pojo.Order;
import com.changgou.order.pojo.OrderItem;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;
import java.util.Map;

/****
 * @Author:admin
 * @Description:Order业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface OrderService {

    /***
     * Order多条件分页查询
     * @param order
     * @param page
     * @param size
     * @return
     */
    PageInfo<Order> findPage(Order order, int page, int size);

    /***
     * Order分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Order> findPage(int page, int size);

    /***
     * Order多条件搜索方法
     * @param order
     * @return
     */
    List<Order> findList(Order order);

    /***
     * 删除Order
     * @param id
     */
    void delete(String id);

    /***
     * 修改Order数据
     * @param order
     */
    void update(Order order);

    /***
     * 新增Order
     * @param order
     */
    Order add(Order order);

    /**
     * 根据ID查询Order
     * @param id
     * @return
     */
     Order findById(String id);

    /***
     * 查询所有Order
     * @return
     */
    List<Order> findAll();

    /**
     * 更新对应的订单的状态
     * @param out_trade_no
     * @param transaction_id
     */
    public void updateStatus(String out_trade_no,String transaction_id);


    /**
     * 查询我的订单信息
     * @param username
     * @return
     */
    List<Order> myOrder(String username);

    /**
     * 查询待付款列表
     * @param username
     * @return
     */
    List<Order> notpay(String username);

    void offOrder(String orderId);

    List<Order> deliveryTheGoods(String username);

    int confirm(String orderId);

    List<Order> findByUsername(String username);

    List<OrderItem> findByOrderId(String orderId);
}
