package com.tool.api.dao;

import com.tool.api.entity.Diary;

import java.util.List;

public interface DiaryDao {
//    查询用户全部考研日记信息
	public List<Diary> findDiaryById(int id);

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
    public void deleteDiary(Diary diary);

//	/*
//	 * 根据id和时间查询用户特定日记
//	 */
//	public Diary findDiaryByDiaryId(String diary_id);
//
//

}
