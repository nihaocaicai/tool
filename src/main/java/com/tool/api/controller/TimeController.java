package com.tool.api.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tool.api.entity.Datetime;
import com.tool.api.service.TimeService;


@Controller
public class TimeController {
    @Autowired
    private TimeService timeService;
    
    /*
     * 每次调用目标方法之前都会执行它
     * 先从数据库获取原始数据，再将新的参数直接替代原来的参数，若没有原始参数对应的新参数，则不作修改
     * map将修改过后的User返回给目标方法
     */
    @ModelAttribute
    public void getTime(@RequestParam(value = "user_id", required = false) String user_id,
    					@RequestParam(value = "time_date", required = false) String time_date, Map<String, Object> map) {
    	Map<String, String> map1 = new HashMap<>();
    	System.out.println(user_id);
    	System.out.println(time_date);
    	map1.put("user_id", user_id);
    	map1.put("time_date", time_date);
    	//查询数据库原始记录
    	Datetime time = timeService.findOnlyTimeById(map1);
    	System.out.println("从数据库中取出一个对象：" + time);
    	/* 
    	 * 判断条件针对数据库插入数据而设
    	 * 由于数据库开始肯定不存在这个user_id，所以返回必定为空，若强行put进去，则调用插入方法时候会NullPointException
    	 */
    	if(time == null) {
    		System.out.println("没有对象");
    	}
    	else {
    		map.put("time", time);
    	}
    }

    /*
    *  根据id查询用户全部时间进度安排
    *  测试例子：http://localhost:8080/tool/findTimeById?user_id=abcddsssagafafa
    * */ 
    @RequestMapping("/findTimeById")
    public String findTimeById(String user_id, Model model) {
    	Datetime time = timeService.findTimeById(user_id);
        model.addAttribute("time", time);
    	return "time";
    }
    
    /*
     * 插入某用户一条时间安排记录
     *  测试例子：http://localhost:8080/tool/insertTime?user_id=abcabc&time_content=别浪费时间了，快上车&time_date=2019-04-23
     */
    @RequestMapping("/insertTime")
    public String insertTime(String user_id, String time_content, String time_date, Model model) throws ParseException {
    	try {
			// 将前端传过来的字符串进行转码，因为http请求默认编码为ISO-8859-1，若不转码，该参数会乱码
			user_id = new String(user_id.getBytes("ISO-8859-1"), "UTF-8");
			time_content = new String(time_content.getBytes("ISO-8859-1"), "UTF-8");
			time_date = new String(time_date.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		java.sql.Date date = java.sql.Date.valueOf(time_date);
		Datetime time = new Datetime(user_id, time_content, date);
		timeService.insertTime(time);
    	return "success";
    }
    	
    /*
     * 目标方法获取根据表单修改过后的Datetime -> @ModelAttribute
     * 更新数据库某个用户时间安排记录
     *  测试例子：http://localhost:8080/tool/updateTime?user_id=abc&time_content=12月3日考试改成12月4日&time_date=2019-04-11
     */
    @RequestMapping("/updateTime")
    public String updateTime(Datetime time) {
    	System.out.println(time);
    	timeService.updateTime(time);
    	return "success";
    }
    
    /*
     * 删除某用户一条时间安排记录
     * 测试例子：http://localhost:8080/tool/deleteTime?user_id=abcabc&time_date=2019-04-23
     */
    @RequestMapping("/deleteTime")
    public String deleteTime(@RequestParam(value = "user_id") String user_id,
    						 @RequestParam(value = "time_date") String time_date) {
    	Map<String, String> map = new HashMap<>();
    	map.put("user_id", user_id);
    	map.put("time_date", time_date);
    	timeService.deleteTime(map);
    	return "success";
    }
}
