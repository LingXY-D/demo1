package com.example.demo1.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo1.entity.user;
import com.example.demo1.entity.UpdateUser;
import com.example.demo1.service.UserService;
import com.example.demo1.util.RestTemplateUtil;
import org.springframework.data.redis.core.script.DigestUtils;
import com.example.demo1.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class usercontroller {

    private static final Logger log = LoggerFactory.getLogger(usercontroller.class);

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @PostMapping("/login")
    public Result login(@RequestBody user user){
        String username = user.getUsername();
        String password = user.getPassword();
        log.info("login username: {} password: {}", username, password);
        user result = userService.login(username, password);
        if(result != null) {
            log.info("login success");
            return new Result(0, result, "登陆成功");
        }
        log.info("login fail");
        return new Result(-1, null, "登陆失败");
    }

    @PostMapping("/register")
    public Result register(@RequestBody user user){
        String username = user.getUsername();
        String password = user.getPassword();
        log.info("register username: {} password: {}", username, password);
        user result = null;
        try{
            result = userService.register(username, password);
        } catch (Exception e){
            return new Result(-1, null, e.getMessage());
        }
        return new Result(0, result, "注册成功");
    }

    @PostMapping("/modifyPassWord")
    public Result modifyPassword(@RequestBody UpdateUser updateuser){
        String username = updateuser.getUsername();
        String password = updateuser.getPassword();
        String newpassword = updateuser.getNewpassword();
        System.out.println(newpassword);
        log.info("login username: {} password: {}", username, password);
        user result = userService.login(username, password);
        if(result != null) {
            result = userService.modifyPassword(username, newpassword);
            return new Result(0, result, "修改成功");
        }
        return new Result(-1, null, "修改失败：原密码错误");
    }

    @GetMapping("/wxLogin")
    public Result wxLogin(@RequestBody String code) throws Exception {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("appid", "Uwx21fc98efe10b45ab");
        param.add("secret", "09af092d9de21a3aa85b9870c0715427");
        param.add("js_code", code);
        param.add("grant_type", "authorization_code");
        JSONObject userObject = RestTemplateUtil.doPost(url, param);
        String openid = userObject.getString("openid");
        String session_key = userObject.getString("session_key");
        System.out.println(userObject);

        user user = userService.selectByOpenid(openid);
        String userID = null;
        if (user == null){
            user user1 = userService.register("default", "123456");
            user1.setOpenid(openid);
            userID = user1.getOpenid();
        }else {
            userID = user.getOpenid();
        }
        Map<String, String> result = new HashMap<>();
        result.put("session_key", session_key);
        result.put("open_id", userID);

        return new Result(1,result,"登录成功");
    }
}
