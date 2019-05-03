package com.tool.api.utils;

import com.tool.api.entity.Diary;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResponseData {
    public static HashMap<String,List<Diary>> responseData(List<Diary> valuesBefore){

        //获取查出来的数据List<E>
        List<Diary> valueBefore = valuesBefore;
        //传过来的数据是对日期进行排序了的
        HashMap<String,List<Diary>> map=new HashMap<>();
        List<Diary> pList = new ArrayList<>();

        int size=valueBefore.size();
        Date date=Date.valueOf("1998-02-01");
        for(Diary p:valueBefore) {
            size--;
            Date d=p.getDiary_write_date();
            System.out.println(d);
            if(date.equals(d)||date.equals(Date.valueOf("1998-02-01"))) {
                pList.add(p);
                if(size==0){
                    map.put(date.toString(),pList);
                }
            }else {
                map.put(date.toString(),pList);
                System.out.println(pList.size());
                pList = new ArrayList<>();
                pList.add(p);
                if(size==0){
                    map.put(date.toString(),pList);
                }
            }
            date=d;
        }
        return map;
    }
}
