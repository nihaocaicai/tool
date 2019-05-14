package com.tool.api.utils.responseDataUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.TableModel;

import com.tool.mapperClass.PlanMapper;

public class ResponsePlanData {
	public static <T> List<HashMap<String, Object>> responseData(List<T> valuesBefore, String method, Object obj)
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
		//获取查出来的数据List<T>
        List<T> valueBefore = valuesBefore;
        System.out.println("valueBefore:" + valueBefore.size());
        //传过来的数据是对日期进行排序了的
        HashMap<String, Object> datemap = new HashMap<>();
        HashMap<String, Object> daymap = new HashMap<>();
        List<T> dateList = new ArrayList<>();
        List<T> dayList = new ArrayList<>();
        List<HashMap<String, Object>> returnList = new ArrayList<>();
        
        SimpleDateFormat ChinaFormat = new SimpleDateFormat("YYYY年MM月");
        SimpleDateFormat EnglishFormat = new SimpleDateFormat("YYYY-MM");
        SimpleDateFormat HourMinuteFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat ChinaDayFormat = new SimpleDateFormat("dd日");
        SimpleDateFormat EnglishDayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE");
        
//      反射，根据对象得到类名
      String className = obj.getClass().getName();

      int size=valueBefore.size();
      String date= "1998-02";
      String day = "32";
      Date tempdate = null;
      
      for(T p:valueBefore) {
          size--;
//          forName(className)：静态加载className这个类;再调用反射的方法
          Date d=(Date) Class.forName(className).getMethod(method).invoke(p);
          String formatDate = EnglishFormat.format(d);
          System.out.println(d);
//          System.out.println(d);
          if(date.equals(formatDate)||date.equals("1998-02")) {
        	  String formatday = EnglishDayFormat.format(d);
        	  if(day.equals(formatday) || day.equals("32")) {
                String start = (String) Class.forName(className).getMethod("getPlan_start_time").invoke(p);
                String end = (String) Class.forName(className).getMethod("getPlan_end_time").invoke(p);
                int finish = (int) Class.forName(className).getMethod("getPlan_if_finish").invoke(p);
                String time = "";
                time += start;
                if(end != "") {
                	time += "-";
                	time += end;
                }
                String content = (String) Class.forName(className).getMethod("getPlan_content").invoke(p);
                T planMapper = (T) new PlanMapper(time, content, finish);
                dayList.add(planMapper);
        	  }
        	  else {
        		  String DayAndWeek = "";
        		  DayAndWeek += ChinaDayFormat.format(tempdate);
        		  DayAndWeek += " ";
        		  DayAndWeek += weekFormat.format(tempdate);
        		  daymap.put("day", DayAndWeek);
        		  daymap.put("plan", dayList);
        		  dateList.add((T) daymap);
        		  daymap = new HashMap<>();
        		  dayList = new ArrayList<>();
        		  String start = (String) Class.forName(className).getMethod("getPlan_start_time").invoke(p);
                  String end = (String) Class.forName(className).getMethod("getPlan_end_time").invoke(p);
                  int finish = (int) Class.forName(className).getMethod("getPlan_if_finish").invoke(p);
                  String time = "";
                  time += start;
                  if(end != "") {
                  	time += "-";
                  	time += end;
                  }
                  String content = (String) Class.forName(className).getMethod("getPlan_content").invoke(p);
                  T planMapper = (T) new PlanMapper(time, content, finish);
                  dayList.add(planMapper);
        	  }
          }else {
        	  String DayAndWeek = "";
    		  DayAndWeek += ChinaDayFormat.format(tempdate);
    		  DayAndWeek += " ";
    		  DayAndWeek += weekFormat.format(tempdate);
    		  daymap.put("day", DayAndWeek);
    		  daymap.put("plan", dayList);
    		  dateList.add((T) daymap);
          	  datemap.put("date", ChinaFormat.format(tempdate));
              datemap.put("data", dateList);
              returnList.add(datemap);
              daymap = new HashMap<>();
              datemap = new HashMap<>();
              dayList = new ArrayList<>();
              dateList = new ArrayList<>();
              
              String start = (String) Class.forName(className).getMethod("getPlan_start_time").invoke(p);
              String end = (String) Class.forName(className).getMethod("getPlan_end_time").invoke(p);
              int finish = (int) Class.forName(className).getMethod("getPlan_if_finish").invoke(p);
              String time = "";
              time += start;
              if(end != "") {
              	time += "-";
              	time += end;
              }
              String content = (String) Class.forName(className).getMethod("getPlan_content").invoke(p);
              T planMapper = (T) new PlanMapper(time, content, finish);
              dayList.add(planMapper);
          }
          date = EnglishFormat.format(d);
          day = EnglishDayFormat.format(d);
          tempdate = d;
      }
      //把最后一个日期只有一个数据的加到map中
      if(dayList.size() != 0) {
    	  String DayAndWeek = "";
		  DayAndWeek += ChinaDayFormat.format(tempdate);
		  DayAndWeek += " ";
		  DayAndWeek += weekFormat.format(tempdate);
		  daymap.put("day", DayAndWeek);
		  daymap.put("plan", dayList);
		  dateList.add((T) daymap);
      	  datemap.put("date", ChinaFormat.format(tempdate));
          datemap.put("data", dateList);
          returnList.add(datemap);
      }
        return returnList;
	}
}
