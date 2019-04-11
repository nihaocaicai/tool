package com.tool.api.dao;

import java.util.Map;

import com.tool.api.entity.Datetime;
/*
 * Time接口文件
 */

public interface TimeDao {
	/*
	 * 根据id查询用户全部时间进度安排信息
	 */
	public Datetime findOnlyTimeById(Map<String, String> map);
	
   /*
    * 根据id查询用户全部时间进度安排信息
    */
    public Datetime findTimeById(String id);
    
    /*
     * 新增某用户的考试安排
     */
    public void insertTime(Datetime time);
    
    /*
     * 更新用户考试安排
     */
    public void updateTime(Datetime time);
    
    /*
     * 删除某用户的考试安排
     */
    public void deleteTime(Map<String, String> map);
}
