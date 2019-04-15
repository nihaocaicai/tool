package com.tool.api.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tool.api.entity.Diary;
import com.tool.api.service.DiaryService;

@Controller
public class DiaryController {
    @Autowired
    private DiaryService diaryService;
    
    /*
     * 每次调用目标方法之前都会执行它
     * 先从数据库获取原始数据，再将新的参数直接替代原来的参数，若没有原始参数对应的新参数，则不作修改
     * map将修改过后的User返回给目标方法
     */
    @ModelAttribute
    public void getDiary(@RequestParam(value = "diary_id", required = false) String diary_id, Map<String, Object> map) {
    	//查询数据库原始记录
    	Diary diary= diaryService.findDiaryByDiaryId(diary_id);
    	System.out.println("从数据库中取出一个对象：" + diary);
    	/* 
    	 * 判断条件针对数据库插入数据而设
    	 * 由于数据库开始肯定不存在这个user_id，所以返回必定为空，若强行put进去，则调用插入方法时候会NullPointException
    	 */
    	if(diary == null) {
    		System.out.println("没有对象");
    	}
    	else {
    		map.put("diary", diary);
    	}
    }

    /*
    *根据id查询用户的日记记录
    *测试用例：http://localhost:8080/tool/findDiaryById?id=abcddsssagafafa
    * */ 
    @RequestMapping("/findDiaryByUserId")
    public String findDiaryByUserId(String user_id, Model model) {
    	Diary diary = diaryService.findDiaryByUserId(user_id);
        model.addAttribute("diary", diary);
    	return "diary";
    }
    
    /*
     * 插入一条某用户日记记录
     * http://localhost:8080/tool/insertDiary?user_id=abcabc&diary_title=第二篇&diary_content=今天复习政治&diary_write_time=2019-6-30%206:31:06&diary_write_place=综合楼
     */
    @RequestMapping("/insertDiary")
    public String insertDiary(String user_id, String diary_title, String diary_content,
    						  String diary_write_time, String diary_write_place, Model model) throws ParseException {  
    	System.out.println("userid:" + user_id);
    	System.out.println(diary_content);
    	System.out.println(diary_write_time);
    	System.out.println(diary_write_place);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date diaryWriteTime = format.parse(diary_write_time);
		Diary diary = new Diary(user_id, diary_title, diary_content, diaryWriteTime, diary_write_place);
		diaryService.insertDiary(diary);
    	return "success";
    }
    
    /*
          * 目标方法获取根据表单修改过后的Diary -> @ModelAttribute
     * 更新数据库某个用户记录
     * http://localhost:8080/tool/updateDiary?diary_id=2&diary_title=第二篇&diary_content=今天复习政治
     */
    @RequestMapping("/updateDiary")
    public String updateDiary(Diary diary) {
    	System.out.println(diary);
    	diaryService.updateDiary(diary);
    	return "success";
    }
    
    /*
     * 删除某用户一条日记记录
     * http://localhost:8080/tool/deleteDiary?diary_id=6
     */
    @RequestMapping("/deleteDiary")
    public String deleteDiary(@RequestParam(value = "diary_id") String diary_id) {
    	diaryService.deleteDiary(diary_id);
    	return "success";
    }
}
