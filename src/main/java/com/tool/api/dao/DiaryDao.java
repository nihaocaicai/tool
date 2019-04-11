package com.tool.api.dao;

import java.util.Map;
import com.tool.api.entity.Diary;

public interface DiaryDao {
	public Diary findDiaryById(String id);
	
	/*
	 * 根据id和时间查询用户特定日记
	 */
	public Diary findOnlyDiaryById(Map<String, String> map);
	
   /*
    * 根据id查询用户全部日记
    */
    public Diary findDiaryById(Diary diary);
    
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
    public void deleteDiary(Map<String, String> map);
}
