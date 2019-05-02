package com.tool.api.service;

import java.util.List;

import com.tool.api.entity.Arrangement;
import com.tool.api.entity.Plan;

public interface TimeService {
	public List<Plan> FindUid(String date);
}
