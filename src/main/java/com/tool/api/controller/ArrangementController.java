package com.tool.api.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.tool.api.utils.formid.FormidCache;
import com.tool.api.utils.responseDataUtils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.tool.api.entity.Arrangement;
import com.tool.api.exception.BaseException;
import com.tool.api.service.ArrangementService;

import static com.tool.api.utils.responseSuccess.success;

@Controller
public class ArrangementController {
	@Autowired
	private ArrangementService arrangementService;
	
	/*
	 * 根据id查询用户的全部日记记录
	 */
	@RequestMapping(value = "/user/arrangements/all", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findUserById(@ModelAttribute("id") String id,
			@ModelAttribute("pageSize") int pageSize)
			throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		int uid = Integer.parseInt(id);
		PageHelper.startPage(1, pageSize*10);
		List<Arrangement> arrangement = arrangementService.findArrangeByUserId(uid);
//		加入转换的数据，类中所在的日期方法名,类的对象
		List<HashMap<String, Object>> list = ResponseData.<Arrangement>responseData(arrangement, "getArrange_date",
				new Arrangement());
		return JSON.toJSONString(list);
	}

	/*
	 * 添加考研安排
	 */
	@RequestMapping(value = "/user/arrangements/add",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addArrangement(@ModelAttribute("id") String id,
			@ModelAttribute("user_arrangement_add") String user_arrangement_add) throws BaseException {
		int uid = Integer.parseInt(id);
//        这里获取formid
		String arrange_form_id = JSON.parseObject(user_arrangement_add).getString("arrange_form_id");
		System.out.println("formid" + arrange_form_id);
		String arrange_content = JSON.parseObject(user_arrangement_add).getString("arrange_content");
		String arrange_place = JSON.parseObject(user_arrangement_add).getString("arrange_place");
		Date arrange_date = Date.valueOf(JSON.parseObject(user_arrangement_add).getString("arrange_date"));
		String arrange_time = JSON.parseObject(user_arrangement_add).getString("arrange_time");
		int arrange_if_prompt = JSON.parseObject(user_arrangement_add).getInteger("arrange_if_prompt");
		Date arrange_if_prompt_date = null;
		if(JSON.parseObject(user_arrangement_add).getString("arrange_if_prompt_date") != null) {
			arrange_if_prompt_date = Date.valueOf(JSON.parseObject(user_arrangement_add).getString("arrange_if_prompt_date"));
		}
		String arrange_if_prompt_time = JSON.parseObject(user_arrangement_add).getString("arrange_if_prompt_time");
//    	根据信息去添加相关安排
		arrangementService.insertArrange(new Arrangement(uid, arrange_content, arrange_place, arrange_date,
				arrange_time, arrange_if_prompt, arrange_if_prompt_date, arrange_if_prompt_time));

		// 将该次表单产生的formid与过期期限写进缓存，键值对为key:user_id,
		// value=openIdAndFormId@Param=openid&@Param=FormId
		new FormidCache().saveFormidCache(id, arrange_form_id);
		
		return JSON.toJSONString(success("POST /user/arrangements/add"));
	}

	/*
	 * 修改考研日记
	 */
	@RequestMapping(value = "/user/arrangements/modify",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String modifyArrangement(@ModelAttribute("id") String id,
			@ModelAttribute("user_arrangement_modify") String user_arrangement_modify) {
		int uid = Integer.parseInt(id);
		int arrange_id = JSON.parseObject(user_arrangement_modify).getInteger("arrange_id");
//        这里获取更新再次提交的formid
		String arrange_form_id = JSON.parseObject(user_arrangement_modify).getString("arrange_form_id");
		String arrange_content = JSON.parseObject(user_arrangement_modify).getString("arrange_content");
		String arrange_place = JSON.parseObject(user_arrangement_modify).getString("arrange_place");
		Date arrange_date = Date.valueOf(JSON.parseObject(user_arrangement_modify).getString("arrange_date"));
		String arrange_time = JSON.parseObject(user_arrangement_modify).getString("arrange_time");
		int arrange_if_prompt = JSON.parseObject(user_arrangement_modify).getInteger("arrange_if_prompt");
		Date arrange_if_prompt_date = null;
		if(JSON.parseObject(user_arrangement_modify).getString("arrange_if_prompt_date") != null) {
			arrange_if_prompt_date = Date.valueOf(JSON.parseObject(user_arrangement_modify).getString("arrange_if_prompt_date"));
		}
		String arrange_if_prompt_time = JSON.parseObject(user_arrangement_modify).getString("arrange_if_prompt_time");
//    	根据信息去更新相关安排
		arrangementService.updateArrange(new Arrangement(arrange_id, uid, arrange_content, arrange_place, arrange_date,
				arrange_time, arrange_if_prompt, arrange_if_prompt_date, arrange_if_prompt_time));
		
		// 将该次表单产生的formid与过期期限写进缓存，键值对为key:user_id,
		// value=openIdAndFormId@Param=openid&@Param=FormId
		new FormidCache().saveFormidCache(id, arrange_form_id);
		
		return JSON.toJSONString(success("POST /user/arrangements/modify"));
	}

	/*
	 * 根据id删除考研日记
	 */
	@RequestMapping(value = "/user/arrangements/delete", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteArrangement(@RequestParam String id, @RequestParam String arrange_id) {
		int uid = Integer.parseInt(id);
		int aid = Integer.parseInt(arrange_id);
//    	根据信息去删除相关日记
		arrangementService.deleteArrange(new Arrangement(aid, uid));
		return JSON.toJSONString(success("GET /user/arrangements/delete"));
	}
}
