package com.tool.api.service;

import java.util.Map;

import com.tool.api.entity.Datetime;

public interface TimeService {
	public Datetime findOnlyTimeById(Map<String, String> map);
	
	public Datetime findTimeById(String id);
	
    public void insertTime(Datetime time);
    
    public void updateTime(Datetime time);
    
    public void deleteTime(Map<String, String> map);
}
