package com.tool.api.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.*;

import com.tool.api.utils.responseDataUtils.ResponseData;
import com.tool.api.utils.responseDataUtils.ResponsePlanData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.tool.api.entity.Plan;
import com.tool.api.exception.BaseException;
import com.tool.api.service.PlanService;

import static com.tool.api.utils.responseSuccess.success;


@Controller
public class PlanController {
	@Autowired
	private PlanService planService;

	/*
	 *根据id查询用户的全部日记记录
	 * 当天之前的（不包括当天）
	 * */
	@RequestMapping(value = "/user/plans/all/before",  produces="application/json;charset=UTF-8")
	@ResponseBody
	public String planFindAllBefore(@ModelAttribute("id") String id, @ModelAttribute("pageSize") int pageSize) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		int uid = Integer.parseInt(id);
//		获取当前系统的日期
		java.util.Date datetime=new java.util.Date();
		java.sql.Date date=new Date(datetime.getTime());
		//分页返回数据，currentPage为返回页数下标，pageSize为返回页数条数
		PageHelper.startPage(1, pageSize*10);
		List<Plan> plan = planService.findPlanAllBefore(new Plan(uid,date));
//		加入转换的数据，类中所在的日期方法名,类的对象
		List<HashMap<String, Object>> list = ResponsePlanData.<Plan>responseData(plan,"getPlan_date",new Plan());
		return JSON.toJSONString(list);
	}
	/*
	 *根据id查询用户的全部日记记录
	 * 当天之后的（包括当天）
	 * */
	@RequestMapping(value = "/user/plans/all/after",  produces="application/json;charset=UTF-8")
	@ResponseBody
	public String planFindAllAfter(@ModelAttribute("id") String id, @ModelAttribute("pageSize") int pageSize) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		int uid = Integer.parseInt(id);
//		获取当前系统的日期
		java.util.Date datetime=new java.util.Date();
		java.sql.Date date=new Date(datetime.getTime());
		//分页返回数据，currentPage为返回页数下标，pageSize为返回页数条数
		PageHelper.startPage(1, pageSize*10);
		List<Plan> plan = planService.findPlanAllAfter(new Plan(uid,date));
//		加入转换的数据，类中所在的日期方法名,类的对象
		List<HashMap<String, Object>> list = ResponseData.<Plan>responseData(plan,"getPlan_date",new Plan());
		return JSON.toJSONString(list);
	}
	/*
	 *根据id查询用户的全部日记记录
	 * 当天的
	 * */
	@RequestMapping(value = "/user/plans/all/intraday", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String planFindAllIntraday(@ModelAttribute("id") String id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		int uid = Integer.parseInt(id);
//		获取当前系统的日期
		java.util.Date datetime=new java.util.Date();
		java.sql.Date date=new Date(datetime.getTime());
		List<Plan> plan = planService.findPlanAllIntraday(new Plan(uid,date));
//		加入转换的数据，类中所在的日期方法名,类的对象
		List<HashMap<String, Object>> list = ResponseData.<Plan>responseData(plan,"getPlan_date",new Plan());
		String str = JSON.toJSONString(list);
		str = str.substring(1, str.length() -1);
		return str;
	}
	/*
	 * 添加考研日记
	 */
	@RequestMapping(value = "/user/plans/add", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String addPlan(@ModelAttribute("id") String id,@ModelAttribute("user_plan_add") String user_plan_add) {
		int uid = Integer.parseInt(id);
		String plan_content = JSON.parseObject(user_plan_add).getString("plan_content");
		java.sql.Date plan_date = java.sql.Date.valueOf(JSON.parseObject(user_plan_add).getString("plan_date"));
		String plan_start_time = JSON.parseObject(user_plan_add).getString("plan_start_time");
		String plan_end_time = JSON.parseObject(user_plan_add).getString("plan_end_time");
		int plan_if_repeat = JSON.parseObject(user_plan_add).getInteger("plan_if_repeat");
		int plan_if_finish = JSON.parseObject(user_plan_add).getInteger("plan_if_finish");
//    	根据信息去添加相关计划
		planService.insertPlan(new Plan(uid,plan_content,plan_date,plan_start_time,plan_end_time,plan_if_repeat,plan_if_finish));
		return JSON.toJSONString(success("POST /user/plans/add"));
	}

	/*
	 * 修改考研计划
	 */
	@RequestMapping(value = "/user/plans/modify",  produces="application/json;charset=UTF-8")
	@ResponseBody
	public String modifyDiary(@ModelAttribute("id") String id,@ModelAttribute("user_plan_modify") String user_plan_modify) {
		int uid = Integer.parseInt(id);
		int plan_id=JSON.parseObject(user_plan_modify).getInteger("plan_id");
		String plan_content = JSON.parseObject(user_plan_modify).getString("plan_content");
		java.sql.Date plan_date = java.sql.Date.valueOf(JSON.parseObject(user_plan_modify).getString("plan_date"));
		String plan_start_time = JSON.parseObject(user_plan_modify).getString("plan_start_time");
		String plan_end_time = JSON.parseObject(user_plan_modify).getString("plan_end_time");
		int plan_if_repeat = JSON.parseObject(user_plan_modify).getInteger("plan_if_repeat");
		int plan_if_finish = JSON.parseObject(user_plan_modify).getInteger("plan_if_finish");
//    	根据信息去添加相关日记
		planService.updatePlan(new Plan(plan_id,uid,plan_content,plan_date,plan_start_time,plan_end_time,plan_if_repeat,plan_if_finish));
		return JSON.toJSONString(success("POST /user/plans/modify"));
	}
	

	/*
	 * 批量修改考研计划是否完成
	 */
	@RequestMapping(value = "/user/plans/batchmodify", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String batchModify(@ModelAttribute("id") String id, @ModelAttribute("user_plan_batch_modify") String batch_modify) throws Exception {
		int uid = Integer.parseInt(id);
		List<Object> listpid = JSON.parseObject(batch_modify).getJSONArray("plan_id");
		List<Object> listfinish = JSON.parseObject(batch_modify).getJSONArray("plan_if_finish");
		
		List<Plan> list = new ArrayList<Plan>();
		int sizepid = listpid.size();
		int sizefinish = listfinish.size();
		if(sizepid != sizefinish) {
			throw new BaseException("error_code:1000:msg:输入参数错误");
		}
		else {
			for(int i = 0; i < sizepid; i++) {
				int pid = (int) listpid.get(i);
				int pfinish = (int) listfinish.get(i);
				Plan plan = new Plan(pid, uid, pfinish);
				list.add(plan);
			}
			planService.batchUpdatePlan(list);
		}
		
		return JSON.toJSONString(success("POST /user/plans/batchmodify"));
	}

	/*
	 *根据id删除考研计划
	 */
	@RequestMapping(value = "/user/plans/delete", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteDiary(@RequestParam String id, @RequestParam String plan_id) throws Exception{
		int uid = Integer.parseInt(id);
		int pid = Integer.parseInt(plan_id);
//    	根据信息去删除相关日记
		int if_exist = planService.findPlanIdIfExist(pid);
		if(if_exist != 0) {
			planService.deletePlan(new Plan(pid,uid));
		} else {
			throw new BaseException("error_code:3000:msg:该用户在数据库中不存在");
		}
		return JSON.toJSONString(success("GET /user/plans/delete"));
	}
}
