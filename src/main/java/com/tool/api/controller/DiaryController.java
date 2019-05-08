package com.tool.api.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.tool.api.utils.responseDataUtils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.tool.api.entity.Diary;
import com.tool.api.service.DiaryService;

import static com.tool.api.utils.responseSuccess.success;

@Controller
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    /*
    *根据id查询用户的全部日记记录
    * */ 
    @RequestMapping(value = "/user/diarys/all",  produces="application/json;charset=UTF-8")
	@ResponseBody
	public String findUserById(String id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		int uid = Integer.parseInt(id);
		List<Diary> diary = diaryService.findDiaryById(uid);
//		加入转换的数据，类中所在的日期方法名,类的对象
		List<HashMap<String, Object>> list = ResponseData.<Diary>responseData(diary,"getDiary_write_date",new Diary());
		return JSON.toJSONString(list);
	}
	/*
	 * 添加考研日记
	 */
	@RequestMapping(value = "/user/diarys/add", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String addDiary(@ModelAttribute("id") String id,@ModelAttribute("user_diary_add") String user_diary_add) {
//        System.out.println(id);
//        System.out.println(user_info);
		int uid = Integer.parseInt(id);
		String diary_title = JSON.parseObject(user_diary_add).getString("diary_title");
		String diary_content = JSON.parseObject(user_diary_add).getString("diary_content");
		String diary_write_time = JSON.parseObject(user_diary_add).getString("diary_write_time");
		String diary_write_place = JSON.parseObject(user_diary_add).getString("diary_write_place");
		Date diary_write_date = Date.valueOf(JSON.parseObject(user_diary_add).getString("diary_write_date"));
//        System.out.println(Date.valueOf(user_brithday));
//    	根据信息去添加相关日记
		diaryService.insertDiary(new Diary(uid,diary_title,diary_content,diary_write_date,diary_write_time,diary_write_place));
		return JSON.toJSONString(success("POST /user/diarys/add"));
	}

	/*
	 * 修改考研日记
	 */
	@RequestMapping(value = "/user/diarys/modify", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String modifyDiary(@ModelAttribute("id") String id,@ModelAttribute("user_diary_modify") String user_diary_modify) {
		int uid = Integer.parseInt(id);
		int diary_id=JSON.parseObject(user_diary_modify).getInteger("diary_id");
		String diary_title = JSON.parseObject(user_diary_modify).getString("diary_title");
		String diary_content = JSON.parseObject(user_diary_modify).getString("diary_content");
		String diary_write_time = JSON.parseObject(user_diary_modify).getString("diary_write_time");
		String diary_write_place = JSON.parseObject(user_diary_modify).getString("diary_write_place");
		Date diary_write_date = Date.valueOf(JSON.parseObject(user_diary_modify).getString("diary_write_date"));
////        System.out.println(Date.valueOf(user_brithday));
//    	根据信息去添加相关日记
		diaryService.updateDiary(new Diary(diary_id,uid,diary_title,diary_content,diary_write_date,diary_write_time,diary_write_place));
		return JSON.toJSONString(success("POST /user/diarys/modify"));
	}

	/*
	 *根据id删除考研日记
	 */
	@RequestMapping(value = "/user/diarys/delete", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteDiary(@RequestParam String id, @RequestParam String diary_id){
		int uid = Integer.parseInt(id);
		int did = Integer.parseInt(diary_id);
//    	根据信息去删除相关日记
		diaryService.deleteDiary(new Diary(did,uid));
		return JSON.toJSONString(success("GET /user/diarys/delete"));
	}


}
