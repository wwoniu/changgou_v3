package com.changgou;

import entity.IdWorker;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package com.changgou *
 * @since 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.changgou.order.dao")
@EnableFeignClients(basePackages = {"com.changgou.goods.feign", "com.changgou.usercenter.feign"})
@EnableScheduling//开启定时任务
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(0,1);
    }


    //创建死信队列
    @Bean
    public Queue createQueue1(){
        return QueueBuilder.durable("delay.queue")
                .withArgument("x-dead-letter-exchange","dlx.exchange")
                .withArgument("x-dead-letter-routing-key","queue.message")
                .build();
    }


    //创建正常的队列
    @Bean
    public Queue createQueue2(){
        return new Queue("queue.message");
    }

    //创建交换机
    @Bean
    public DirectExchange dlxExchanage(){
        return new DirectExchange("dlx.exchange");
    }

    //创建binding

    @Bean(name="SeckillBinding")
    public Binding dlxBinding(){
        return  BindingBuilder
                .bind(createQueue2())
                .to(dlxExchanage())
                .with("queue.message");
    }
}
