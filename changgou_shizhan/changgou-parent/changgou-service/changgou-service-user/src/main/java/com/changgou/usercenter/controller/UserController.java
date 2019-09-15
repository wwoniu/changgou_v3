package com.changgou.usercenter.controller;

import com.alibaba.fastjson.JSON;
import com.changgou.usercenter.config.TokenDecode;
import com.changgou.usercenter.dao.UserMapper;
import com.changgou.usercenter.pojo.User;
import com.changgou.usercenter.service.UserService;
import com.github.pagehelper.PageInfo;
import com.netflix.client.ClientException;
import entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /***
     * User分页条件搜索实现
     * @param user
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) User user, @PathVariable int page, @PathVariable int size) {
        //调用UserService实现分页条件查询User
        PageInfo<User> pageInfo = userService.findPage(user, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * User分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用UserService实现分页查询User
        PageInfo<User> pageInfo = userService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param user
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<User>> findList(@RequestBody(required = false) User user) {
        //调用UserService实现条件查询User
        List<User> list = userService.findList(user);
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        //调用UserService实现根据主键删除
        userService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改User数据
     * @param user
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody User user, @PathVariable String id) {
        //设置主键值
        user.setUsername(id);
        //调用UserService实现修改User
        userService.update(user);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增User数据
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        //调用UserService实现添加User
        userService.add(user);
        return new Result(true, StatusCode.OK, "添加成功");
    }


    /***
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable String id) {

        //调用UserService实现根据主键查询User
        User user = userService.findById(id);
        return new Result<User>(true, StatusCode.OK, "查询成功", user);
    }

    /**
     * 加载用户的数据
     *
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    public Result<User> findByUsername(@PathVariable(name = "id") String id) {
        //调用UserService实现根据主键查询User
        User user = userService.findById(id);
        return new Result<User>(true, StatusCode.OK, "查询成功", user);
    }

    /***
     *
     * 希望 拥有admin的角色人才能访问.
     * 查询User全部数据
     * @return
     */
    @PreAuthorize(value = "hasAuthority('goods_list')")
    // @PreAuthorize 表示 在执行方法之前 先进行权限校验,只有拥有 admin角色的用户可以执行该方法.
    @GetMapping
    public Result<List<User>> findAll(HttpServletRequest request) {

        System.out.println("头信息为:" + request.getHeader("Authorization"));


        //调用UserService实现查询所有User
        List<User> list = userService.findAll();
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", list);
    }

    @RequestMapping("/login")
    public Result<User> login(String username, String password, HttpServletResponse response, HttpServletRequest request) {
        //1.从数据库中查询用户名对应的用户的对象
        User user = userService.findById(username);
        if (user == null) {
            //2.判断用户是否为空 为空返回数据
            return new Result<User>(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }

        //3如果不为空格 判断 密码是否正确 正确则登录成功

        if (BCrypt.checkpw(password, user.getPassword())) {
            //成功
            Map<String, Object> info = new HashMap<String, Object>();
            info.put("role", "USER");
            info.put("success", "SUCCESS");
            info.put("username", username);

            //1.生成令牌
            String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(info), null);
            //2.设置cookie中
            Cookie cookie = new Cookie("Authorization", jwt);
            response.addCookie(cookie);
            //3.设置头文件中
            response.setHeader("Authorization", jwt);

            return new Result<User>(true, StatusCode.OK, "成功", jwt);
        } else {
            //失败
            return new Result<User>(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }


    }


    @GetMapping(value = "/points/add")
    public Result addPoints(@RequestParam(value="points") Integer points
            ,@RequestParam(value="username") String username ) {

        userService.addPoints(points,username);
        return new Result(true,StatusCode.OK,"添加积分成功");
    }


    @PostMapping(value = "/gerenxinxi/update")
    public Result update(@RequestBody User user){
        userService.geRenXinXiUpdate(user);
       return new Result(true,StatusCode.OK,"更新个人信息成功");
    }

    @Autowired
    private TokenDecode tokenDecode;
    @GetMapping("/user/info")
    public Result<User> userInfo(){
        String username = tokenDecode.getUserInfo().get("username");
       User user = userService.userInfo(username);
       return new Result<>(true,StatusCode.OK,"查询用户信息成功",user);
    }


    //登录状态修改密码
    @PutMapping("/updatepassword")
    public Result updatepassword(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password,@RequestParam(value = "newpassword") String newpassword ){
        //查询要修改密码的用户
        User user =userService.selectusername(username);
        //获取数据库里的密码
        String password1 = user.getPassword();
        //对输入的旧密码进行加密
        String s = Base64.toBase64String(password.getBytes());
        //判断输入的旧密码是否与数据库里的旧密码是否相同
        if (password1.equals(s)){
            //对新密码进行加密
            String updatepassword = Base64.toBase64String(newpassword.getBytes());
            //设置新密码
            user.setPassword(updatepassword);
            //将新密码保存到数据库
            userMapper.updateByPrimaryKey(user);
            return new Result(true,StatusCode.OK,"更改密码成功",user);
        }else {
            return new Result(false,StatusCode.ERROR,"旧密码错误,请重新输入");
        }

    }

    @Autowired
    private RedisTemplate redisTemplate;
    //发送短信
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        //获取随机数
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        try {
            //发送短信
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
            System.out.println(code);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.NOTFOUNDERROR,"短信发送失败");
        }
        //存入redis
        redisTemplate.boundValueOps("DUANXIN"+telephone).set(code.toString());
        //设置有效时长1个小时
        redisTemplate.expireAt("DUANXIN"+ telephone,DateUtil.addDateHour(new Date(), 1));
        return new Result(true,StatusCode.OK,"短信发送成功");
    }

    //短信修改密码
    @RequestMapping("/submit")
    public Result submitOrder(@RequestParam String telephone,@RequestParam String code,@RequestParam String username,@RequestParam String password){
        //从Redis中获取缓存的验证码，key为手机号+"DUANXIN"

        Object o1 = redisTemplate.boundValueOps("DUANXIN" + telephone).get();
        //校验手机验证码
        if (o1.equals(code)){
            //获取用户信息
            User byId = userService.findById(username);
            String phone = byId.getPhone();
            String byIdUsername = byId.getUsername();
            //判断用户名和绑定的手机号
            if (phone.equals(telephone)&&byIdUsername.equals(username)){
                //加密新密码
                String base64String = Base64.toBase64String(password.getBytes());
                //设置新密码
                byId.setPassword(base64String);
                //保存新密码
                userMapper.updateByPrimaryKey(byId);
            }else {
                return new Result(false,StatusCode.ERROR,"用户名或手机号不一致");
            }
            return new Result(true,StatusCode.OK,"修改密码成功");
        }

        return new Result(false,StatusCode.ERROR,"验证码错误");
    }


    @GetMapping("/user/name")
    public Result getusername(){
        Map<String, String> stringMap = tokenDecode.getUserInfo();
        String username = stringMap.get("username");
        return new Result<>(true,StatusCode.OK,"查询成功!",username);
    }
}
