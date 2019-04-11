package com.tool.api.entity;

import java.sql.Date;

public class Error {
	private Integer error_id;		//出错序号，自增
	private String user_id;			//用户id
	private String error_content;	//出错反馈内容
	private Date error_date;		//反馈时间
	public Integer getError_id() {
		return error_id;
	}
	public void setError_id(Integer error_id) {
		this.error_id = error_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getError_content() {
		return error_content;
	}
	public void setError_content(String error_content) {
		this.error_content = error_content;
	}
	public Date getError_date() {
		return error_date;
	}
	public void setError_date(Date error_date) {
		this.error_date = error_date;
	}
	//重写toString方法...
}
