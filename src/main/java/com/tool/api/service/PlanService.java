package com.tool.api.service;

import java.util.List;
import com.tool.api.entity.Plan;

public interface PlanService {
	
	public int findPlanIdIfExist(int plan_id);
	
	public List<Plan> findPlanAllBefore(Plan plan);

	public List<Plan> findPlanAllAfter(Plan plan);

	public List<Plan> findPlanAllIntraday(Plan plan);

	public void insertPlan(Plan plan);

	public void updatePlan(Plan plan);
	
	public void batchUpdatePlan(List<Plan> plans);

	public void deletePlan(Plan plan);


}
