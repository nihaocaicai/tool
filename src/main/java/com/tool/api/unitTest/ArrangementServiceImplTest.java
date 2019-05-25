package com.tool.api.unitTest;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.tool.api.entity.Arrangement;
import com.tool.api.service.ArrangementService;
import com.tool.api.utils.responseDataUtils.ResponseData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-service.xml","classpath:spring/spring-db.xml"})
public class ArrangementServiceImplTest {
	@Autowired
	private ArrangementService arrangementService;
	
	@Test
	public void testFind() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
		List<Arrangement> arrangement = arrangementService.findArrangeByUserId(10);
//		加入转换的数据，类中所在的日期方法名,类的对象
		List<HashMap<String, Object>> list = ResponseData.<Arrangement>responseData(arrangement, "getArrange_date",
				new Arrangement());
		System.out.println(list);
	}
	
	@Test
	public void testInsert() {
		arrangementService.insertArrange(new Arrangement(4, "XXX", "XXX", Date.valueOf("2019-05-25"),
				"10:50:00", 1, Date.valueOf("2019-05-25"), "13:00:00"));
	}
	
	@Test
	public void testUpdate() {
		arrangementService.updateArrange(new Arrangement(4, "XXX", "XXXX", Date.valueOf("2019-05-25"),
				"10:50:00", 1, Date.valueOf("2019-05-25"), "13:00:00"));
	}
	
	@Test
	public void testDelete() {
		arrangementService.deleteArrange(new Arrangement(82,4));
	}
}
