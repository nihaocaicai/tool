package com.tool.api.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.tool.api.utils.HttpRequest;
import com.tool.api.utils.redis.RedisUtil;

//注释关闭计时器
@Component
public class AccessTokenController {
	@Scheduled(cron = "0 50 0/2 * * * ") // 从五十分开始，间隔两小时执行一次
	public void taskCycle() throws Exception {
		// 小程序唯一标识 (在微信小程序管理后台获取)
		String wxspAppid = "wx1571b5d3bf600f43";
		// 小程序的 app secret (在微信小程序管理后台获取)
		String wxspSecret = "1a99b23e6c8e86160c91e906736ce76a";
		// 写死的类型
		String grant_type = "client_credential";
		// 封装请求数据
		String params = "grant_type=" + grant_type + "&secret=" + wxspSecret + "&appid=" + wxspAppid;
		// 发送POST请求
		String result = HttpRequest.sendPost("https://api.weixin.qq.com/cgi-bin/token", params);
		// 拿到accesstoken
		String accesstoken = JSON.parseObject(result).getString("access_token");
		String key = "access_token";
		String value = accesstoken;
		/**
		 * 存储数据到缓存中，并制定过期时间和当Key存在时是否覆盖。
		 * 
		 * @param key
		 * @param value
		 * @param nxxx  nxxx的值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，
		 *              则只有当key已经存在时才进行set
		 * @param expx  expx的值只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒。
		 * @param time  过期时间，单位是expx所代表的单位。
		 * @return
		 */
		String flag = RedisUtil.getJedis().get("access_token");
		if (flag != null) {
			RedisUtil.getJedis().set(key, value, "XX", "EX", 3600 * 2);
		} 
		else {
			RedisUtil.getJedis().set(key, value, "NX", "EX", 3600 * 2);
		}
		System.out.println("access_token: " + accesstoken);
	}
}
