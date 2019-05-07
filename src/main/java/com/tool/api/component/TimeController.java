package com.tool.api.component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tool.api.entity.Arrangement;
import com.tool.api.service.TemplateMessageService;
import com.tool.api.service.TimeService;
import com.tool.api.utils.redis.RedisOps;
import com.tool.mapperClass.FormId;
import com.tool.mapperClass.OpenIdAndFormId;

//注释关闭定时器
@Component
public class TimeController {

	@Autowired
	private TimeService timeService;
	@Autowired
	private TemplateMessageService templateMessageService;

	@Scheduled(cron = "10 0/1 * * * * ") // 从第十秒开始，间隔一分钟执行
	public void taskCycle() throws Exception {
		// 当前时间
		Date nowdate = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatHour = new SimpleDateFormat("HH");
		
		String date = formatDate.format(nowdate);
		String hour = formatHour.format(nowdate);
		
		// 根据当前时间去查数据库中与之匹配并且开启提醒的用户，并返回一个列表
		List<Arrangement> list = null;
		list = timeService.FindUid(date, hour);
		// 获取access_token
		String access_token = RedisOps.get("access_token");
		System.out.println("\naccess_token:" + access_token);
		System.out.println("\nlist:" + list);
		if (list != null) {
			// 遍历符合要求的用户
			for (Arrangement arrangement : list) {
				// 计划内容
				String course = arrangement.getArrange_content();
				// 获取用户具体安排日期
				SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
				String arrangeDate = formatdate.format(arrangement.getArrange_date());
				// 获取用户具体安排时间
				String arrangeTime = arrangement.getArrange_time();
				// 获取考试地点
				String place = arrangement.getArrange_place();

				String uid = String.valueOf(arrangement.getUser_id());
				// 从缓存中获取用户uid所对应的openid与对应的list<formid>
				OpenIdAndFormId openIdAndFormId = (OpenIdAndFormId) RedisOps.getObject(uid);
				System.out.println("\nopenIdAndFormId:" + openIdAndFormId);
				// 获取用户openid
				String openid = openIdAndFormId.getOpenid();
				// 判断formid是否失效
				List<FormId> listforms = openIdAndFormId.getFormid();
				System.out.println("\nlistforms:" + listforms);
				String formid = null;
				SimpleDateFormat formatenddate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//
				if (listforms != null) {
					for (FormId formIdTable : listforms) {
						String enddate = formIdTable.getEndday();
						String now = formatenddate.format(nowdate);
						// 如果当前formid失效日期大于当前时间，则证明该formid有效
						if (now.compareTo(enddate) < 0) {
							formid = formIdTable.getFormid();
							templateMessageService.sendMessage(openid, access_token, formid, "考试安排", course, arrangeDate, arrangeTime,
									place);
							// 消耗一个formid
							listforms.remove(0);
							// 将修改过的list<formid>写回对应的类
							openIdAndFormId.setFormid(listforms);
							// 将修改过后的值重新写回缓存
							RedisOps.setObject(uid, openIdAndFormId);
							break;
						}
					}
				}
			}
		}
	}
}
