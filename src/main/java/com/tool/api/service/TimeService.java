package com.tool.api.service;

import java.util.List;
import com.tool.api.entity.Arrangement;

public interface TimeService {
	public List<Arrangement> FindUid(String date, String hour);
}
