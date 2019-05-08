package com.tool.api.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.tool.api.utils.formid.FormidCache;
import com.tool.api.utils.responseDataUtils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.tool.api.entity.Arrangement;
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
	public String findUserById(String id)
			throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		int uid = Integer.parseInt(id);
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
			@ModelAttribute("user_arrangement_add") String user_arrangement_add) {
		int uid = Integer.parseInt(id);
//        这里获取formid
		String arrange_form_id = JSON.parseObject(user_arrangement_add).getString("arrange_form_id");
		System.out.println("formid" + arrange_form_id);
		String arrange_content = JSON.parseObject(user_arrangement_add).getString("arrange_content");
		String arrange_place = JSON.parseObject(user_arrangement_add).getString("arrange_place");
		Date arrange_date = Date.valueOf(JSON.parseObject(user_arrangement_add).getString("arrange_date"));
		String arrange_time = JSON.parseObject(user_arrangement_add).getString("arrange_time");
		int arrange_if_prompt = JSON.parseObject(user_arrangement_add).getInteger("arrange_if_prompt");
		Date arrange_if_prompt_date = Date
				.valueOf(JSON.parseObject(user_arrangement_add).getString("arrange_if_prompt_date"));
		Time arrange_if_prompt_time = Time
				.valueOf(JSON.parseObject(user_arrangement_add).getString("arrange_if_prompt_time"));
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
		Date arrange_if_prompt_date = Date
				.valueOf(JSON.parseObject(user_arrangement_modify).getString("arrange_if_prompt_date"));
		Time arrange_if_prompt_time = Time
				.valueOf(JSON.parseObject(user_arrangement_modify).getString("arrange_if_prompt_time"));
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

	/*
	 * 插入某用户一条考试安排记录 测试例子：
	 */
//    @RequestMapping(value = "/insertArrange", method = {RequestMethod.POST}, produces="application/json;charset=UTF-8")
//    public String insertArrange(String user_id, String arrange_content, String arrange_place, String arrange_time,
//    						 String arrange_if_prompt, String arrange_if_prompt_time, Model model){
//    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	Date arrangeTime = null;
//    	Date arrangeIfPromptTime = null;
//		try {
//			arrangeTime = format.parse(arrange_time);
//			arrangeIfPromptTime = format.parse(arrange_if_prompt_time);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//插入数据到数据库
//		Arrangement arrangement = new Arrangement(user_id, arrange_content, arrange_place, arrangeTime, Integer.valueOf(arrange_if_prompt), arrangeIfPromptTime);
//		arrangementService.insertArrange(arrangement);
////
////		//将该次表单产生的formid与过期期限写进缓存，键值对为key:user_id, value=openIdAndFormId@Param=openid&@Param=FormId
////		Date date = new Date();
////		Calendar rightnow = Calendar.getInstance();
////		rightnow.setTime(date);
////		rightnow.add(Calendar.DATE, 7);
////		Date endday = rightnow.getTime();
////		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH");
////		String formidDate = format1.format(endday);
////		System.out.println(formidDate);
////		//判断是否初次缓存？
////		if(RedisOps.getObject(user_id) == null) {
////			//取出uid对应的openid
////			String open_id = userService.findUserIdById(Integer.parseInt(user_id));
////			System.out.println(open_id);
////			OpenIdAndFormId openIdAndFormId = new OpenIdAndFormId();
////			FormId formid1 = new FormId(formid, formidDate);
////			List<FormId> list = new ArrayList<FormId>();
////			list.add(formid1);
////			openIdAndFormId.setOpenid(open_id);
////			openIdAndFormId.setFormid(list);
////			RedisOps.setObject(user_id, openIdAndFormId);
////		}
////		else {
////			OpenIdAndFormId openIdAndFormId = (OpenIdAndFormId) RedisOps.getObject(user_id);
////			List<FormId> list = openIdAndFormId.getFormid();
////			System.out.println("list:" + list);
////			FormId formId2 = new FormId(formid, formidDate);
////			list.add(formId2);
////			openIdAndFormId.setFormid(list);
////			RedisOps.setObject(user_id, openIdAndFormId);
////		}
//    	return "success";
//    }
//
	/*
	 * 目标方法获取根据表单修改过后的Datetime -> @ModelAttribute 更新数据库某个用户考试安排记录
	 * 测试例子：http://localhost:8080/tool/updateArrange?arrange_id=3&arrange_place=
	 * 广财综合楼&arrange_time=2019-12-03%209:00:00
	 */
//    @RequestMapping(value = "/updateArrange", method = {RequestMethod.POST}, produces="application/json;charset=UTF-8")
//    public String updateArrange(Arrangement arrangement) {
//    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	Date arrangeTime = null;
//    	Date arrangeIfPromptTime = null;
//    	String Time1 = format.format(arrangement.getArrange_time());
//    	String Time2 = format.format(arrangement.getArrange_if_prompt_time());
//    	try {
//			arrangeTime = format.parse(Time1);
//			arrangeIfPromptTime = format.parse(Time2);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//     	arrangement.setArrange_time(arrangeTime);
//     	arrangement.setArrange_if_prompt_time(arrangeIfPromptTime);
//    	arrangementService.updateArrange(arrangement);
//    	return "success";
//    }
//
	/*
	 * 删除某用户一条考试安排记录
	 * 测试例子：http://localhost:8080/tool/deleteTime?user_id=abcabc&time_date=2019-04-
	 * 23
	 */
//    @RequestMapping("/deleteArrange")
//    public String deleteArrange(@RequestParam(value = "arrange_id") String arrange_id) {
//    	arrangementService.deleteArrange(arrange_id);
//    	return "success";
//    }
}
