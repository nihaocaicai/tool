package com.tool.api.controller;

import com.alibaba.fastjson.JSON;
import com.tool.api.entity.User;
import com.tool.api.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
* 获取令牌，相当于登录
* */
@Controller
public class TokenController {
    @Autowired
    private UserTokenService userTokenService;
    /**
     * 用户获取令牌（登陆）
     * @url /token
     * @POST code
     * @note 虽然查询应该使用get，但为了稍微增强安全性，所以使用POST
     * http://localhost:8080/tool/token
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @ResponseBody
    public String getToken(@RequestBody String code) throws Exception {
        if(JSON.parseObject(code).isEmpty()){
            return JSON.toJSONString("code不能为空");
        }
        String token = userTokenService.getToken(JSON.parseObject(code).getString("code"));
        token="{"+"\""+"token"+"\""+":"+"\""+token+"\""+"}";
        return JSON.toJSONString(token);
//        System.out.println(JSON.parseObject(code).getString("code"));
//        return "token";
    }
}
