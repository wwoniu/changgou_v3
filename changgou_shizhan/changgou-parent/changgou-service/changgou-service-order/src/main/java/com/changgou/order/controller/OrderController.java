package com.changgou.order.controller;

import com.changgou.order.config.TokenDecode;
import com.changgou.order.pojo.Order;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.OrderService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderService orderItemService;

    /***
     * Order分页条件搜索实现
     * @param order
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Order order, @PathVariable int page, @PathVariable int size) {
        //调用OrderService实现分页条件查询Order
        PageInfo<Order> pageInfo = orderService.findPage(order, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * Order分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用OrderService实现分页查询Order
        PageInfo<Order> pageInfo = orderService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param order
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Order>> findList(@RequestBody(required = false) Order order) {
        //调用OrderService实现条件查询Order
        List<Order> list = orderService.findList(order);
        return new Result<List<Order>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        //调用OrderService实现根据主键删除
        orderService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改Order数据
     * @param order
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Order order, @PathVariable String id) {
        //设置主键值
        order.setId(id);
        //调用OrderService实现修改Order
        orderService.update(order);
        return new Result(true, StatusCode.OK, "修改成功");
    }


    @Autowired
    private TokenDecode tokenDecode;

    /***
     * 创建订单
     * @param order
     * @return
     */
    @PostMapping
    public Result<Order> add(@RequestBody Order order) {
        //调用OrderService实现添加Order
        order.setUsername(tokenDecode.getUserInfo().get("username"));
        Order orderResult = orderService.add(order);
        return new Result(true, StatusCode.OK, "添加成功",orderResult);
    }

    /***
     * 根据ID查询Order数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Order> findById(@PathVariable String id) {
        //调用OrderService实现根据主键查询Order
        Order order = orderService.findById(id);
        return new Result<Order>(true, StatusCode.OK, "查询成功", order);
    }

    /***
     * 查询Order全部数据
     * @return
     */
    @GetMapping
    public Result<List<Order>> findAll() {
        //调用OrderService实现查询所有Order
        List<Order> list = orderService.findAll();
        return new Result<List<Order>>(true, StatusCode.OK, "查询成功", list);
    }


    /**
     * 查询我的订单信息
     * @param username
     * @return
     */
    @GetMapping("/myOrder")
    public Result<List<Order>> myOrder(@RequestParam String username){
        List<Order> orders= orderService.myOrder(username);
        return new Result<List<Order>>(true,StatusCode.OK,"查询我的订单成功",orders);
    }

    /**
     * 查询待付款列表
     * @param username
     * @return
     */
    @GetMapping("/notpay")
    public Result<List<Order>> notpay(@RequestParam String username){
        List<Order> orders= orderService.notpay(username);
        return new Result<List<Order>>(true,StatusCode.OK,"查询待付款列表成功",orders);
    }

    /**
     * 取消订单
     * @param orderId
     */
    @RequestMapping("/offOrder")
    public Result offOrder(@RequestParam String orderId){
        orderService.offOrder(orderId);
        return new Result(true, StatusCode.OK,"取消订单成功");
    }

    /**
     * 我的订单待发货,待收货订单列表
     * @param username
     * @return
     */
    @GetMapping("/deliveryTheGoods")
    public Result<List<Order>> deliveryTheGoods(@RequestParam String username){
        List<Order> orders= orderService.deliveryTheGoods(username);
        return new Result<List<Order>>(true,StatusCode.OK,"查询待发货,待收货订单列表成功",orders);
    }


    /**
     * 确认收货
     * @return
     */
    @RequestMapping("/confirm")
    public Result confirm(@RequestParam String orderId){
        int i = orderService.confirm(orderId);
        return new Result(true, StatusCode.OK,"确认收货成功",i);
    }

    /**
     * 得到订单中心信息
     * @param
     * @return
     */
    @RequestMapping("/orderDetail")
    public Result<List<Map>> findByUsername(){

        Map<String, String> userInfo = tokenDecode.getUserInfo();
        String username = userInfo.get("username");
        //最后封装到resultlist
        List<Map> resultList=new ArrayList();
        //查询出订单列表
        List<Order> orderList = orderService.findByUsername(username);
        for (Order order : orderList) {
            String orderId = order.getId();
            //根据订单id查询出item列表
            List<OrderItem> orderItemList= orderItemService.findByOrderId(orderId);
            HashMap<String, Object> map = new HashMap<>();
            map.put("order",order);
            map.put("orderItemList",orderItemList);
            resultList.add(map);
        }
        //将查询结果封装到map中
        Map<String,Object> map=new HashMap<>();
        //根据

        return new Result<List<Map>>(true, StatusCode.OK,"查询显示订单中心",resultList);
    }
}
