package com.tool.api.dao;

import java.sql.Date;
import java.util.List;
import com.tool.api.entity.Plan;

public interface PlanDao {
    /*
     * 根据id和时间date查询用户该日期前所有计划安排信息
     */
    public List<Plan> findPlanAllBefore(Plan plan);

    /*
     * 根据id和时间date查询用户该日期后所有计划安排信息
     */
    public List<Plan> findPlanAllAfter(Plan plan);

   /*
    * 根据id和时间date查询用户当天计划安排信息
    */
    public List<Plan> findPlanAllIntraday(Plan plan);

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
    public void deletePlan(Plan plan);

//    /*
//     * 根据id和时间查询用户全部计划进度安排信息
//     */
//    public Plan findPlanByPlanId(String plan_id);
//
}
