package com.example.demo1.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo1.annotation.UserLogin;
import com.example.demo1.entity.User;
import com.example.demo1.entity.UpdateUser;
import com.example.demo1.service.UserService;
import com.example.demo1.util.JwtUtil;
import com.example.demo1.util.RestTemplateUtil;
import org.springframework.data.redis.core.script.DigestUtils;
import com.example.demo1.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody HashMap<String, String> user){
        String username = user.get("username");
        String password = user.get("password");
        User result = userService.login(username, password);
        if(result != null) {
            final long seven_days = 604800000;
            String token = JwtUtil.createToken(String.valueOf(result.getId()), seven_days, "access");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);
            jsonObject.put("user", result);
            return new Result(0, jsonObject, "登录成功");
        }
        return new Result(-1, null, "登录失败");
    }

    @PostMapping("/register")
    public Result register(@RequestBody HashMap<String, String> user){
        String username = user.get("username");
        String password = user.get("password");
        log.info("register username: {} password: {}", username, password);
        User result = null;
        try{
            result = userService.register(username, password);

        } catch (Exception e){
            return new Result(-1, null, e.getMessage());
        }
        return new Result(0, result, "注册成功");
    }

    @UserLogin
    @PostMapping("/modify")
    public Result modifyPassword(HttpServletRequest request, @RequestBody HashMap<String, String> modifyMap){
        String token = request.getHeader("Authorization").substring(7);
        int userId = JwtUtil.getUserID(token);
        String password = modifyMap.get("password");
        String newPassword = modifyMap.get("new_password");
        User result = userService.modifyPassword(userId, password, newPassword);
        if (result != null) {
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

        User user = userService.selectByOpenid(openid);
        String userID = null;
        if (user == null){
            User user1 = userService.register("default", "123456");
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