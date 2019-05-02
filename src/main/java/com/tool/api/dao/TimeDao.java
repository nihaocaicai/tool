package com.tool.api.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.tool.api.entity.Plan;

@Repository
public interface TimeDao {
	public List<Plan> findUid(@Param("date") String date);
}
