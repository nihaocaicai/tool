package com.tool.api.service;

import com.tool.api.entity.Diary;

import java.util.List;


public interface DiaryService {
    //    查询用户全部考研日记信息
    public List<Diary> findDiaryById(int id);
//    添加考研日记
    public void insertDiary(Diary diary);
//    更新考研日记
    public void updateDiary(Diary diary);
//    删除一条考研日记
    public void deleteDiary(Diary diary);
	
//	public Diary findDiaryByDiaryId(String diary_id);
//
//
}
