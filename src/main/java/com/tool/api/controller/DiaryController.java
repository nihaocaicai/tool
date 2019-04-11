package com.tool.api.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void getTime(@RequestParam(value = "user_id", required = false) String user_id,
    					@RequestParam(value = "diary_date", required = false) String diary_date, Map<String, Object> map) {
    	Map<String, String> map1 = new HashMap<>();
    	System.out.println(user_id);
    	System.out.println(diary_date);
    	map1.put("user_id", user_id);
    	map1.put("diary_date", diary_date);
    	//查询数据库原始记录
    	Diary diary= diaryService.findOnlyDiaryById(map1);
    	System.out.println("从数据库中取出一个对象：" + diary);
    	/* 
    	 * 判断条件针对数据库插入数据而设
    	 * 由于数据库开始肯定不存在这个user_id，所以返回必定为空，若强行put进去，则调用插入方法时候会NullPointException
    	 */
    	if(diary == null) {
    		System.out.println("没有对象");
    	}
    	else {
    		map.put("plan", diary);
    	}
    }

    /*
    *根据id查询用户的日记记录
    *测试用例：http://localhost:8080/tool/findDiaryById?id=abcddsssagafafa
    * */ 
    @RequestMapping("/findDiaryById")
    public String findDiaryById(String id, Model model) {
    	Diary diary = diaryService.findDiaryById(id);
        model.addAttribute("diary", diary);
    	return "diary";
    }
    
    /*
     * 插入一条某用户日记记录
     * http://localhost:8080/tool/insertDiary?user_id=abcabc&diary_content=今天我要学完高数第一章&diary_date=2019-04-11
     */
    @RequestMapping("/insertDiary")
    public String insertDiary(String user_id, String diary_content, String diary_date, Model model) throws ParseException {
    	try {
			// 将前端传过来的字符串进行转码，因为http请求默认编码为ISO-8859-1，若不转码，该参数会乱码
			user_id = new String(user_id.getBytes("ISO-8859-1"), "UTF-8");
			diary_content = new String(diary_content.getBytes("ISO-8859-1"), "UTF-8");
			diary_date = new String(diary_date.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		java.sql.Date date = java.sql.Date.valueOf(diary_date);
		Diary diary = new Diary(user_id, diary_content, date);
		diaryService.insertDiary(diary);
    	return "success";
    }
    
    /*
          * 目标方法获取根据表单修改过后的Diary -> @ModelAttribute
     * 更新数据库某个用户记录
     * http://localhost:8080/tool/updateDiary?user_id=abc&diary_content=XXXWxxWWX&diary_date=2019-04-11
     */
    @RequestMapping("/updateDiary")
    public String updateDiary(Diary diary) {
    	System.out.println(diary);
    	diaryService.updateDiary(diary);
    	return "success";
    }
    
    /*
     * 删除某用户一条日记记录
     * http://localhost:8080/tool/deleteDiary?user_id=abcabc&diary_date=2019-04-11
     */
    @RequestMapping("/deleteDiary")
    public String deleteDiary(@RequestParam(value = "user_id") String user_id,
    						 @RequestParam(value = "diary_date") String diary_date) {
    	Map<String, String> map = new HashMap<>();
    	map.put("user_id", user_id);
    	map.put("diary_date", diary_date);
    	diaryService.deleteDiary(map);
    	return "success";
    }
}
