package com.tool.api.service.impl;


import com.alibaba.fastjson.JSON;
import com.tool.api.dao.UserDao;
import com.tool.api.entity.User;
import com.tool.api.exception.TokenException;
import com.tool.api.service.UserTokenService;
import com.tool.api.utils.HttpRequest;
import com.tool.api.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Map;


@Service
@Transactional
public class UserTokenServicelmpl implements UserTokenService{
    /**
     * 登陆
     * 思路1：每次调用登录接口都去微信刷新一次session_key，生成新的Token，不删除久的Token
     * 思路2：检查Token有没有过期，没有过期则直接返回当前Token
     * 思路3：重新去微信刷新session_key并删除当前Token，返回新的Token
     */
    // 小程序app_id
    private String wxAppID="wx1571b5d3bf600f43";
    // 小程序app_secret
    private String wxAppSecret="35679cf09af2868451a000934bd88c0d";

    //    获取token
    public String getToken(String code)throws Exception{
        // 微信使用code换取用户openid及session_key的url地址
        String wxLoginUrl="https://api.weixin.qq.com/sns/jscode2session?";
        String params="appid="+wxAppID+"&secret="+wxAppSecret+"&js_code="+code+"&grant_type=authorization_code";
        String result = HttpRequest.sendGet(wxLoginUrl,params);
        //解析JSON字符串!!!
        Map<String, Object> wxResult = JSON.parseObject(result);
        //获取用户的唯一标识（openid）
        String openid = (String) wxResult.get("openid");
        if(openid != null) {

        }
        else {
            // 为什么以empty判断是否错误，这是根据微信返回
            // 规则摸索出来的
            // 这种情况通常是由于传入不合法的code
            throw new Exception("获取session_key及openID时异常，微信内部错误");
        }
        String token;
        token="";

        return token;
    }
    // 写入缓存
    private String saveToCache(String openid) throws TokenException{
        String key = generateToken();
        String value = openid;
//        时间戳
        String expire_in="";
//        boolean result = cache(key, value,expire_in);

//        if (!result){
//            throw new TokenException(null,"服务器缓存异常","10005");
//        }
        return key;
    }

/*     颁发令牌
     只要调用登陆就颁发新令牌
     但旧的令牌依然可以使用
     所以通常令牌的有效时间比较短
     目前微信的express_in时间是7200秒
     在不设置刷新令牌（refresh_token）的情况下
     只能延迟自有token的过期时间超过7200秒（目前还无法确定，在express_in时间到期后
     还能否进行微信支付
     没有刷新令牌会有一个问题，就是用户的操作有可能会被突然中断*/
    private String grantToken(String openid){
        // 如果想要更加安全可以考虑自己生成更复杂的令牌
        String token="";

        User user = findUserById(openid);
        // 借助微信的openid作为用户标识
        if (user.getUser_id()==null) {
//            查询数据库没有，插入
            user.setUser_id(openid);
            insertUser(user);
        }

        return token;
    }
    // 生成令牌
    public static String generateToken()
    {
        String randChar= RandomString.getRandomString(32);
        return DigestUtils.md5DigestAsHex(randChar.getBytes());
    }
    //    注解注入UserDao
    @Autowired
    private UserDao userDao;
    //    查询用户是否存在
    public User findUserById(String id){
        return this.userDao.findUserById(id);
    }
    //    插入新的用户
    public void insertUser(User user) {
        this.userDao.insertUser(user);
    }

    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }
}
