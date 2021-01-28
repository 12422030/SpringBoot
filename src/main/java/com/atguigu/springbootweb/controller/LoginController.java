package com.atguigu.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        //登录成功
        if(!StringUtils.isEmpty(username) &&"112233".equals(password))
        {
            session.setAttribute("loginUser",username);
            //return "dashboard";
            return "redirect:/main.html";
        }
        //登录失败
        else {
            map.put("msg","用户名密码错误");
            return "login";
        }
    }
}
