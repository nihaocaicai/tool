package com.tool.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.api.dao.ErrorDao;
import com.tool.api.entity.Error;
import com.tool.api.service.ErrorService;

@Service
@Transactional
public class ErrorServiceImpl implements ErrorService{

	@Autowired
	ErrorDao errorDao;
	
	@Override
	public Error findErrorById(String id) {
		// TODO Auto-generated method stub
		return this.errorDao.findErrorById(id);
	}

}
