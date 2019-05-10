package com.tool.mapperClass;

public class PlanMapper {
	private String time;
	private String content;
	
	public PlanMapper(String time, String content) {
		super();
		this.time = time;
		this.content = content;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "PlanMapper [time=" + time + ", content=" + content + "]";
	}	
}
