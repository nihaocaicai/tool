package com.tool.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tool.api.entity.Error;
import com.tool.api.service.ErrorService;

@Controller
public class ErrorController {
    @Autowired
    private ErrorService errorService;

    /*
    *根据id查询用户错误反馈
    * */ 
    @RequestMapping("/findErrorById")
    public String findErrorById(String id, Model model) {
    	Error error = errorService.findErrorById(id);
        model.addAttribute("error", error);
    	return "error";
    }
}
