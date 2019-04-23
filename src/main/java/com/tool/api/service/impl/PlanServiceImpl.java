package com.tool.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.api.dao.PlanDao;
import com.tool.api.entity.Plan;
import com.tool.api.service.PlanService;
import com.tool.mapperClass.FindPlanAllAfter;
import com.tool.mapperClass.FindPlanByIdAndDate;

@Service
@Transactional
public class PlanServiceImpl implements PlanService{

	@Autowired
	PlanDao planDao;

	/*
     * 根据id和时间date查询用户当天计划安排信息
     */
	@Override
	public List<FindPlanByIdAndDate> findPlanAllIntraday(String id, String date) {
		// TODO Auto-generated method stub
		//存储新数据的临时表List<FindPlanByIdAndDate> 
		List<FindPlanByIdAndDate> pList = new ArrayList<>();
		//获取查出来的数据List<Plan>
		List<Plan> plans = planDao.findPlanAllIntraday(id, date);
		//格式化所需要的时间格式
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		for(Plan p: plans) {
			String plan_id = String.valueOf(p.getPlan_id());
			String plan_content = p.getPlan_content();
			String plan_start_time = "";
			if(p.getPlan_start_time() != null) {
				plan_start_time = format.format(p.getPlan_start_time());
			}
			String plan_end_time = "";
			if(p.getPlan_end_time() != null) {
				plan_end_time = format.format(p.getPlan_end_time());
			}
			String plan_if_repeat = (p.getPlan_if_repeat() == 1 ? "true" : "false");
			String plan_if_prompt = (p.getPlan_if_prompt() == 1 ? "true" : "false");
			String plan_if_prompt_time = "";
			if(p.getPlan_if_prompt_time() != null) {
				plan_if_prompt_time = format.format(p.getPlan_if_prompt_time());
			}
			String plan_if_finish = (p.getPlan_if_finish() == 1 ? "true" : "false");
			//新建一个FindPlanByIdAndDate，存储数据
			FindPlanByIdAndDate fDate = new FindPlanByIdAndDate(plan_id, plan_content, plan_start_time, plan_end_time, plan_if_repeat, plan_if_prompt, plan_if_prompt_time, plan_if_finish, "0");
			//添加进去List<FindPlanByIdAndDate>中
			pList.add(fDate);
		}
		return pList;
	}
	
	/*
     * 根据id和时间date查询用户该日期前所有计划安排信息
     */
	@Override
	public List<FindPlanAllAfter> findPlanAllBefore(String id, String date) {
		// TODO Auto-generated method stub
		//数据库取出数据，返回成为list
		List<Plan> plans = planDao.findPlanAllBefore(id, date);
		/*
		 * key：date value:List
		 */
		Map<String, List<FindPlanByIdAndDate>> map = new HashMap<>();
		//作为map的临时list:tempPlan，put到map中
		List<FindPlanByIdAndDate> tempPlan = new ArrayList<>();
		//所需时间格式定义
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
		//初始化时间为传入的时间date
		String Date = date;
		int listsize = plans.size();
		//遍历该list:plans
		for(int i = 0; i < listsize; i++) {
			Plan p = plans.get(i);
			//获取Plan中需要的部分数据，并更改格式
			String tempdate = format.format(p.getPlan_start_time());
			String plan_id = String.valueOf(p.getPlan_id());
			String plan_content = p.getPlan_content();
			String plan_start_time = "";
			if(p.getPlan_start_time() != null) {
				plan_start_time = format1.format(p.getPlan_start_time());
			}
			String plan_end_time = "";
			if(p.getPlan_end_time() != null) {
				plan_end_time = format1.format(p.getPlan_end_time());
			}
			String plan_if_repeat = (p.getPlan_if_repeat() == 1 ? "true" : "false");
			String plan_if_prompt = (p.getPlan_if_prompt() == 1 ? "true" : "false");
			String plan_if_prompt_time = "";
			if(p.getPlan_if_prompt_time() != null) {
				plan_if_prompt_time = format.format(p.getPlan_if_prompt_time());
			}
			String plan_if_finish = (p.getPlan_if_finish() == 1 ? "true" : "false");
			//新建一个FindPlanByIdAndDate，存储数据上面数据
			FindPlanByIdAndDate fDate = new FindPlanByIdAndDate(plan_id, plan_content, plan_start_time, plan_end_time, plan_if_repeat, plan_if_prompt, plan_if_prompt_time, plan_if_finish, "0");
			//假如Date等于当前Plan时间，则将该FindPlanByIdAndDate 添加进list：tempPlan中
			if(tempdate.equals(Date)) {
				tempPlan.add(fDate);
				if(i == listsize-1) {
					map.put(Date, tempPlan);
				}
			}
			else { 
				//假如Date为当天时间，则将Date指向当前Plan的时间
				if(Date == null) {
					Date = tempdate;
					tempPlan.add(fDate);
				}
				else {
				//否则将list：tempPlan放进map中，键为当前时间，值为list：tempPlan
				map.put(Date, tempPlan);
				//更新Date为当前Plan的时间
				Date = tempdate;
				//新建一个list：tempPlan，存放下一个内容
				tempPlan = new ArrayList<>();
				//将当前的FindPlanByIdAndDate添加进list：tempPlan
				tempPlan.add(fDate);
				//如果是最后一条数据，则直接把该list：tempPlan添加进map中
				if(i == listsize-1) {
					map.put(Date, tempPlan);
				}
				}
			}
		}
		//新建一个list，作为返回值
		List<FindPlanAllAfter> list = new ArrayList<>();
		for(Map.Entry<String, List<FindPlanByIdAndDate>> entry: map.entrySet()) {
			String tempDate = entry.getKey();
			List<FindPlanByIdAndDate> tempData = entry.getValue();
			FindPlanAllAfter fAfter = new FindPlanAllAfter(tempDate, tempData);
			list.add(fAfter);
		}
		return list;
	}

