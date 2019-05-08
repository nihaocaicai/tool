package com.tool.mapperClass;

import java.util.List;

public class Template {

	// 消息接收方
	private String toUser;
	// 模板id
	private String templateId;
	// 用户表单提交的formid
	private String formid;

	// 模板消息详情链接
	private String url;
	// 消息顶部的颜色
	private String topColor;
	// 参数列表
	private List<TemplateParam> templateParamList;
	// 省略getter、setter方法
	// 按微信接口要求格式化模板
	public String toJSON() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append(String.format("\"touser\":\"%s\"", this.toUser)).append(",");
		buffer.append(String.format("\"template_id\":\"%s\"", this.templateId)).append(",");
		buffer.append(String.format("\"form_id\":\"%s\"", this.formid)).append(",");
		buffer.append(String.format("\"page\":\"%s\"", this.url)).append(",");
		buffer.append(String.format("\"color\":\"%s\"", this.topColor)).append(",");
		buffer.append("\"data\":{");
		TemplateParam param = null;
		for (int i = 0; i < this.templateParamList.size(); i++) {
			param = templateParamList.get(i);
			// 判断是否追加逗号
			if (i < this.templateParamList.size() - 1) {

				buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"},", param.getName(),
						param.getValue(), param.getColor()));
			} else {
				buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"}", param.getName(),
						param.getValue(), param.getColor()));
			}

		}
		buffer.append("}");
		buffer.append("}");
		return buffer.toString();
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopColor() {
		return topColor;
	}

	public void setTopColor(String topColor) {
		this.topColor = topColor;
	}

	public List<TemplateParam> getTemplateParamList() {
		return templateParamList;
	}

	public void setTemplateParamList(List<TemplateParam> templateParamList) {
		this.templateParamList = templateParamList;
	}
}