package com.tool.api.controller;

import java.sql.Date;

import com.tool.api.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.tool.api.entity.User;
import com.tool.api.service.UserService;

import static com.tool.api.utils.responseSuccess.success;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    /*
     *根据id查询用户信息
     */
    @RequestMapping(value = "/user/info/show", produces="application/json;charset=UTF-8")
	@ResponseBody
    public String findUserById(String id){
		int uid = Integer.parseInt(id);
        User user = userService.findUserById(uid);
        return JSON.toJSONString(user);
    }
    
    /*
     * 更新数据库某个用户记录
     */
    @RequestMapping(value = "/user/info/modify", produces="application/json;charset=UTF-8")
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
     */
    @RequestMapping(value = "/user/info/delete", produces="application/json;charset=UTF-8")
    @ResponseBody
    public String delete(String id) throws Exception{
        int uid = Integer.parseInt(id);
        //查找数据库是否有id
        int if_exit = userService.findUserByIdIf(uid);
        System.out.println(if_exit);
        if (if_exit!=0){
            //查询数据库有，删除
            userService.deleteUser(uid);
            return JSON.toJSONString(success("GET /user/info/delete"));
        }else {
            throw new BaseException("error_code:3000:msg:该用户在数据库中不存在");
        }
    }
}
