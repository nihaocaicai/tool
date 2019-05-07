package com.tool.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Date;

public class Plan {
	private int plan_id; // 计划id，自增
	private int user_id; // 用户id
	private String plan_content; // 计划内容
	@JSONField(format = "yyyy-MM-dd")
	private Date plan_date; // 计划开始的日期
	private String plan_start_time; // 计划开始时间
	private String plan_end_time; // 计划结束时间
	private int plan_if_repeat; // 是否每日重复
	private int plan_if_finish; // 是否完成计划
	private int right = 0;

	public Plan() {
		super();
	}

	public Plan(int user_id, Date plan_date) {
		super();
		this.user_id = user_id;
		this.plan_date = plan_date;
	}

	public Plan(int plan_id, int user_id, int plan_if_finish) {
		super();
		this.plan_id = plan_id;
		this.user_id = user_id;
		this.plan_if_finish = plan_if_finish;
	}

	public Plan(int user_id, String plan_content, Date plan_date, String plan_start_time, String plan_end_time,
			int plan_if_repeat, int plan_if_finish) {
		super();
		this.user_id = user_id;
		this.plan_content = plan_content;
		this.plan_date = plan_date;
		this.plan_start_time = plan_start_time;
		this.plan_end_time = plan_end_time;
		this.plan_if_repeat = plan_if_repeat;
		this.plan_if_finish = plan_if_finish;
	}

	public Plan(int plan_id, int user_id, String plan_content, Date plan_date, String plan_start_time,
			String plan_end_time, int plan_if_repeat, int plan_if_finish) {
		super();
		this.plan_id = plan_id;
		this.user_id = user_id;
		this.plan_content = plan_content;
		this.plan_date = plan_date;
		this.plan_start_time = plan_start_time;
		this.plan_end_time = plan_end_time;
		this.plan_if_repeat = plan_if_repeat;
		this.plan_if_finish = plan_if_finish;
	}

	public Plan(int plan_id, int user_id) {
		super();
		this.user_id = user_id;
		this.plan_id = plan_id;
	}

	public int getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
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

	public String getPlan_start_time() {
		return plan_start_time;
	}

	public void setPlan_start_time(String plan_start_time) {
		this.plan_start_time = plan_start_time;
	}

	public String getPlan_end_time() {
		return plan_end_time;
	}

	public void setPlan_end_time(String plan_end_time) {
		this.plan_end_time = plan_end_time;
	}

	public int getPlan_if_repeat() {
		return plan_if_repeat;
	}

	public void setPlan_if_repeat(int plan_if_repeat) {
		this.plan_if_repeat = plan_if_repeat;
	}

	public int getPlan_if_finish() {
		return plan_if_finish;
	}

	public void setPlan_if_finish(int plan_if_finish) {
		this.plan_if_finish = plan_if_finish;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}
}
