package com.tool.mapperClass;

import java.io.Serializable;

public class FormId implements Serializable{
	/**
	 * 生成序列化id
	 */
	private static final long serialVersionUID = -2942138507777686001L;
	private String formid;
	private String enddate;

	public FormId() {
		super();
	}

	public FormId(String formid, String enddate) {
		super();
		this.formid = formid;
		this.enddate = enddate;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public String getEndday() {
		return enddate;
	}

	public void setEndday(String enddate) {
		this.enddate = enddate;
	}

	@Override
	public String toString() {
		return "FormId [formid=" + formid + ", enddate=" + enddate + "]";
	}
	
}
