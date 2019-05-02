package com.tool.mapperClass;

import java.io.Serializable;
import java.util.List;

public class OpenIdAndFormId implements Serializable{
	/**
	 * 生成序列化id
	 */
	private static final long serialVersionUID = -352476045110963875L;
	private List<FormId> formid;
	private String openid;
	
	public List<FormId> getFormid() {
		return formid;
	}
	public void setFormid(List<FormId> formid) {
		this.formid = formid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	@Override
	public String toString() {
		return "OpenIdAndFormId [formid=" + formid + ", openid=" + openid + "]";
	}
	
}
