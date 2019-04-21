package com.tool.api.service;

import java.util.List;
import java.util.Map;

import com.tool.api.entity.Plan;
import com.tool.mapperClass.FindPlanAllAfter;
import com.tool.mapperClass.FindPlanByIdAndDate;

public interface PlanService {
	public Plan findPlanByPlanId(String plan_id);
	
	public List<FindPlanByIdAndDate> findPlanAllIntraday(String id, String date);

	public List<FindPlanAllAfter> findPlanAllBefore(String id, String date);
	
	public List<FindPlanAllAfter> findPlanAllAfter(String id, String date);
	
    public void insertPlan(Plan plan);
    
    public void updatePlan(Plan plan);
    
    public void deletePlan(String plan_id);
}
