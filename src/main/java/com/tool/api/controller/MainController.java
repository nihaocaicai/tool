package com.tool.api.controller;


import com.tool.api.exception.BaseException;
import com.tool.api.utils.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
//	token验证
	@ModelAttribute
	public void verifyToken(@RequestHeader String token)throws Exception{
			if(RedisUtil.getJedis().get(token)==null){
				throw new BaseException("error_code:2001:msg:Token已过期或无效Token");
			}
    }
//userControllern start
	//获取用户设置信息
	@RequestMapping(value = "/v1/user/info/show", method = {RequestMethod.GET})
	public String userControllerFind(@RequestHeader String token){
		String id = RedisUtil.getJedis().get(token);
		return "redirect:/user/info/show?id="+id;
	}
	//更新用户信息
	@RequestMapping(value = "/v1/user/info/modify", method = {RequestMethod.POST})
	public String userControllerUpdate(@RequestHeader String token,@RequestBody String user_info,RedirectAttributes attr) {
		String id = RedisUtil.getJedis().get(token);
		attr.addFlashAttribute("id", id);
		attr.addFlashAttribute("user_info", user_info);
//		System.out.println(user_info);
		return "redirect:/user/info/modify";
	}
	//删除用户信息
	@RequestMapping(value = "/v1/user/info/delete", method = {RequestMethod.GET})
	public String userControllerDelete(@RequestHeader String token) {
		String id = RedisUtil.getJedis().get(token);
		return "redirect:/user/info/delete?id="+id;
	}
//userControllern end
}
