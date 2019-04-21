package com.tool.mapperClass;

import java.util.List;

public class FindPlanAllAfter {
	private String date;
	private List<FindPlanByIdAndDate> data;
	
	public FindPlanAllAfter(String date, List<FindPlanByIdAndDate> data) {
		super();
		this.date = date;
		this.data = data;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<FindPlanByIdAndDate> getData() {
		return data;
	}
	public void setData(List<FindPlanByIdAndDate> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "{\"date\":\"" + date + "\", \"data\":" + data + "}";
	}
	
	
}
