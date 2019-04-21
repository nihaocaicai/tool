package com.tool.mapperClass;

public class FindPlanByIdAndDate {
	private String plan_id;
	private String plan_content;
	private String plan_start_time;
	private String plan_end_time;
	private String plan_if_repeat;
	private String plan_if_prompt;
	private String plan_if_prompt_time;
	private String plan_if_finish;
	private String right;
	
	public FindPlanByIdAndDate(String plan_id, String plan_content, String plan_start_time, String plan_end_time,
			String plan_if_repeat, String plan_if_prompt, String plan_if_prompt_time, String plan_if_finish,
			String right) {
		super();
		this.plan_id = plan_id;
		this.plan_content = plan_content;
		this.plan_start_time = plan_start_time;
		this.plan_end_time = plan_end_time;
		this.plan_if_repeat = plan_if_repeat;
		this.plan_if_prompt = plan_if_prompt;
		this.plan_if_prompt_time = plan_if_prompt_time;
		this.plan_if_finish = plan_if_finish;
		this.right = right;
	}
	
	public String getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}
	public String getPlan_content() {
		return plan_content;
	}
	public void setPlan_content(String plan_content) {
		this.plan_content = plan_content;
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
	public String getPlan_if_repeat() {
		return plan_if_repeat;
	}
	public void setPlan_if_repeat(String plan_if_repeat) {
		this.plan_if_repeat = plan_if_repeat;
	}
	public String getPlan_if_prompt() {
		return plan_if_prompt;
	}
	public void setPlan_if_prompt(String plan_if_prompt) {
		this.plan_if_prompt = plan_if_prompt;
	}
	public String getPlan_if_prompt_time() {
		return plan_if_prompt_time;
	}
	public void setPlan_if_prompt_time(String plan_if_prompt_time) {
		this.plan_if_prompt_time = plan_if_prompt_time;
	}
	public String getPlan_if_finish() {
		return plan_if_finish;
	}
	public void setPlan_if_finish(String plan_if_finish) {
		this.plan_if_finish = plan_if_finish;
	}
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "{\"plan_id\":" + plan_id + ", \"plan_content\":\"" + plan_content + "\", \"plan_start_time\":\""
				+ plan_start_time + "\", \"plan_end_time\":\"" + plan_end_time + "\", \"plan_if_repeat\":" + plan_if_repeat
				+ "\", \"plan_if_prompt\":\"" + plan_if_prompt + "\", \"plan_if_prompt_time\":\"" + plan_if_prompt_time
				+ "\", \"plan_if_finish\":\"" + plan_if_finish + "\",right\":\"" + right + "\"}";
	}
	
	
}
