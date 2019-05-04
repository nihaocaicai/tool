package com.tool.api.utils.responseDataUtils;

import com.tool.api.entity.Diary;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResponseData {
    public static <T> HashMap<String,List<T>> responseData(List<T> valuesBefore,String method,Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {

        //获取查出来的数据List<T>
        List<T> valueBefore = valuesBefore;
        //传过来的数据是对日期进行排序了的
        HashMap<String,List<T>> map=new HashMap<>();
        List<T> pList = new ArrayList<>();

//        反射，根据对象对象得到类名
        String className = obj.getClass().getName();

        int size=valueBefore.size();
        Date date=Date.valueOf("1998-02-01");
        for(T p:valueBefore) {
            size--;
//            forName(className)：静态加载className这个类;再调用反射的方法
            Date d=(Date) Class.forName(className).getMethod(method).invoke(p);
//            System.out.println(d);
            if(date.equals(d)||date.equals(Date.valueOf("1998-02-01"))) {
                pList.add(p);
                if(size==0){
                    map.put(date.toString(),pList);
                }
            }else {
                map.put(date.toString(),pList);
//                System.out.println(pList.size());
                pList = new ArrayList<>();
                pList.add(p);
                //判断最后一个日期是否只有一个，如果只有一个，不执行下面的操作，不然date会重复，下一个会覆盖上一个
                if(size==0&&date.equals(d)){
                    map.put(date.toString(),pList);
                }
            }
            date=d;
        }
        //把最后一个日期只有一个数据的加到map中
        if(pList.size()!=0){
            map.put(date.toString(),pList);
        }
        return map;
    }
}
