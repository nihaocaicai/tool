package com.tool.api.service;

import com.tool.api.entity.Arrangement;

public interface ArrangementService {
	public Arrangement findArrangeByArrangeId(String arrange_id);
	
	public Arrangement findArrangeByUserId(String user_id);
	
    public void insertArrange(Arrangement arrangement);
    
    public void updateArrange(Arrangement arrangement);
    
    public void deleteArrange(String arrange_id);
}
