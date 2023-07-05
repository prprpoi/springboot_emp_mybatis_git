package com.bluebird.controller;

import com.bluebird.po.Result;
import com.bluebird.po.emp;
import com.bluebird.service.EmpService;
import com.bluebird.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody emp emp) {
        log.info("员工登录:{}", emp);
        emp e = empService.login(emp);

        //登陆成功,生成令牌,并下发令牌
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("userName", e.getUsername());
            //jwt包含了当前登录的员工信息
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }


        //登陆失败,返回错误信息
        return e != null ? Result.success() : Result.error("用户名或密码错误");
    }
}
