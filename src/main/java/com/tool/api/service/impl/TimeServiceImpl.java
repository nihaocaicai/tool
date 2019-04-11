package com.tool.api.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tool.api.dao.TimeDao;
import com.tool.api.entity.Datetime;
import com.tool.api.service.TimeService;

@Service
@Transactional
public class TimeServiceImpl implements TimeService{

	@Autowired
	TimeDao timeDao;
	
	//获取某用户特定时间安排
	@Override
	public Datetime findOnlyTimeById(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.timeDao.findOnlyTimeById(map);
	}
	
	//获取某用户全部时间安排
	@Override
	public Datetime findTimeById(String id) {
		// TODO Auto-generated method stub
		return this.timeDao.findTimeById(id);
	}

	//新增某用户时间安排
	@Override
	public void insertTime(Datetime time) {
		// TODO Auto-generated method stub
		this.timeDao.insertTime(time);
	}

	//更新某用户时间安排
	@Override
	public void updateTime(Datetime time) {
		// TODO Auto-generated method stub
		this.timeDao.updateTime(time);
	}

	//删除某用户时间安排
	@Override
	public void deleteTime(Map<String, String> map) {
		// TODO Auto-generated method stub
		this.timeDao.deleteTime(map);
	}

}
