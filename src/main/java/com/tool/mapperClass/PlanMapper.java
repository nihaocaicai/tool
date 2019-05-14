package com.tool.mapperClass;

public class PlanMapper {
	private String time;
	private String content;
	private int plan_if_finish;
	
	public PlanMapper(String time, String content, int plan_if_finish) {
		super();
		this.time = time;
		this.content = content;
		this.plan_if_finish = plan_if_finish;
	}
	
	public int getPlan_if_finish() {
		return plan_if_finish;
	}
	public void setPlan_if_finish(int plan_if_finish) {
		this.plan_if_finish = plan_if_finish;
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
		return "PlanMapper [time=" + time + ", content=" + content + ", plan_if_finish=" + plan_if_finish + "]";
	}	
}
