package com.tool.api.controller;


import com.tool.api.exception.BaseException;
import com.tool.api.utils.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
//全局token验证
	@ModelAttribute
	public void verifyToken(@RequestHeader String token)throws Exception{
			if(RedisUtil.getJedis().get(token)==null){
				throw new BaseException("error_code:2001:msg:Token已过期或无效Token");
			}
    }
//userController start
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
//userController end

//diaryController start
	//获取用户全部日记信息
	@RequestMapping(value = "/v1/user/diarys/all", method = {RequestMethod.GET})
	public String diaryControllerFindAll(@RequestHeader String token) {
		String id = RedisUtil.getJedis().get(token);
		return "redirect:/user/diarys/all?id=" + id;
	}
	//添加考研日记
	@RequestMapping(value = "/v1/user/diarys/add", method = {RequestMethod.POST})
	public String diaryControllerAdd(@RequestHeader String token,@RequestBody String user_diary_add,RedirectAttributes attr) {
			String id = RedisUtil.getJedis().get(token);
			attr.addFlashAttribute("id", id);
			attr.addFlashAttribute("user_diary_add", user_diary_add);
//		System.out.println(user_diary_add);
			return "redirect:/user/diarys/add";
	}
	//修改考研日记
	@RequestMapping(value = "/v1/user/diarys/modify", method = {RequestMethod.POST})
	public String diaryControllerModify(@RequestHeader String token,@RequestBody String user_diary_modify,RedirectAttributes attr) {
		String id = RedisUtil.getJedis().get(token);
		attr.addFlashAttribute("id", id);
		attr.addFlashAttribute("user_diary_modify", user_diary_modify);
//		System.out.println(user_diary_modify);
		return "redirect:/user/diarys/modify";
	}
	//删除考研日记
	@RequestMapping(value = "/v1/user/diarys/delete", method = {RequestMethod.GET})
	public String diaryControllerDelete(@RequestHeader String token,@RequestParam int diary_id){
		String id = RedisUtil.getJedis().get(token);
//		System.out.println(diary_id);
		return "redirect:/user/diarys/delete?diary_id="+diary_id+"&id="+id;
	}
//diaryController end

//arrangementController start
	//获取用户全部安排信息
	@RequestMapping(value = "/v1/user/arrangements/all", method = {RequestMethod.GET})
	public String arrangementControllerFindAll(@RequestHeader String token) {
	String id = RedisUtil.getJedis().get(token);
	return "redirect:/user/arrangements/all?id=" + id;
	}
	//添加考研安排
	@RequestMapping(value = "/v1/user/arrangements/add", method = {RequestMethod.POST})
	public String arrangementControllerAdd(@RequestHeader String token,@RequestBody String user_arrangement_add,RedirectAttributes attr) {
		String id = RedisUtil.getJedis().get(token);
		attr.addFlashAttribute("id", id);
		attr.addFlashAttribute("user_arrangement_add", user_arrangement_add);
		System.out.println(user_arrangement_add);
		return "redirect:/user/arrangements/add";
	}
	//修改考研安排
	@RequestMapping(value = "/v1/user/arrangements/modify", method = {RequestMethod.POST})
	public String arrangementControllerModify(@RequestHeader String token,@RequestBody String user_arrangement_modify,RedirectAttributes attr) {
		String id = RedisUtil.getJedis().get(token);
		attr.addFlashAttribute("id", id);
		attr.addFlashAttribute("user_arrangement_modify", user_arrangement_modify);
		System.out.println(user_arrangement_modify);
		return "redirect:/user/arrangements/modify";
	}
	//删除考研日记
	@RequestMapping(value = "/v1/user/arrangements/delete", method = {RequestMethod.GET})
	public String arrangementControllerDelete(@RequestHeader String token,@RequestParam int arrange_id){
		String id = RedisUtil.getJedis().get(token);
//		System.out.println(arrange_id);
		return "redirect:/user/arrangements/delete?arrange_id="+arrange_id+"&id="+id;
	}
//arrangementController end

//planController start

	//获取用户全部计划信息（当天之前的，不包括当天）
	@RequestMapping(value = "/v1/user/plans/all/before", method = {RequestMethod.GET})
	public String planControllerFindAllBefore(@RequestHeader String token) {
		String id = RedisUtil.getJedis().get(token);
		return "redirect:/user/plans/all/before?id=" + id;
	}
	//获取用户全部计划信息（当天之后的，包括当天）
	@RequestMapping(value = "/v1/user/plans/all/after", method = {RequestMethod.GET})
	public String planControllerFindAllAfter(@RequestHeader String token) {
		String id = RedisUtil.getJedis().get(token);
		return "redirect:/user/plans/all/after?id=" + id;
	}
	//获取用户全部计划信息（当天）
	@RequestMapping(value = "/v1/user/plans/all/intraday", method = {RequestMethod.GET})
	public String planControllerFindAllIntraday(@RequestHeader String token) {
		String id = RedisUtil.getJedis().get(token);
		return "redirect:/user/plans/all/intraday?id=" + id;
	}
	//添加考研计划
	@RequestMapping(value = "/v1/user/plans/add", method = {RequestMethod.POST})
	public String planControllerAdd(@RequestHeader String token,@RequestBody String user_plan_add,RedirectAttributes attr) {
		String id = RedisUtil.getJedis().get(token);
		attr.addFlashAttribute("id", id);
		attr.addFlashAttribute("user_plan_add", user_plan_add);
		System.out.println(user_plan_add);
		return "redirect:/user/plans/add";
	}
	//修改考研安排
	@RequestMapping(value = "/v1/user/plans/modify", method = {RequestMethod.POST})
	public String planControllerModify(@RequestHeader String token,@RequestBody String user_plan_modify,RedirectAttributes attr) {
		String id = RedisUtil.getJedis().get(token);
		attr.addFlashAttribute("id", id);
		attr.addFlashAttribute("user_plan_modify", user_plan_modify);
		System.out.println(user_plan_modify);
		return "redirect:/user/plans/modify";
	}
	//删除考研日记
	@RequestMapping(value = "/v1/user/plans/delete", method = {RequestMethod.GET})
	public String planControllerDelete(@RequestHeader String token,@RequestParam int plan_id){
		String id = RedisUtil.getJedis().get(token);
//		System.out.println(arrange_id);
		return "redirect:/user/plans/delete?plan_id="+plan_id+"&id="+id;
	}
//planController end
}
