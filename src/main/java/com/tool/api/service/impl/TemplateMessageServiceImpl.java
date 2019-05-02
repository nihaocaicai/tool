package com.tool.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tool.api.service.TemplateMessageService;
import com.tool.api.utils.CommonUtil;
import com.tool.mapperClass.Template;
import com.tool.mapperClass.TemplateParam;

@Service
public class TemplateMessageServiceImpl implements TemplateMessageService{

	@Override
	public boolean sendMessage(String openid, String access_token, String formid, String title, String course, String date,
			String time, String place) {
		// TODO Auto-generated method stub
		Template tem=new Template();  
		//模板消息id
        tem.setTemplateId("RupC_e0_LbgddKA3DrQKaIOY-rOniQtYX3Opt1H84Eg");
        tem.setTopColor("#00DD00");//颜色
        tem.setToUser(openid);//接收方ID
        tem.setFormid(formid);
        System.out.println(openid+"=====================");
        //设置超链接（点击模板可跳转相应链接中）
        tem.setUrl("你要跳转的链接");
        List<TemplateParam> paras=new ArrayList<TemplateParam>();//消息主体
        paras.add(new TemplateParam("first",title,"#333")); //标题 
        paras.add(new TemplateParam("keyword1",course,"#333"));//审核类型
        paras.add(new TemplateParam("keyword2",date,"#333"));//日期
        paras.add(new TemplateParam("keyword3",time,"#333"));//时间
        paras.add(new TemplateParam("keyword4",place,"#333"));//地点
        paras.add(new TemplateParam("remark","点击此消息查看详情","#333"));  //消息详情
        tem.setTemplateParamList(paras);  
        //发送消息
        boolean result=sendTemplateMsg(access_token, tem);
        System.out.println(result);
        return result;
	}

	public static boolean sendTemplateMsg(String access_token, Template template) {
		boolean flag=false;   
        String requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        //传入access_token的值
        requestUrl=requestUrl.replace("ACCESS_TOKEN", access_token);  
        String jsonString = template.toJSON();//把String拼接转为json类型
        System.out.println("jsonString:" + jsonString);
        JSONObject jsonResult=CommonUtil.httpsRequest(requestUrl, "POST", jsonString); 
        if(jsonResult!=null){  
            int errorCode=jsonResult.getInteger("errcode");  
            String errorMessage=jsonResult.getString("errmsg");  
            if(errorCode==0){  
                flag=true;  
            }else{  
                System.out.println("模板消息发送失败:"+errorCode+","+errorMessage);
                System.out.println(access_token+"================");
                flag=false;  
            }  
        }
        return flag;    
	}
}
