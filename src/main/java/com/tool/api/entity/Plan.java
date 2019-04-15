package com.tool.api.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Plan {
	private Integer plan_id;		//计划id，自增
	private String user_id;			//用户id
	private String plan_content;	//计划内容
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date plan_start_time;	//计划开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date plan_end_time;		//计划结束时间
	private int plan_if_repeat;		//是否每日重复
	private int plan_if_prompt;		//是否打开微信未完成提示
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date plan_if_prompt_time;//提示的时间
	private int plan_if_finish;		//是否完成计划
	
	public Plan() {
		super();
	}
	
	public Plan(String user_id, String plan_content, Date plan_start_time, Date plan_end_time, int plan_if_repeat,
			int plan_if_prompt, Date plan_if_prompt_time, int plan_if_finish) {
		super();
		this.user_id = user_id;
		this.plan_content = plan_content;
		this.plan_start_time = plan_start_time;
		this.plan_end_time = plan_end_time;
		this.plan_if_repeat = plan_if_repeat;
		this.plan_if_prompt = plan_if_prompt;
		this.plan_if_prompt_time = plan_if_prompt_time;
		this.plan_if_finish = plan_if_finish;
	}

	public Integer getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(Integer plan_id) {
		this.plan_id = plan_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPlan_content() {
		return plan_content;
	}
	public void setPlan_content(String plan_content) {
		this.plan_content = plan_content;
	}
	public Date getPlan_start_time() {
		return plan_start_time;
	}
	public void setPlan_start_time(Date plan_start_time) {
		this.plan_start_time = plan_start_time;
	}
	public Date getPlan_end_time() {
		return plan_end_time;
	}
	public void setPlan_end_time(Date plan_end_time) {
		this.plan_end_time = plan_end_time;
	}
	public int getPlan_if_repeat() {
		return plan_if_repeat;
	}
	public void setPlan_if_repeat(int plan_if_repeat) {
		this.plan_if_repeat = plan_if_repeat;
	}
	public int getPlan_if_prompt() {
		return plan_if_prompt;
	}
	public void setPlan_if_prompt(int plan_if_prompt) {
		this.plan_if_prompt = plan_if_prompt;
	}
	public Date getPlan_if_prompt_time() {
		return plan_if_prompt_time;
	}
	public void setPlan_if_prompt_time(Date plan_if_prompt_time) {
		this.plan_if_prompt_time = plan_if_prompt_time;
	}
	public int getPlan_if_finish() {
		return plan_if_finish;
	}
	public void setPlan_if_finish(int plan_if_finish) {
		this.plan_if_finish = plan_if_finish;
	}
	
	
	//重写toString方法...
}
