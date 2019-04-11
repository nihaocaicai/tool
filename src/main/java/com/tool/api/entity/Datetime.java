package com.tool.api.entity;

import java.sql.Date;

public class Datetime {
	private Integer time_id;     //该表主键
	private String user_id;      //用户id
	private String time_content; //考试安排
	private Date time_date;      //插入日期
	
	
	public Datetime() {
		super();
	}

	public Datetime(Integer time_id, String user_id, String time_content, Date time_date) {
		super();
		this.time_id = time_id;
		this.user_id = user_id;
		this.time_content = time_content;
		this.time_date = time_date;
	}
	
	public Datetime(String user_id, String time_content, Date time_date) {
		super();
		this.user_id = user_id;
		this.time_content = time_content;
		this.time_date = time_date;
	}
	public Integer getTime_id() {
		return time_id;
	}
	public void setTime_id(Integer time_id) {
		this.time_id = time_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTime_content() {
		return time_content;
	}
	public void setTime_content(String time_content) {
		this.time_content = time_content;
	}
	public Date getTime_date() {
		return time_date;
	}
	public void setTime_date(Date time_date) {
		this.time_date = time_date;
	}
	//重写toString...
}
