package com.weblearning.tomato.controller;

import com.weblearning.tomato.mapper.LoginMapper;
import com.weblearning.tomato.pojo.Result;
import com.weblearning.tomato.pojo.User;
import com.weblearning.tomato.service.LoginService;
import com.weblearning.tomato.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginMapper loginMapper;
    //注册操作，等待加入检验重复注册功能
    @PostMapping("/add")
    public Result addUser(@RequestBody User user) {
        log.info("注册操作");
        loginMapper.addUser(user);
        return Result.success();
    }
    //登陆操作，采用Jwt令牌
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("登录操作");
        User u = loginMapper.login(user);
        if (u != null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", u.getId());
            map.put("username", u.getUsername());
            String jwt = JwtUtils.getJwt(map);
            return Result.success(jwt);
        }
        else{
            return Result.error("用户名不存在或密码错误，请重试");
        }
    }
    //用id获取用户
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id") Integer id) {
        log.info("依据id获取用户");
        loginMapper.getUserById(id);
        return Result.success();
    }
}
