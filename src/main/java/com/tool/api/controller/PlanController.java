package com.tool.api.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tool.api.entity.Plan;
import com.tool.api.service.PlanService;
import com.tool.mapperClass.FindPlanByIdAndDate;

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
    public void getPlan(@RequestParam(value = "plan_id", required = false) String plan_id, Map<String, Object> map) {
    	System.out.println(plan_id);
    	//查询数据库原始记录
    	Plan plan = planService.findPlanByPlanId(plan_id);
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
    *根据user_id还有时间date查询用户计划安排
    *测试例子：http://localhost:8080/tool/findPlanByIdAndDate?user_id=abcddsssagafafa&date=2019-04-14
    *@Param FindPlanByIdAndDate 临时存储pojo 位置com.tool.mapperClass
    * */ 
    @RequestMapping("/findPlanByIdAndDate")
    public String findPlanByIdAndDate(String user_id, String date, ModelMap model) {
    	List<FindPlanByIdAndDate> plan = planService.findPlanByIdAndDate(user_id, date);
    	//转换成JSON格式
    	String json = "data:";
    	json += JSON.toJSONString(plan);
    	model.put("data", json);
    	return "plan";
    }
    
    /*
     * 插入某用户一条计划记录
     * http://localhost:8080/tool/insertPlan?user_id=abc&plan_content=复习高数plan_start_time=2019-2-17%208:45:33&
     * plan_end_time=2019-6-12%209:35:33&plan_if_repeat=1&plan_if_prompt=0&plan_if_prompt_time=2020-03-26%209:06:31&
     * plan_if_finish=0
     */
    @RequestMapping("/insertPlan")
    public String insertPlan(String user_id, String plan_content, String plan_start_time, String plan_end_time,
    						 String plan_if_repeat, String plan_if_prompt, String plan_if_prompt_time, String plan_if_finish, Model model) throws ParseException {   	
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date planStartTime = format.parse(plan_start_time);
    	Date planEndTime = format.parse(plan_end_time);
    	Date planIfPromptTime = format.parse(plan_if_prompt_time);
    	
    	Plan plan = new Plan(user_id, plan_content, planStartTime, planEndTime, Integer.valueOf(plan_if_repeat), Integer.valueOf(plan_if_prompt), planIfPromptTime, Integer.valueOf(plan_if_finish));
    	planService.insertPlan(plan);
    	return "success";
    }
    
    /*
          * 目标方法获取根据表单修改过后的Plan -> @ModelAttribute
     * 更新数据库某个用户计划记录
     * 测试用例：http://localhost:8080/tool/updatePlan?plan_id=6&plan_content=今日复习计划
     */
    @RequestMapping("/updatePlan")
    public String updateTime(Plan plan) {
    	System.out.println(plan);
    	planService.updatePlan(plan);
    	return "success";
    }
    
    /*
     * 删除某用户一个计划记录
     * 测试用例：http://localhost:8080/tool/deletePlan?plan_id=4
     */
    @RequestMapping("/deletePlan")
    public String deletePlan(@RequestParam(value = "plan_id") String plan_id) {
    	planService.deletePlan(plan_id);
    	return "success";
    }
}
