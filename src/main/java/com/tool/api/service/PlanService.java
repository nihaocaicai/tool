package com.tool.api.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.tool.api.entity.Plan;
import com.tool.mapperClass.FindPlanAllAfter;
import com.tool.mapperClass.FindPlanByIdAndDate;

public interface PlanService {
	public List<Plan> findPlanAllBefore(Plan plan);

	public List<Plan> findPlanAllAfter(Plan plan);

	public List<Plan> findPlanAllIntraday(Plan plan);

	public void insertPlan(Plan plan);

	public void updatePlan(Plan plan);

	public void deletePlan(Plan plan);

//	public Plan findPlanByPlanId(String plan_id);

}
