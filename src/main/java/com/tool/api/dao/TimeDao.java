package com.tool.api.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.tool.api.entity.Arrangement;

@Repository
public interface TimeDao {
	public List<Arrangement> findUid(@Param("date") String date, @Param("hour") String hour);
}
