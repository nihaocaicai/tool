package com.tool.api.utils.redis;

import com.tool.api.utils.SerializeUtil;
import redis.clients.jedis.Jedis;

public class RedisOps {
	//写缓存
	public static void set(String key, String value) {
		Jedis jedis = RedisUtil.getJedis();
		jedis.set(key, value);
		jedis.close();
	}

	//读缓存
	public static String get(String key) {
		Jedis jedis = RedisUtil.getJedis();
		String value = jedis.get(key);
		jedis.close();
		return value;
	}

	//将对象写进缓存
	public static void setObject(String key, Object object) {
		Jedis jedis = RedisUtil.getJedis();
		jedis.set(key.getBytes(), SerializeUtil.serizlize(object));
		jedis.close();
	}

	//读取缓存中的对象
	public static Object getObject(String key) {
		Jedis jedis = RedisUtil.getJedis();
		byte[] bytes = jedis.get(key.getBytes());
		System.out.println("bytes:" + bytes);
		jedis.close();
		if (bytes == null) {
			return null;
		}
		else {
			return SerializeUtil.deserialize(bytes);
		}
	}

}
