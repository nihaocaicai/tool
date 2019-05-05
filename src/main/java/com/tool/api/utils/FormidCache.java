package com.tool.api.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.tool.api.service.UserService;
import com.tool.mapperClass.FormId;
import com.tool.mapperClass.OpenIdAndFormId;

public class FormidCache {
	
	//普通工具类调用service方法，调用获取spring上下文工具类springContextUtil获取
	UserService userService = (UserService) SpringContextUtil.getBean("UserService");
	
	public void saveFormidCache(String id, String formid) {
		// 将该次表单产生的formid与过期期限写进缓存，键值对为key:user_id,
		// value=openIdAndFormId@Param=openid&@Param=FormId
		//获取当前时间
		Date date = new Date();
		Calendar rightnow = Calendar.getInstance();
		rightnow.setTime(date);
		//当前时间+7为formid过期时间
		rightnow.add(Calendar.DATE, 7);
		Date endday = rightnow.getTime();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH");
		//转换格式
		String formidDate = format1.format(endday);
		// 判断是否初次缓存？
		if (RedisOps.getObject(id) == null) {
			// 取出uid对应的openid
			System.out.println("id:" + id);
			//获得用户openid
			String open_id = userService.findUserIdById(Integer.parseInt(id));
			//设置openid对应的list<formid>
			OpenIdAndFormId openIdAndFormId = new OpenIdAndFormId();
			//设置新的formid与其所对应的过期时间
			FormId formid1 = new FormId(formid, formidDate);
			List<FormId> list = new ArrayList<FormId>();
			//添加到list<formid>中
			list.add(formid1);
			openIdAndFormId.setOpenid(open_id);
			openIdAndFormId.setFormid(list);
			RedisOps.setObject(id, openIdAndFormId);
		} else {
			//设置openid对应的list<Formid>
			OpenIdAndFormId openIdAndFormId = (OpenIdAndFormId) RedisOps.getObject(id);
			List<FormId> list = openIdAndFormId.getFormid();
			//设置新的formid与其所对应的过期时间
			FormId formId2 = new FormId(formid, formidDate);
			//添加到list<formid>中
			list.add(formId2);
			System.out.println("list:" + list);
			openIdAndFormId.setFormid(list);
			RedisOps.setObject(id, openIdAndFormId);
		}
	}
}
