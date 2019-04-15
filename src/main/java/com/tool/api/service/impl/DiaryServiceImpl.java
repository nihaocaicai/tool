package com.tool.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.api.dao.DiaryDao;
import com.tool.api.entity.Diary;
import com.tool.api.service.DiaryService;

@Service
@Transactional
public class DiaryServiceImpl implements DiaryService{

	@Autowired
	DiaryDao diaryDao;
	
	@Override
	public Diary findDiaryByUserId(String user_id) {
		// TODO Auto-generated method stub
		return this.diaryDao.findDiaryByUserId(user_id);
	}

	@Override
	public Diary findDiaryByDiaryId(String diary_id) {
		// TODO Auto-generated method stub
		return this.diaryDao.findDiaryByDiaryId(diary_id);
	}

	@Override
	public void insertDiary(Diary diary) {
		// TODO Auto-generated method stub
		this.diaryDao.insertDiary(diary);
	}

	@Override
	public void updateDiary(Diary diary) {
		// TODO Auto-generated method stub
		this.diaryDao.updateDiary(diary);
	}

	@Override
	public void deleteDiary(String diary_id) {
		// TODO Auto-generated method stub
		this.diaryDao.deleteDiary(diary_id);
	}

}