	/*
     * 根据id和时间date查询用户该日期后所有计划安排信息
     */
	@Override
	public List<FindPlanAllAfter> findPlanAllAfter(String id, String date) {
		// TODO Auto-generated method stub
		//数据库取出数据，返回成为list
		List<Plan> plans = planDao.findPlanAllAfter(id, date);
		/*
		 * key：date value:List
		 */
		Map<String, List<FindPlanByIdAndDate>> map = new HashMap<>();
		//作为map的临时list:tempPlan，put到map中
		List<FindPlanByIdAndDate> tempPlan = new ArrayList<>();
		//所需时间格式定义
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
		//初始化时间为传入的时间date
		String Date = date;
		int listsize = plans.size();
		//遍历该list:plans
		for(int i = 0; i < listsize; i++) {
			Plan p = plans.get(i);
			//获取Plan中需要的部分数据，并更改格式
			String tempdate = format.format(p.getPlan_start_time());
			String plan_id = String.valueOf(p.getPlan_id());
			String plan_content = p.getPlan_content();
			String plan_start_time = "";
			if(p.getPlan_start_time() != null) {
				plan_start_time = format1.format(p.getPlan_start_time());
			}
			String plan_end_time = "";
			if(p.getPlan_end_time() != null) {
				plan_end_time = format1.format(p.getPlan_end_time());
			}
			String plan_if_repeat = (p.getPlan_if_repeat() == 1 ? "true" : "false");
			String plan_if_prompt = (p.getPlan_if_prompt() == 1 ? "true" : "false");
			String plan_if_prompt_time = "";
			if(p.getPlan_if_prompt_time() != null) {
				plan_if_prompt_time = format.format(p.getPlan_if_prompt_time());
			}
			String plan_if_finish = (p.getPlan_if_finish() == 1 ? "true" : "false");
			//新建一个FindPlanByIdAndDate，存储数据上面数据
			FindPlanByIdAndDate fDate = new FindPlanByIdAndDate(plan_id, plan_content, plan_start_time, plan_end_time, plan_if_repeat, plan_if_prompt, plan_if_prompt_time, plan_if_finish, "0");
			//假如Date等于当前Plan时间，则将该FindPlanByIdAndDate 添加进list：tempPlan中
			if(tempdate.equals(Date)) {
				tempPlan.add(fDate);
				if(i == listsize-1) {
					map.put(Date, tempPlan);
				}
			}
			else { 
				//假如Date为当天时间，则将Date指向当前Plan的时间
				if(Date == null) {
					Date = tempdate;
					tempPlan.add(fDate);
				}
				else {
				//否则将list：tempPlan放进map中，键为当前时间，值为list：tempPlan
				map.put(Date, tempPlan);
				//更新Date为当前Plan的时间
				Date = tempdate;
				//新建一个list：tempPlan，存放下一个内容
				tempPlan = new ArrayList<>();
				//将当前的FindPlanByIdAndDate添加进list：tempPlan
				tempPlan.add(fDate);
				//如果是最后一条数据，则直接把该list：tempPlan添加进map中
				if(i == listsize-1) {
					map.put(Date, tempPlan);
				}
				}
			}
		}
		//新建一个list，作为返回值
		List<FindPlanAllAfter> list = new ArrayList<>();
		for(Map.Entry<String, List<FindPlanByIdAndDate>> entry: map.entrySet()) {
			String tempDate = entry.getKey();
			List<FindPlanByIdAndDate> tempData = entry.getValue();
			FindPlanAllAfter fAfter = new FindPlanAllAfter(tempDate, tempData);
			list.add(fAfter);
		}
		return list;
	}

	@Override
	public Plan findPlanByPlanId(String plan_id) {
		// TODO Auto-generated method stub
		return this.planDao.findPlanByPlanId(plan_id);
	}

	@Override
	public void insertPlan(Plan plan) {
		// TODO Auto-generated method stub
		this.planDao.insertPlan(plan);
	}

	@Override
	public void updatePlan(Plan plan) {
		// TODO Auto-generated method stub
		this.planDao.updatePlan(plan);
	}

	@Override
	public void deletePlan(String plan_id) {
		// TODO Auto-generated method stub
		this.planDao.deletePlan(plan_id);
	}

}
