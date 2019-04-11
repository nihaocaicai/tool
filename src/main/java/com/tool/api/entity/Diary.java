package com.tool.api.entity;

import java.sql.Date;

public class Diary {
	private Integer diary_id;		//日记编号
	private String user_id;			//用户id
	private String diary_content;	//日记内容
	private Date diary_date;		//写日记时间
	
	public Diary() {
		super();
	}

	public Diary(Integer diary_id, String user_id, String diary_content, Date diary_date) {
		super();
		this.diary_id = diary_id;
		this.user_id = user_id;
		this.diary_content = diary_content;
		this.diary_date = diary_date;
	}
	
	public Diary(String user_id, String diary_content, Date diary_date) {
		super();
		this.user_id = user_id;
		this.diary_content = diary_content;
		this.diary_date = diary_date;
	}

	public Integer getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(Integer diary_id) {
		this.diary_id = diary_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDiary_content() {
		return diary_content;
	}
	public void setDiary_content(String diary_content) {
		this.diary_content = diary_content;
	}
	public Date getDiary_date() {
		return diary_date;
	}
	public void setDiary_date(Date diary_date) {
		this.diary_date = diary_date;
	}
	//重写toString方法...
}
