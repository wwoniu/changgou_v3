package com.changgou.usercenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/userCenter")
public class UserCenterController {
    @RequestMapping("/myOrder")
    public String getMyOrder(){
        return "myOrder";
    }
    @RequestMapping("/addressList")
    public String getAddressList(){
        return "addressList";
    }
    @RequestMapping("/home")
    public String getHome(String str){
        return "home";
    }
    @RequestMapping("/mycomment")
    public String getMycomment(String str){
        return "mycomment";
    }
    @RequestMapping("/userinfo")
    public String getUserinfo(String str){
        return "userinfo";
    }
}
