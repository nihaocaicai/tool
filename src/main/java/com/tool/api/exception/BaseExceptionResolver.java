package com.tool.api.exception;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BaseExceptionResolver implements HandlerExceptionResolver {
    /**
     * 系统抛出的异常
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e 系统抛出的异常
     * @return
     */

    @Override
    @ResponseBody
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        // 解析出异常类型
        BaseException baseException = null;
        // 若该异常类型是系统自定义的异常，直接取出异常信息返回前端。
        if(e instanceof BaseException){
            baseException = (BaseException)e;
        }else{
            // 如果不是系统自定义异常，构造一个系统自定义的异常类型，信息为“未知错误”
            baseException = new BaseException("error_code:1003:msg:未知错误");
        }
        //错误信息
        String message = baseException.getMessage();
        //字符分割，获取error_code和msg
        String[] messArray = message.split(":");
        String error_code = messArray[1];
        String msg=messArray[3];
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("error_code",error_code);
        map.put("msg",msg);
        //将错误信息以json格式显示给前端
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        //异常状态码统一设为400
        modelAndView.setStatus(BAD_REQUEST);
        //将错误信息传到页面格式返回
        modelAndView.addObject(map);
        return modelAndView;
    }
}
