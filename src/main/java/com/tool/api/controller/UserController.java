package com.tool.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tool.api.entity.User;
import com.tool.api.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    /*
    *根据id查询用户信息
    * */
    @RequestMapping("/findUserById")
    public String findUserById(String id, Model model){
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
//        返回用户信息展示页面
        return "user";
    }
}
