package com.tool.api.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import com.tool.api.entity.Plan;
import com.tool.api.service.PlanService;
import com.tool.api.service.UserService;
import com.tool.api.utils.RedisOps;
import com.tool.mapperClass.FindPlanAllAfter;
import com.tool.mapperClass.FindPlanByIdAndDate;
import com.tool.mapperClass.FormId;
import com.tool.mapperClass.OpenIdAndFormId;

@Controller
public class PlanController {
	@Autowired
	private PlanService planService;
	@Autowired
	private UserService userService;

	/*
	 * 每次调用目标方法之前都会执行它 先从数据库获取原始数据，再将新的参数直接替代原来的参数，若没有原始参数对应的新参数，则不作修改
	 * map将修改过后的User返回给目标方法
	 */
	@ModelAttribute
	public void getPlan(@RequestParam(value = "plan_id", required = false) String plan_id, Map<String, Object> map) {
		System.out.println(plan_id);
		// 查询数据库原始记录
		Plan plan = planService.findPlanByPlanId(plan_id);
		System.out.println("从数据库中取出一个对象：" + plan);
		/*
		 * 判断条件针对数据库插入数据而设
		 * 由于数据库开始肯定不存在这个user_id，所以返回必定为空，若强行put进去，则调用插入方法时候会NullPointException
		 */
		if (plan == null) {
			System.out.println("没有对象");
		} else {
			map.put("plan", plan);
		}
	}

	/*
	 * 获取用户当天考研计划
	 * 测试例子：http://localhost:8080/tool/api.tool/v1/user/plans/all/intraday?token=
	 * abcddsssagafafa&date=2019-04-14
	 * 
	 * @Param FindPlanByIdAndDate 临时存储pojo 位置com.tool.mapperClass
	 */
	@RequestMapping(value = "/api.tool/v1/user/plans/all/intraday", method = {RequestMethod.GET}, produces="application/json;charset=UTF-8")
	public String findPlansAllIntraday(String token, String date, ModelMap map) {
		List<FindPlanByIdAndDate> plan = planService.findPlanAllIntraday(token, date);
		// 转换成JSON格式
		String json = "data:";
		json += JSON.toJSONString(plan);
		System.out.println(json);
		map.put("message", json);
		return "plan";
	}

	/*
	 * 获取用户全部考研计划（当天之前的）
	 * 测试例子：http://localhost:8080/tool/api.tool/v1/user/plans/all/before?token=
	 * abcddsssagafafa&date=2019-04-19
	 * 
	 * @Param FindPlanAllAfter 临时存储pojo 位置com.tool.mapperClass
	 */
	@RequestMapping(value = "/api.tool/v1/user/plans/all/before", method = {RequestMethod.GET}, produces="application/json;charset=UTF-8")
	public String findPlansAllbefore(String token, String date, ModelMap map) {
		List<FindPlanAllAfter> list = planService.findPlanAllBefore(token, date);
		String json = JSON.toJSONString(list);
		map.put("message", json);
		return "plan";
	}

	/*
	 * 获取用户全部考研计划（当天之后的）
	 * 测试例子：http://localhost:8080/tool/api.tool/v1/user/plans/all/after?token=abc&
	 * date=2019-04-19
	 * 
	 * @Param FindPlanAllAfter 临时存储pojo 位置com.tool.mapperClass
	 */
	@RequestMapping(value = "/api.tool/v1/user/plans/all/after", method = {RequestMethod.GET}, produces="application/json;charset=UTF-8")
	public String findPlansAllAfter(String token, String date, ModelMap map) {
		List<FindPlanAllAfter> list = planService.findPlanAllAfter(token, date);
		String json = "all_plan:";
		json += JSON.toJSONString(list);
		System.out.println(list);
		map.put("message", json);
		return "plan";
	}

	/*
	 * 插入某用户一条计划记录 http://localhost:8080/tool/insertPlan?user_id=abc&plan_content=
	 * 复习高数plan_start_time=2019-2-17%208:45:33&
	 * plan_end_time=2019-6-12%209:35:33&plan_if_repeat=1&plan_if_prompt=0&
	 * plan_if_prompt_time=2020-03-26%209:06:31& plan_if_finish=0
	 */
	@RequestMapping(value = "/insertPlan", method = {RequestMethod.POST}, produces="application/json;charset=UTF-8")
	public String insertPlan(String user_id, String plan_content, String plan_start_time, String plan_end_time,
			String plan_if_repeat, String plan_if_prompt, String plan_if_prompt_time, String plan_if_finish,
			String formid, Model model) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date planStartTime = format.parse(plan_start_time);
		Date planEndTime = format.parse(plan_end_time);
		Date planIfPromptTime = format.parse(plan_if_prompt_time);

		Plan plan = new Plan(user_id, plan_content, planStartTime, planEndTime, Integer.valueOf(plan_if_repeat),
				Integer.valueOf(plan_if_prompt), planIfPromptTime, Integer.valueOf(plan_if_finish));
		planService.insertPlan(plan);

		// 将该次表单产生的formid与过期期限写进缓存，键值对为key:user_id,
		// value=openIdAndFormId@Param=openid&@Param=FormId
		// 获取当前时间
		Date date = new Date();
		// 当前时间+7则为formid过期时间
		Calendar rightnow = Calendar.getInstance();
		rightnow.setTime(date);
		rightnow.add(Calendar.DATE, 7);
		Date endday = rightnow.getTime();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH");
		String formidDate = format1.format(endday);
		System.out.println(formidDate);
		// 判断是否初次缓存？
		if (RedisOps.getObject(user_id) == null) {
			// 取出uid对应的openid
			String open_id = "";
			System.out.println(open_id);
			OpenIdAndFormId openIdAndFormId = new OpenIdAndFormId();
			FormId formid1 = new FormId(formid, formidDate);
			List<FormId> list = new ArrayList<FormId>();
			list.add(formid1);
			openIdAndFormId.setOpenid(open_id);
			openIdAndFormId.setFormid(list);
			RedisOps.setObject(user_id, openIdAndFormId);
		} else {
			OpenIdAndFormId openIdAndFormId = (OpenIdAndFormId) RedisOps.getObject(user_id);
			List<FormId> list = openIdAndFormId.getFormid();
			System.out.println("list:" + list);
			FormId formId2 = new FormId(formid, formidDate);
			list.add(formId2);
			openIdAndFormId.setFormid(list);
			RedisOps.setObject(user_id, openIdAndFormId);
		}
		return "success";
	}

	/*
	 * 目标方法获取根据表单修改过后的Plan -> @ModelAttribute 更新数据库某个用户计划记录
	 * 测试用例：http://localhost:8080/tool/updatePlan?plan_id=6&plan_content=今日复习计划
	 */
	@RequestMapping(value = "/updatePlan", method = {RequestMethod.POST}, produces="application/json;charset=UTF-8")
	public String updateTime(Plan plan) {
		System.out.println(plan);
		planService.updatePlan(plan);
		return "success";
	}

	/*
	 * 删除某用户一个计划记录 测试用例：http://localhost:8080/tool/deletePlan?plan_id=4
	 */
	@RequestMapping("/deletePlan")
	public String deletePlan(@RequestParam(value = "plan_id") String plan_id) {
		planService.deletePlan(plan_id);
		return "success";
	}
}
