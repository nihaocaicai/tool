package com.tool.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tool.api.dao.TimeDao;
import com.tool.api.entity.Arrangement;
import com.tool.api.entity.Plan;
import com.tool.api.service.TimeService;

@Service
public class TimeServiceImpl implements TimeService{

	@Autowired
	TimeDao timeDao;
	
	@Override
	public List<Plan> FindUid(String date) {
		// TODO Auto-generated method stub
		System.out.println("现在时间：" + date);
		List<Plan> list = timeDao.findUid(date);
		System.out.println(list);
		return list;
	}

}
