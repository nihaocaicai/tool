package com.tool.api.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Diary {
	private Integer diary_id;		//日记编号
	private String user_id;			//用户id
	private String diary_title;		//日记标题
	private String diary_content;	//日记内容
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date diary_write_time; //日期
	private String diary_write_place; //地点
	
	public Diary() {
		super();
	}
	
	public Diary(String user_id, String diary_title, String diary_content, Date diary_write_time,
			String diary_write_place) {
		super();
		this.user_id = user_id;
		this.diary_title = diary_title;
		this.diary_content = diary_content;
		this.diary_write_time = diary_write_time;
		this.diary_write_place = diary_write_place;
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
	public java.util.Date getDiary_write_time() {
		return diary_write_time;
	}
	public void setDiary_write_time(java.util.Date diary_write_time) {
		this.diary_write_time = diary_write_time;
	}
	public String getDiary_write_place() {
		return diary_write_place;
	}
	public void setDiary_write_place(String diary_write_place) {
		this.diary_write_place = diary_write_place;
	}

	//重写toString方法...
}
