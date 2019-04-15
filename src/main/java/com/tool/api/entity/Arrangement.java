package com.tool.api.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Arrangement {
	private int arrange_id;		//安排id
	private String user_id;		//用户唯一标示ID
	private String arrange_content;		//考试安排
	private String arrange_place;		//考试地点
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date arrange_time;			//考试时间
	private int arrange_if_prompt;		//是否打开微信提示考试时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date arrange_if_prompt_time;//提示考试时间
	
	public Arrangement() {
		super();
	}
	
	public Arrangement(String user_id, String arrange_content, String arrange_place, Date arrange_time,
			int arrange_if_prompt, Date arrange_if_prompt_time) {
		super();
		this.user_id = user_id;
		this.arrange_content = arrange_content;
		this.arrange_place = arrange_place;
		this.arrange_time = arrange_time;
		this.arrange_if_prompt = arrange_if_prompt;
		this.arrange_if_prompt_time = arrange_if_prompt_time;
	}

	public int getArrange_id() {
		return arrange_id;
	}
	public void setArrange_id(int arrange_id) {
		this.arrange_id = arrange_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getArrange_content() {
		return arrange_content;
	}
	public void setArrange_content(String arrange_content) {
		this.arrange_content = arrange_content;
	}
	public String getArrange_place() {
		return arrange_place;
	}
	public void setArrange_place(String arrange_place) {
		this.arrange_place = arrange_place;
	}
	public Date getArrange_time() {
		return arrange_time;
	}
	public void setArrange_time(Date arrange_time) {
		this.arrange_time = arrange_time;
	}
	public int getArrange_if_prompt() {
		return arrange_if_prompt;
	}
	public void setArrange_if_prompt(int arrange_if_prompt) {
		this.arrange_if_prompt = arrange_if_prompt;
	}
	public Date getArrange_if_prompt_time() {
		return arrange_if_prompt_time;
	}
	public void setArrange_if_prompt_time(Date arrange_if_prompt_time) {
		this.arrange_if_prompt_time = arrange_if_prompt_time;
	}
	//重写toString方法
}
