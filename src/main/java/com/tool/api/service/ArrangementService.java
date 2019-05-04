package com.tool.api.service;

import com.tool.api.entity.Arrangement;

import java.util.List;

public interface ArrangementService {
	public List<Arrangement> findArrangeByUserId(int user_id);

	public void insertArrange(Arrangement arrangement);

	public void updateArrange(Arrangement arrangement);

	public void deleteArrange(Arrangement arrangement);


    //	public Arrangement findArrangeByArrangeId(String arrange_id);

//
//
}
