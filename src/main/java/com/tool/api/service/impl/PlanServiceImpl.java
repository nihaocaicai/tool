package com.tool.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.api.dao.PlanDao;
import com.tool.api.entity.Plan;
import com.tool.api.service.PlanService;
import com.tool.mapperClass.FindPlanByIdAndDate;

@Service
@Transactional
public class PlanServiceImpl implements PlanService{

	@Autowired
	PlanDao planDao;

	@Override
	public List<FindPlanByIdAndDate> findPlanByIdAndDate(String id, String date) {
		// TODO Auto-generated method stub
		//存储新数据的临时表List<FindPlanByIdAndDate> 
		List<FindPlanByIdAndDate> pList = new ArrayList<>();
		//获取查出来的数据List<Plan>
		List<Plan> plans = planDao.findPlanByIdAndDate(id, date);
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
