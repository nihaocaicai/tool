package com.tool.api.controller;

import javax.servlet.http.HttpServletRequest;

import com.tool.api.utils.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

	@RequestMapping(value = "/v1/user/info/show", method = {RequestMethod.GET})
	public String userControllerFind(String token) {
		String id = RedisUtil.getJedis().get(token);
		if(id==null){
			return null;
		}else {
			//		System.out.println(id);
			return "redirect:/user/info/show?id="+id;
		}
	}
	
	@RequestMapping(value = "/v1/user/info/modify", method = {RequestMethod.GET})
	public String userControllerUpdate(HttpServletRequest httpServletRequest) {
		String url = httpServletRequest.getQueryString();
		System.out.println(url);
		return "redirect:/user/info/modify?"+url;
	}
	
	@RequestMapping(value = "/api.tool/v1/user/info/delete", method = {RequestMethod.GET})
	public String userControllerDelete(String token, RedirectAttributes attr) {
		attr.addFlashAttribute("token", token);
		return "redirect:/user/info/delete";
	}
}
