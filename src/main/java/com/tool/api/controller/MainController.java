package com.tool.api.controller;


import com.tool.api.exception.BaseException;
import com.tool.api.utils.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
	@ModelAttribute
	public void verifyToken(@RequestHeader String token)throws Exception{
			if(RedisUtil.getJedis().get(token)==null){
				throw new BaseException("error_code:2001:msg:Token已过期或无效Token");
			}
    }

	@RequestMapping(value = "/v1/user/info/show", method = {RequestMethod.GET})
	public String userControllerFind(@RequestHeader String token){
		String id = RedisUtil.getJedis().get(token);
		return "redirect:/user/info/show?id="+id;
	}
	
	@RequestMapping(value = "/v1/user/info/modify", method = {RequestMethod.POST})
	public String userControllerUpdate(@RequestHeader String token,@RequestBody String user_info,RedirectAttributes attr) {
		String id = RedisUtil.getJedis().get(token);
		attr.addFlashAttribute("id", id);
		attr.addFlashAttribute("user_info", user_info);
//		System.out.println(user_info);
		return "redirect:/user/info/modify";
	}
	
	@RequestMapping(value = "/api.tool/v1/user/info/delete", method = {RequestMethod.GET})
	public String userControllerDelete(String token, RedirectAttributes attr) {
		attr.addFlashAttribute("token", token);
		return "redirect:/user/info/delete";
	}
}
