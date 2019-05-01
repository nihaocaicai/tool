package com.tool.api.controller;

import com.alibaba.fastjson.JSON;
import com.tool.api.exception.BaseException;
import com.tool.api.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


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
     * http://localhost:8080/tool/v1/token
     */
    @RequestMapping(value = "/v1/token", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getToken(@RequestBody String code) throws Exception {
        if(JSON.parseObject(code).getString("code")==null){
            throw new BaseException("error_code:2000:msg:code不能为空");
        }
        String token = userTokenService.getToken(JSON.parseObject(code).getString("code"));
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("token",token);
        return JSON.toJSONString(map);
    }
}
