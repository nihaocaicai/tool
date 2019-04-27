package com.tool.api.service.impl;


import com.alibaba.fastjson.JSON;
import com.tool.api.dao.UserDao;
import com.tool.api.entity.User;
import com.tool.api.exception.TokenException;
import com.tool.api.service.UserTokenService;
import com.tool.api.utils.HttpRequest;
import com.tool.api.utils.RandomString;
import com.tool.api.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;


@Service
@Transactional
public class UserTokenServicelmpl implements UserTokenService{
//token star
    // 生成令牌,这里令牌不应该重复
    public static String generateToken()
    {
        String randChar= RandomString.getRandomString(32);
        return DigestUtils.md5DigestAsHex(randChar.getBytes());
    }

    //验证token是否合法或者是否过期
    public static void needPrimaryScope(){

    }
//token end


//user token star
    // 小程序app_id
    private String wxAppID="wx1571b5d3bf600f43";
    // 小程序app_secret
    private String wxAppSecret="1a99b23e6c8e86160c91e906736ce76a";
    // 获取token
    public String getToken(String code)throws Exception{
        // 微信使用code换取用户openid及session_key的url地址
        String wxLoginUrl="https://api.weixin.qq.com/sns/jscode2session";
        String params="appid="+wxAppID+"&secret="+wxAppSecret+"&js_code="+code+"&grant_type=authorization_code";
        String result = HttpRequest.sendGet(wxLoginUrl,params);
        //解析JSON字符串!!!
        String openid = JSON.parseObject(result).getString("openid");
//        System.out.println(openid);
        if(openid==null) {
            // 为什么以empty判断是否错误，这是根据微信返回
            // 这种情况通常是由于传入不合法的code
            throw new Exception("获取session_key及openID时异常，微信内部错误");
        }
        else{
            return this.grantToken(openid);
        }
    }

    /*颁发令牌
     只要调用登陆就颁发新令牌
     但旧的令牌依然可以使用
     所以通常令牌的有效时间比较短
     */
    @Autowired
    private UserDao userDao;
    private String grantToken(String openid){
        //查找数据库是否有openid
        int if_exit = this.userDao.findUserByIdIf(openid);
//        System.out.println(if_exit);
        // 借助微信的openid作为用户标识
        // 但在系统中的相关查询还是使用自己的uid
        if (if_exit==0){
            //查询数据库没有，插入
            this.userDao.insertUser(new User(openid));
        }
//      查询该user_id在数据库的UID
        int uid = this.userDao.findUserByUserId(openid);
//        将UID装换为字符串
        openid=""+uid;
//        System.out.println(uid);
        //准备缓存的东西
        String cachedValue =this.prepareCachedValue(openid);
        //开始缓存
        String token = this.saveToCache(cachedValue);
//        System.out.println(token);
        return token;
    }

    //准备缓存的东西
    private String prepareCachedValue(String openid)
    {
        return openid;
    }

    // 写入缓存,在这里获得对应的token
    private String saveToCache(String openid){
        String key = generateToken();
        String value = openid;
        /**
         * 存储数据到缓存中，并制定过期时间和当Key存在时是否覆盖。
         * @param key
         * @param value
         * @param nxxx  nxxx的值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，
         *              则只有当key已经存在时才进行set
         * @param expx
         *            expx的值只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒。
         * @param time
         *            过期时间，单位是expx所代表的单位。
         * @return
         */
        RedisUtil.getJedis().set(key,value,"NX","EX",3600*24*6);
//        System.out.println(key+"+"+RedisUtil.getJedis().get(key));
//        释放资源
        RedisUtil.getJedis().close();
        return key;
    }
}
