package com.tool.api.entity;


import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class Diary {
	private Integer diary_id;		//日记编号
	private Integer user_id;			//用户id
	private String diary_title;		//日记标题
	private String diary_content;	//日记内容
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date diary_write_date; //日期
	private String diary_write_time; //时间
	private String diary_write_place; //地点
	private int right=0;

	public Diary() {
		super();
	}

	public Diary(int user_id,String diary_title, String diary_content, Date diary_write_date, String diary_write_time,
				String diary_write_place) {
		super();
		this.user_id = user_id;
		this.diary_title = diary_title;
		this.diary_content = diary_content;
		this.diary_write_date = diary_write_date;
		this.diary_write_time = diary_write_time;
		this.diary_write_place = diary_write_place;
	}
	public Diary(int diary_id,int user_id,String diary_title, String diary_content, Date diary_write_date, String diary_write_time,
				 String diary_write_place) {
		super();
		this.diary_id=diary_id;
		this.user_id = user_id;
		this.diary_title = diary_title;
		this.diary_content = diary_content;
		this.diary_write_date = diary_write_date;
		this.diary_write_time = diary_write_time;
		this.diary_write_place = diary_write_place;
	}
	public Diary(int diary_id,int user_id) {
		super();
		this.diary_id=diary_id;
		this.user_id = user_id;
	}

	public Integer getDiary_id() {
		return diary_id;
	}

	public void setDiary_id(Integer diary_id) {
		this.diary_id = diary_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getDiary_title() {
		return diary_title;
	}

	public void setDiary_title(String diary_title) {
		this.diary_title = diary_title;
	}

	public String getDiary_content() {
		return diary_content;
	}

	public void setDiary_content(String diary_content) {
		this.diary_content = diary_content;
	}

	public Date getDiary_write_date() {
		return diary_write_date;
	}

	public void setDiary_write_date(Date diary_write_date) {
		this.diary_write_date = diary_write_date;
	}

	public String getDiary_write_time() {
		return diary_write_time;
	}

	public void setDiary_write_time(String diary_write_time) {
		this.diary_write_time = diary_write_time;
	}

	public String getDiary_write_place() {
		return diary_write_place;
	}

	public void setDiary_write_place(String diary_write_place) {
		this.diary_write_place = diary_write_place;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}
}
