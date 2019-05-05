package com.tool.api.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tool.api.dao.TimeDao;
import com.tool.api.entity.Arrangement;
import com.tool.api.service.TimeService;

@Service
public class TimeServiceImpl implements TimeService{

	@Autowired
	TimeDao timeDao;
	
	@Override
	public List<Arrangement> FindUid(String date, String hour) {
		// TODO Auto-generated method stub
		System.out.println("现在时间：" + date);
		System.out.println("现在几时：" + hour);
		List<Arrangement> list = timeDao.findUid(date, hour);
		System.out.println(list);
		return list;
	}

}
