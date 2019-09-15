package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserCenterWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterWebApplication.class,args);
    }
}
