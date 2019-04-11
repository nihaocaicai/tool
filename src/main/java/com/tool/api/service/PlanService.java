package com.tool.api.service;

import java.util.Map;
import com.tool.api.entity.Plan;

public interface PlanService {
	public Plan findOnlyPlanById(Map<String, String> map);
	
	public Plan findPlanById(String id);
	
    public void insertPlan(Plan plan);
    
    public void updatePlan(Plan plan);
    
    public void deletePlan(Map<String, String> map);
}
