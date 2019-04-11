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
import com.tool.api.entity.Plan;
import com.tool.api.service.PlanService;

@Controller
public class PlanController {
    @Autowired
    private PlanService planService;
    
    /*
     * 每次调用目标方法之前都会执行它
     * 先从数据库获取原始数据，再将新的参数直接替代原来的参数，若没有原始参数对应的新参数，则不作修改
     * map将修改过后的User返回给目标方法
     */
    @ModelAttribute
    public void getTime(@RequestParam(value = "user_id", required = false) String user_id,
    					@RequestParam(value = "plan_date", required = false) String plan_date, Map<String, Object> map) {
    	Map<String, String> map1 = new HashMap<>();
    	System.out.println(user_id);
    	System.out.println(plan_date);
    	map1.put("user_id", user_id);
    	map1.put("plan_date", plan_date);
    	//查询数据库原始记录
    	Plan plan = planService.findOnlyPlanById(map1);
    	System.out.println("从数据库中取出一个对象：" + plan);
    	/* 
    	 * 判断条件针对数据库插入数据而设
    	 * 由于数据库开始肯定不存在这个user_id，所以返回必定为空，若强行put进去，则调用插入方法时候会NullPointException
    	 */
    	if(plan == null) {
    		System.out.println("没有对象");
    	}
    	else {
    		map.put("plan", plan);
    	}
    }

    /*
    *根据id查询用户计划安排
    *测试例子：http://localhost:8080/tool/findPlanById?user_id=abcddsssagafafa
    * */ 
    @RequestMapping("/findPlanById")
    public String findPlanById(String user_id, Model model) {
    	Plan plan = planService.findPlanById(user_id);
        model.addAttribute("plan", plan);
    	return "plan";
    }
    
    /*
     * 插入某用户一条计划记录
     * http://localhost:8080/tool/insertPlan?user_id=abc&plan_content=想去踏春&plan_date=2019-05-06
     */
    @RequestMapping("/insertPlan")
    public String insertPlan(String user_id, String plan_content, String plan_date, Model model) throws ParseException {
    	try {
			// 将前端传过来的字符串进行转码，因为http请求默认编码为ISO-8859-1，若不转码，该参数会乱码
			user_id = new String(user_id.getBytes("ISO-8859-1"), "UTF-8");
			plan_content = new String(plan_content.getBytes("ISO-8859-1"), "UTF-8");
			plan_date = new String(plan_date.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		java.sql.Date date = java.sql.Date.valueOf(plan_date);
		Plan plan = new Plan(user_id, plan_content, date);
		planService.insertPlan(plan);
    	return "success";
    }
    
    /*
          * 目标方法获取根据表单修改过后的Plan -> @ModelAttribute
     * 更新数据库某个用户计划记录
     * 测试用例：http://localhost:8080/tool/updatePlan?user_id=abcddsssagafafa&plan_content=wwWWWWWW&plan_date=2019-04-10 
     */
    @RequestMapping("/updatePlan")
    public String updateTime(Plan plan) {
    	System.out.println(plan);
    	planService.updatePlan(plan);
    	return "success";
    }
    
    /*
     * 删除某用户一个计划记录
     * 测试用例：http://localhost:8080/tool/deletePlan?user_id=abc&plan_date=2019-05-06
     */
    @RequestMapping("/deletePlan")
    public String deletePlan(@RequestParam(value = "user_id") String user_id,
    						 @RequestParam(value = "plan_date") String plan_date) {
    	Map<String, String> map = new HashMap<>();
    	map.put("user_id", user_id);
    	map.put("plan_date", plan_date);
    	planService.deletePlan(map);
    	return "success";
    }
}
