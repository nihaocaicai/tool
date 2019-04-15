package com.tool.api.dao;

import java.util.Map;
import com.tool.api.entity.Arrangement;
/*
 * Time接口文件
 */

public interface ArrangementDao {
	/*
	 * 根据id查询用户精确时间进度安排信息
	 */
	public Arrangement findArrangeByArrangeId(String arrange_id);
	
   /*
    * 根据id查询用户全部时间进度安排信息
    */
    public Arrangement findArrangeByUserId(String user_id);
    
    /*
     * 新增某用户的考试安排
     */
    public void insertArrange(Arrangement arrangement);
    
    /*
     * 更新用户考试安排
     */
    public void updateArrange(Arrangement arrangement);
    
    /*
     * 删除某用户的考试安排
     */
    public void deleteArrange(String arrange_id);
}
