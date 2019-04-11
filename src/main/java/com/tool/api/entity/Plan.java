package com.tool.api.entity;

import java.sql.Date;

public class Plan {
	private Integer plan_id;		//计划id，自增
	private String user_id;			//用户id
	private String plan_content;	//计划内容
	private Date plan_date;         //计划时间

	public Plan() {
		super();
	}
	
	public Plan(String user_id, String plan_content, Date plan_date) {
		super();
		this.user_id = user_id;
		this.plan_content = plan_content;
		this.plan_date = plan_date;
	}
	
	public Plan(Integer plan_id, String user_id, String plan_content, Date plan_date) {
		super();
		this.plan_id = plan_id;
		this.user_id = user_id;
		this.plan_content = plan_content;
		this.plan_date = plan_date;
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
	public Date getPlan_date() {
		return plan_date;
	}
	public void setPlan_date(Date plan_date) {
		this.plan_date = plan_date;
	}
	//重写toString方法...
}
