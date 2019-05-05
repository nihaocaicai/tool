package com.tool.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Date;
import java.sql.Time;

public class Arrangement {
	private int arrange_id;		//安排id
	private int user_id;		//用户唯一标示ID
	private String arrange_content;		//安排内容
	private String arrange_place;		//安排地点
	@JSONField(format="yyyy-MM-dd")
	private Date arrange_date;			//安排日期
	private String arrange_time;			//安排时间
	private int arrange_if_prompt;		//是否打开微信提示考试时间
	@JSONField(format="yyyy-MM-dd")
	private Date arrange_if_prompt_date;//提示日期
	@JSONField(format="HH:mm:ss")
	private Time arrange_if_prompt_time;//提示时间
	private int right=0;
	
	public Arrangement() {
		super();
	}

	public Arrangement(int user_id,String arrange_content,String arrange_place,Date arrange_date,String arrange_time,
					   int arrange_if_prompt,Date arrange_if_prompt_date,Time arrange_if_prompt_time) {
		super();
		this.user_id=user_id;
		this.arrange_content=arrange_content;
		this.arrange_place=arrange_place;
		this.arrange_date=arrange_date;
		this.arrange_time=arrange_time;
		this.arrange_if_prompt=arrange_if_prompt;
		this.arrange_if_prompt_date=arrange_if_prompt_date;
		this.arrange_if_prompt_time=arrange_if_prompt_time;
	}

	public Arrangement(int arrange_id,int user_id,String arrange_content,String arrange_place,Date arrange_date,String arrange_time,
					   int arrange_if_prompt,Date arrange_if_prompt_date,Time arrange_if_prompt_time) {
		super();
		this.arrange_id=arrange_id;
		this.user_id=user_id;
		this.arrange_content=arrange_content;
		this.arrange_place=arrange_place;
		this.arrange_date=arrange_date;
		this.arrange_time=arrange_time;
		this.arrange_if_prompt=arrange_if_prompt;
		this.arrange_if_prompt_date=arrange_if_prompt_date;
		this.arrange_if_prompt_time=arrange_if_prompt_time;
	}

	public Arrangement(int arrange_id,int user_id) {
		super();
		this.arrange_id=arrange_id;
		this.user_id=user_id;
	}

	public int getArrange_id() {
		return arrange_id;
	}

	public void setArrange_id(int arrange_id) {
		this.arrange_id = arrange_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
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

	public Date getArrange_date() {
		return arrange_date;
	}

	public void setArrange_date(Date arrange_date) {
		this.arrange_date = arrange_date;
	}

	public String getArrange_time() {
		return arrange_time;
	}

	public void setArrange_time(String arrange_time) {
		this.arrange_time = arrange_time;
	}

	public int getArrange_if_prompt() {
		return arrange_if_prompt;
	}

	public void setArrange_if_prompt(int arrange_if_prompt) {
		this.arrange_if_prompt = arrange_if_prompt;
	}

	public Date getArrange_if_prompt_date() {
		return arrange_if_prompt_date;
	}

	public void setArrange_if_prompt_date(Date arrange_if_prompt_date) {
		this.arrange_if_prompt_date = arrange_if_prompt_date;
	}

	public Time getArrange_if_prompt_time() {
		return arrange_if_prompt_time;
	}

	public void setArrange_if_prompt_time(Time arrange_if_prompt_time) {
		this.arrange_if_prompt_time = arrange_if_prompt_time;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Arrangement [arrange_id=" + arrange_id + ", user_id=" + user_id + ", arrange_content=" + arrange_content
				+ ", arrange_place=" + arrange_place + ", arrange_date=" + arrange_date + ", arrange_time="
				+ arrange_time + ", arrange_if_prompt=" + arrange_if_prompt + ", arrange_if_prompt_date="
				+ arrange_if_prompt_date + ", arrange_if_prompt_time=" + arrange_if_prompt_time + ", right=" + right
				+ "]";
	}
	
}
