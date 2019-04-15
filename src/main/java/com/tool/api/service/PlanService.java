package com.tool.api.service;

import com.tool.api.entity.Plan;

public interface PlanService {
	public Plan findPlanByPlanId(String plan_id);
	
	public Plan findPlanById(String id);
	
    public void insertPlan(Plan plan);
    
    public void updatePlan(Plan plan);
    
    public void deletePlan(String plan_id);
}
