package com.changgou.user.controller;

import com.changgou.order.feign.OrderFeign;
import com.changgou.order.pojo.Order;
import com.changgou.user.feign.UserFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/userCenter")
public class UserCenterController {

    @Autowired
    private UserFeign userFeign;

    @RequestMapping("/myOrder")
    public String getMyOrder(){
        return "myOrder";
    }
    @RequestMapping("/addressList")
    public String getAddressList(){
        return "addressList";
    }

    @RequestMapping("/home")
    public String getHome(HttpServletRequest request,Model model){
        String token="";
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equalsIgnoreCase("Authorization")){
                token = cookie.getValue();
            }
        }

        //Result getusername = userFeign.getusername();
        //String username = (String) getusername.getData();
        String username="zhangsan";
        model.addAttribute("username",username);
        model.addAttribute("token",token);
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
