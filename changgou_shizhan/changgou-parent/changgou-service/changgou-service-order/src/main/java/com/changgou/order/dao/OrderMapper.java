package com.changgou.order.dao;
import com.changgou.order.pojo.Order;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:admin
 * @Description:Order的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface OrderMapper extends Mapper<Order> {


    @Update(value = "update tb_order set order_status='3',pay_status='2' where id=#{orderId}")
    int offOrder(String orderId);

    @Select(value = "select * from tb_order where pay_status='1' and consign_status in ('0','1') and username=#{username}")
    List<Order> deliveryTheGoods(String username);
}
