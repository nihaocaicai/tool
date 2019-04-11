package com.tool.api.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.api.dao.PlanDao;
import com.tool.api.entity.Plan;
import com.tool.api.service.PlanService;

@Service
@Transactional
public class PlanServiceImpl implements PlanService{

	@Autowired
	PlanDao planDao;

	@Override
	public Plan findPlanById(String id) {
		// TODO Auto-generated method stub
		return this.planDao.findPlanById(id);
	}

	@Override
	public Plan findOnlyPlanById(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.planDao.findOnlyPlanById(map);
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
	public void deletePlan(Map<String, String> map) {
		// TODO Auto-generated method stub
		this.planDao.deletePlan(map);
	}

}
