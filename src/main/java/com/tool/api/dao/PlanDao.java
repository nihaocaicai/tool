package com.tool.api.dao;
import com.tool.api.entity.Plan;

public interface PlanDao {
	
	/*
	 * 根据id和时间查询用户全部计划进度安排信息
	 */
	public Plan findPlanByPlanId(String plan_id);
	
   /*
    * 根据id查询用户全部计划安排信息
    */
    public Plan findPlanById(String id);
    
    /*
     * 新增某用户的计划安排
     */
    public void insertPlan(Plan plan);
    
    /*
     * 更新用户计划安排
     */
    public void updatePlan(Plan plan);
    
    /*
     * 删除某用户的计划安排
     */
    public void deletePlan(String plan_id);
}
