package com.tool.api.service.impl;

import java.sql.Date;
import java.util.List;

import com.tool.api.entity.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.api.dao.PlanDao;
import com.tool.api.service.PlanService;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanDao planDao;

	@Override
	public List<Plan> findPlanAllBefore(Plan plan) {
		return planDao.findPlanAllBefore(plan);
	}

	@Override
	public List<Plan> findPlanAllAfter(Plan plan) {
		return planDao.findPlanAllAfter(plan);
	}

	@Override
	public List<Plan> findPlanAllIntraday(Plan plan) {
		return planDao.findPlanAllIntraday(plan);
	}

	@Override
	public void insertPlan(Plan plan) {
		planDao.insertPlan(plan);
	}

	@Override
	public void updatePlan(Plan plan) {
		planDao.updatePlan(plan);
	}

	@Override
	public void batchUpdatePlan(List<Plan> plans) {
		// TODO Auto-generated method stub
		planDao.batchUpdatePlan(plans);
	}

	@Override
	public void deletePlan(Plan plan) {
		planDao.deletePlan(plan);
	}
}
