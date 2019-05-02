package com.tool.api.service;

public interface TemplateMessageService {
	/**
     * 
     * @param openid
     * @param title
     * @param course
     * @param date
     * @param time
     * @param place
     */
     boolean sendMessage(String openid, String access_token, String formid, String title,String course,
                String date, String time, String place);
}
