package com.kakao.pay.coupon.test;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.kakao.pay.coupon.controller.CouponController;
import com.kakao.pay.coupon.dto.Coupon;
import com.kakao.pay.coupon.service.CouponService;

@RunWith(SpringRunner.class)
@WebMvcTest(CouponController.class)
public class CouponControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CouponService couponService;
	
	@Test
	public void create_test() throws Exception{

		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("result", "true");
		
		when(couponService.create(2)).thenReturn(map);
				
		mockMvc.perform(post("/api/coupon/create")
		.contentType(MediaType.APPLICATION_JSON).content("{\"data1\":2}")
		.characterEncoding("utf-8"))
		.andDo(print()).andReturn();
		

//		.perform http method 설정입니다. post, put 등 모두 사용가능합니다.
//		.accept MediaType을 나타냅니다. 
//		.header header정보를 설정합니다.
//		.param 파라미터 설정
//		.cookie 쿠키설정
//		.sessionAttr 세선설정
//
//		.andExpect 응답에 대한 테스트 입니다.
//		.andDo 요청과 응답에 대한 처리 부분입니다.
//		.andReturn 응답에 대한 결과 반환입니다.

	}
	
	
	@Test
	public void provide_test() throws Exception{

		Coupon coupon = new Coupon(); 
		coupon.setCouponCode("Pmo9bnNsz1V3NIwz");
		coupon.setEmail("jw_park16@naver.com");
		coupon.setExpirationDate("2020-05-31");
		coupon.setId("ThA+nZJbfWWQAc8OKckO/EhMrFCALfyvvSdligQUnbc=");
		coupon.setRegDate("2020-05-30");
		coupon.setUseYN(0);

		when(couponService.provide("jw_park16@naver.com")).thenReturn(coupon);
				
		mockMvc.perform(get("/api/coupon/provide/{email}" , "jw_park16@naver.com")
		.characterEncoding("utf-8")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andReturn();
		
	}
	
	@Test
	public void show_test() throws Exception{

		List<Coupon> list = new ArrayList<Coupon>();
		Coupon coupon1 = new Coupon();
		coupon1.setCouponCode("Pmo9bnNsz1V3NIwz");
		
		Coupon coupon2 = new Coupon();
		coupon2.setCouponCode("O9Ytf9yen49KY7GM");

		list.add(coupon1);
		list.add(coupon2);
		
		when(couponService.show("jw_park16@naver.com")).thenReturn(list);
		//when(couponService.show("ht1004@naver.com")).thenReturn(list); //메일이 다를 때 body = null
				
		mockMvc.perform(get("/api/coupon/show/{email}" , "jw_park16@naver.com")
		.characterEncoding("utf-8")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andReturn();
		
	}

	
	@Test
	public void use_test() throws Exception{

		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("result", "true");

		when(couponService.use("jw_park16@naver.com")).thenReturn(map);
				
		mockMvc.perform(put("/api/coupon/use/{email}" , "jw_park16@naver.com")
		.characterEncoding("utf-8")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andReturn();
		
	}

	
	@Test
	public void cancel_test() throws Exception{

		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("result", "true");

		when(couponService.cancel("Pmo9bnNsz1V3NIwz")).thenReturn(map);
				
		mockMvc.perform(put("/api/coupon/cancel/{code}" , "Pmo9bnNsz1V3NIwz")
		.characterEncoding("utf-8")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andReturn();
		
	}
	
	@Test
	public void expire_test() throws Exception{

		List<Coupon> list = new ArrayList<Coupon>();
		Coupon coupon1 = new Coupon();
		coupon1.setCouponCode("JyZW6KhSUeNlneLK");
		
		Coupon coupon2 = new Coupon();
		coupon2.setCouponCode("ZtHT7PdeGLVyDfDT");

		list.add(coupon1);
		list.add(coupon2);
		
		when(couponService.expire()).thenReturn(list);
				
				
		mockMvc.perform(get("/api/coupon/expire"))
		.andDo(print()).andReturn();
		
	}
	

}
