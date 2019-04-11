package com.tool.api.dao;

import com.tool.api.entity.Error;

public interface ErrorDao {
	public Error findErrorById(String id);
}
