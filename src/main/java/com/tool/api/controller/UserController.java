package com.tool.api.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tool.api.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.alibaba.fastjson.JSON;
import com.tool.api.entity.User;
import com.tool.api.service.UserService;

import static com.tool.api.utils.responseSuccess.success;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    
//    /*
//     * 每次调用目标方法之前都会执行它
//     * 先从数据库获取原始数据，再将新的参数直接替代原来的参数，若没有原始参数对应的新参数，则不作修改
//     * map将修改过后的User返回给目标方法
//     */
//    @ModelAttribute
//    public void getUser(@RequestParam(value = "id", required = false) int id, Map<String, Object> map) {
//    	//查询数据库原始记录
//    	User user = userService.findUserById(id);
//    	System.out.println("从数据库中取出一个对象：" + user);
//    	/*
//    	 * 判断条件针对数据库插入数据而设
//    	 * 由于数据库开始肯定不存在这个user_id，所以返回必定为空，若强行put进去，则调用插入方法时候会NullPointException
//    	 */
//    	if(user == null) {
//
//    	}
//    	else {
//    		System.out.println("进行修改");
//    		map.put("user", user);
//    	}
//    }
    
    /*
      *根据id查询用户信息
     * 测试例子：http://localhost:8080/tool/v1/user/info/show?token=abcddsssagafafa
     */
    @RequestMapping(value = "/user/info/show", method = {RequestMethod.GET}, produces="application/json;charset=UTF-8")
	@ResponseBody
    public String findUserById(String id){
		int uid = Integer.parseInt(id);
        User user = userService.findUserById(uid);
        return JSON.toJSONString(user);
    }
    
    /*
     * 更新数据库某个用户记录
     * 测试例子：http://localhost:8080/tool/v1/user/info/modify
     */
    @RequestMapping(value = "/user/info/modify", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String updateUser(@ModelAttribute("id") String id,@ModelAttribute("user_info") String user_info) {
//        System.out.println(id);
//        System.out.println(user_info);
        int uid = Integer.parseInt(id);
        String user_name = JSON.parseObject(user_info).getString("user_name");
        String user_avatar = JSON.parseObject(user_info).getString("user_avatar");
        Integer user_gender = JSON.parseObject(user_info).getInteger("user_gender");
        String user_city = JSON.parseObject(user_info).getString("user_city");
        Date user_birthday = Date.valueOf(JSON.parseObject(user_info).getString("user_birthday"));
        String user_target = JSON.parseObject(user_info).getString("user_target");
        String user_motto = JSON.parseObject(user_info).getString("user_motto");
        Date user_exam_date = Date.valueOf(JSON.parseObject(user_info).getString("user_exam_date"));
//        System.out.println(Date.valueOf(user_brithday));
//    	根据修改过后的user去更新数据库
    	userService.updateUser(new User(uid,user_name,user_avatar,user_gender,user_city,
                user_birthday,user_target,user_motto,user_exam_date));
        return JSON.toJSONString(success("POST /user/info/modify"));
    }
    
    /*
     * 删除数据库中某个用户记录
     * 测试例子：http://localhost:8080/tool/deleteUser?user_id=CCC
     */
    @RequestMapping("/user/info/delete")
    public String delete(HttpServletRequest httpRequest) {
    	Map<String,?> Map = RequestContextUtils.getInputFlashMap(httpRequest); 
    	String token = (String) Map.get("token");
    	return "success";
    }
}
