package com.tool.api.service.impl;

import java.util.Map;
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
	public Diary findDiaryById(String id) {
		// TODO Auto-generated method stub
		return this.diaryDao.findDiaryById(id);
	}

	@Override
	public Diary findOnlyDiaryById(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.diaryDao.findOnlyDiaryById(map);
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
	public void deleteDiary(Map<String, String> map) {
		// TODO Auto-generated method stub
		this.diaryDao.deleteDiary(map);
	}

}
