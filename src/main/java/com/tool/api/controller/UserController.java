package com.tool.api.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.tool.api.entity.User;
import com.tool.api.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    
    /*
     * 每次调用目标方法之前都会执行它
     * 先从数据库获取原始数据，再将新的参数直接替代原来的参数，若没有原始参数对应的新参数，则不作修改
     * map将修改过后的User返回给目标方法
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "token", required = false) String token, Map<String, Object> map) {
    	//查询数据库原始记录
    	User user = userService.findUserById(token);
    	System.out.println("从数据库中取出一个对象：" + user);
    	/* 
    	 * 判断条件针对数据库插入数据而设
    	 * 由于数据库开始肯定不存在这个user_id，所以返回必定为空，若强行put进去，则调用插入方法时候会NullPointException
    	 */
    	if(user == null) {
    		
    	}
    	else {
    		System.out.println("进行修改");
    		map.put("user", user);
    	}
    }
    
    /*
      *根据id查询用户信息
     * 测试例子：http://localhost:8080/tool/api.tool/v1/user/info/show?token=abcddsssagafafa
     */
    @RequestMapping(value = "/api.tool/v1/user/info/show", method = RequestMethod.GET)
    public String findUserById(String token, ModelMap map){
        User user = userService.findUserById(token);
        String json = "";
    	json += JSON.toJSONString(user);
        map.put("message",json);
//        返回用户信息展示页面
        return "user";
    }
    
    /*
     * 数据库插入一个新的用户记录
     * 测试例子:http://localhost:8080/tool/api.tool/v1/user/info/add?token=abc&user_target=XXXX&user_city=湛江
     */
    @RequestMapping(value = "/api.tool/v1/user/info/add", method = RequestMethod.GET)
    public String insertUser(User user, ModelMap map) {
    	userService.insertUser(user);
    	String message = "{\"error_code\":0,\"msg\":\"ok\",\"request\":\"POST api.tool/v1/user/info/add\"";
    	map.put("message", message);
    	return "user";
    }
    
    /*
     * 目标方法获取根据表单修改过后的User -> @ModelAttribute
     * 更新数据库某个用户记录
     * 测试例子：http://localhost:8080/tool/api.tool/v1/user/info/modify?token=abc&user_target=XXXX&user_city=湛江
     */
    @RequestMapping(value = "/api.tool/v1/user/info/modify", method = RequestMethod.GET)
    public String updateUser(User user, ModelMap map) {
    	//根据修改过后的user去更新数据库
    	userService.updateUser(user);
    	String message = "{\"error_code\":0,\"msg\":\"ok\",\"request\":\"POST api.tool/v1/user/info/modify\"}";
    	map.put("message", message);
    	return "user";
    }
    
    /*
     * 删除数据库中某个用户记录
     * 测试例子：http://localhost:8080/tool/deleteUser?user_id=CCC
     */
    @RequestMapping("/deleteUser")
    public String delete(@RequestParam(value = "user_id") String user_id) {
    	userService.deleteUser(user_id);
    	return "success";
    }
}
