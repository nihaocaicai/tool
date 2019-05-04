package com.tool.api.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.api.dao.ArrangementDao;
import com.tool.api.entity.Arrangement;
import com.tool.api.service.ArrangementService;

@Service
@Transactional
public class ArrangementServiceImpl implements ArrangementService{

	@Autowired
	ArrangementDao arrangementDao;

	//获取某用户全部时间安排
	@Override
	public List<Arrangement> findArrangeByUserId(int user_id) {
		return this.arrangementDao.findArrangeByUserId(user_id);
	}
	//新增某用户时间安排
	@Override
	public void insertArrange(Arrangement arrangement) {
		this.arrangementDao.insertArrange(arrangement);
	}

	//更新某用户时间安排
	@Override
	public void updateArrange(Arrangement arrangement) {
		this.arrangementDao.updateArrange(arrangement);
	}

	//删除某用户时间安排
	@Override
	public void deleteArrange(Arrangement arrangement) {
		this.arrangementDao.deleteArrange(arrangement);
	}

//	//获取某用户特定时间安排
//	@Override
//	public Arrangement findArrangeByArrangeId(String arrange_id) {
//		// TODO Auto-generated method stub
//		return this.arrangementDao.findArrangeByArrangeId(arrange_id);
//	}
//
//

}
