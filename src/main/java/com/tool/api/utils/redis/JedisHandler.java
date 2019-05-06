package com.tool.api.utils.redis;

import org.springframework.cglib.proxy.InvocationHandler;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Method;

/**
 * jedis代理，自动关闭资源
 */
public class JedisHandler implements InvocationHandler {

    private JedisPool jedisPool;

    public JedisHandler(JedisPool jedisPool){
        this.jedisPool = jedisPool;
    }

    /**
     * 当使用jedis方法的时候，实际调用的这里的方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JedisHandler.invoke -----start");
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Object invoke = method.invoke(jedis, args);
            System.out.println("代理方法获得的结果："+invoke);
            return invoke;
        }finally {
            if (jedis != null) {
                jedis.close();
                System.out.println("关闭jedis连接");
            }
            System.out.println("JedisHandler.invoke -----end");
        }
    }
}

