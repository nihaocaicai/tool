package com.tool.api.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public void getUser(@RequestParam(value = "user_id", required = false) String user_id, Map<String, Object> map) {
    	//查询数据库原始记录
    	User user = userService.findUserById(user_id);
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
     * 测试例子：http://localhost:8080/tool/findUserById?user_id=abcddsssagafafa
     */
    @RequestMapping("/findUserById")
    public String findUserById(String user_id, Model model){
        User user = userService.findUserById(user_id);
        model.addAttribute("user",user);
//        返回用户信息展示页面
        return "user";
    }
    
    /*
     * 数据库插入一个新的用户记录
     * 测试例子http://localhost:8080/tool/insertUser?user_id=DDD&user_name=orange&user_avatar=bbbbbb&user_gender=2&user_city=徐闻
     * user_birthday=2015-07-15&user_target=tshinghua&user_motto=心有所向&user_exam_date=2019-05-31
     */
    @RequestMapping("/insertUser")
    public String insertUser(String user_id, String user_name, String user_avatar, String user_gender, String user_city, 
    						 String user_birthday, String user_target, String user_motto, String user_exam_date) {
    	java.sql.Date userBirthday = java.sql.Date.valueOf(user_birthday);
    	java.sql.Date userExamDay = java.sql.Date.valueOf(user_exam_date);
    	System.out.println("userExamDay:" + userExamDay);
    	User user = new User(user_id, user_name, user_avatar, Integer.valueOf(user_gender), user_city, userBirthday, user_target, user_motto, userExamDay);
    	userService.insertUser(user);
    	return "success";
    }
    
    /*
     * 目标方法获取根据表单修改过后的User -> @ModelAttribute
     * 更新数据库某个用户记录
     * 测试例子：http://localhost:8080/tool/updateUser?user_id=abc&user_target=XXXX&user_city=湛江
     */
    @RequestMapping("/updateUser")
    public String updateUser(User user) {
    	//这是根据表单提交的信息修改过后的user
    	System.out.println("修改：" + user);
    	//根据修改过后的user去更新数据库
    	userService.updateUser(user);
    	return "success";
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
