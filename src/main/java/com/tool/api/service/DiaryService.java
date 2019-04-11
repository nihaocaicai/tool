package com.tool.api.service;

import java.util.Map;
import com.tool.api.entity.Diary;

public interface DiaryService {
	
	public Diary findOnlyDiaryById(Map<String, String> map);
	
	public Diary findDiaryById(String id);
	
    public void insertDiary(Diary diary);
    
    public void updateDiary(Diary diary);
    
    public void deleteDiary(Map<String, String> map);
}
