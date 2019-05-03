package com.tool.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.api.dao.DiaryDao;
import com.tool.api.entity.Diary;
import com.tool.api.service.DiaryService;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class DiaryServiceImpl implements DiaryService{

	@Autowired
	DiaryDao diaryDao;
	//    查询用户全部考研日记信息
	public List<Diary> findDiaryById(int id) {
		return this.diaryDao.findDiaryById(id);
	}
//	添加考研日记
	public void insertDiary(Diary diary) {
		this.diaryDao.insertDiary(diary);
	}
//	更新考研日记
	public void updateDiary(Diary diary) {
		this.diaryDao.updateDiary(diary);
	}
//	删除一条考研日记
	public void deleteDiary(Diary diary) {
		this.diaryDao.deleteDiary(diary);
	}
//
//	@Override
//	public Diary findDiaryByDiaryId(String diary_id) {
//		// TODO Auto-generated method stub
//		return this.diaryDao.findDiaryByDiaryId(diary_id);
//	}
//
//

}
