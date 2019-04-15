package com.tool.api.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tool.api.entity.Arrangement;
import com.tool.api.service.ArrangementService;


@Controller
public class ArrangementController {
    @Autowired
    private ArrangementService arrangementService;
    
    /*
     * 每次调用目标方法之前都会执行它
     * 先从数据库获取原始数据，再将新的参数直接替代原来的参数，若没有原始参数对应的新参数，则不作修改
     * map将修改过后的User返回给目标方法
     */
    @ModelAttribute
    public void getArrange(@RequestParam(value = "arrange_id", required = false) String arrange_id, Map<String, Object> map) {
    	//查询数据库原始记录
    	Arrangement arrangement = arrangementService.findArrangeByArrangeId(arrange_id);
    	System.out.println("从数据库中取出一个对象：" + arrangement);
    	/* 
    	 * 判断条件针对数据库插入数据而设
    	 * 由于数据库开始肯定不存在这个user_id，所以返回必定为空，若强行put进去，则调用插入方法时候会NullPointException
    	 */
    	if(arrangement == null) {
    		System.out.println("没有对象");
    	}
    	else {
    		System.out.println(arrangement.getArrange_if_prompt());
    		System.out.println(arrangement.getArrange_if_prompt_time());
    		map.put("arrangement", arrangement);
    	}
    }

    /*
    *  根据id查询用户全部考试安排
    *  测试例子：http://localhost:8080/tool/findTimeById?user_id=abcddsssagafafa
    * */ 
    @RequestMapping("/findArrangeById")
    public String findArrangeByUserId(String user_id, Model model) {
    	Arrangement arrangement = arrangementService.findArrangeByUserId(user_id);
        model.addAttribute("arrangement", arrangement);
    	return "time";
    }
    
    /*
     * 插入某用户一条考试安排记录
     *  测试例子：http://localhost:8080/tool/insertTime?user_id=abcabc&time_content=别浪费时间了，快上车&time_date=2019-04-23
     */
    @RequestMapping("/insertArrange")
    public String insertArrange(String user_id, String arrange_content, String arrange_place, String arrange_time,
    						 String arrange_if_prompt, String arrange_if_prompt_time, Model model){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date arrangeTime = null;
    	Date arrangeIfPromptTime = null;
		try {
			arrangeTime = format.parse(arrange_time);
			arrangeIfPromptTime = format.parse(arrange_if_prompt_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Arrangement arrangement = new Arrangement(user_id, arrange_content, arrange_place, arrangeTime, Integer.valueOf(arrange_if_prompt), arrangeIfPromptTime);
		arrangementService.insertArrange(arrangement);
    	return "success";
    }
    	
    /*
     * 目标方法获取根据表单修改过后的Datetime -> @ModelAttribute
     * 更新数据库某个用户考试安排记录
     *  测试例子：http://localhost:8080/tool/updateArrange?arrange_id=3&arrange_place=广财综合楼&arrange_time=2019-12-03%209:00:00
     */
    @RequestMapping("/updateArrange")
    public String updateArrange(Arrangement arrangement) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date arrangeTime = null;
    	Date arrangeIfPromptTime = null;
    	String Time1 = format.format(arrangement.getArrange_time());
    	String Time2 = format.format(arrangement.getArrange_if_prompt_time());
    	try {
			arrangeTime = format.parse(Time1);
			arrangeIfPromptTime = format.parse(Time2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	arrangement.setArrange_time(arrangeTime);
     	arrangement.setArrange_if_prompt_time(arrangeIfPromptTime);
    	arrangementService.updateArrange(arrangement);
    	return "success";
    }
    
    /*
     * 删除某用户一条考试安排记录
     * 测试例子：http://localhost:8080/tool/deleteTime?user_id=abcabc&time_date=2019-04-23
     */
    @RequestMapping("/deleteArrange")
    public String deleteArrange(@RequestParam(value = "arrange_id") String arrange_id) {
    	arrangementService.deleteArrange(arrange_id);
    	return "success";
    }
}
