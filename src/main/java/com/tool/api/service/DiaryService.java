package com.tool.api.service;

import com.tool.api.entity.Diary;

public interface DiaryService {
	
	public Diary findDiaryByDiaryId(String diary_id);
	
	public Diary findDiaryByUserId(String user_id);
	
    public void insertDiary(Diary diary);
    
    public void updateDiary(Diary diary);
    
    public void deleteDiary(String diary_id);
}
