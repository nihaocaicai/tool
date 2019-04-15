package com.tool.api.dao;

import com.tool.api.entity.Diary;

public interface DiaryDao {
	public Diary findDiaryByUserId(String user_id);
	
	/*
	 * 根据id和时间查询用户特定日记
	 */
	public Diary findDiaryByDiaryId(String diary_id);
	
    /*
     * 新增某用户的日记
     */
    public void insertDiary(Diary diary);
    
    /*
     * 更新用户某条日记记录
     */
    public void updateDiary(Diary diary);
    
    /*
     * 删除某用户的日记记录
     */
    public void deleteDiary(String diary_id);
}
