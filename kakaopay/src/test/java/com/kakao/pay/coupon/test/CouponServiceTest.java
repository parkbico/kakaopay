package com.kakao.pay.coupon.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kakao.pay.coupon.dto.Coupon;
import com.kakao.pay.coupon.service.CouponService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponServiceTest {
	
	@Autowired
	CouponService couponService;
	

	
	/*
	 * provice 제공되는 쿠폰이 미사용 상태이고 만료 날짜가 유효한 날짜인지 확인
	*/
	@Test
	public void proviceTest() {
		String email = "jw_park16@naver.com";
		
		Coupon coupon = new Coupon();
		coupon = couponService.provide(email);
		
		if(couponService.provide(email)!=null) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			TimeZone jst = TimeZone.getTimeZone("Asia/Seoul");
			Calendar cal = Calendar.getInstance(jst);
			
			//오늘 날짜
			Date today = cal.getTime();
			//만료 날짜
			cal.add(Calendar.MONTH, 1);
			String expirationDate = coupon.getExpirationDate();
			Date exday = new Date();
			try {
				exday = sdf.parse(expirationDate) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int compare = today.compareTo(exday);
			Assert.assertTrue(compare < 0);
			Assert.assertEquals(coupon.getUseYN(), 0);
			
		}
		
	}
	

	/*
	 *  보여준 쿠폰 리스트에 메일이 모두 동일한지
	*/
	@Test
	public void showTest() {
		String email = "jw_park16@naver.com";
		List<Coupon> list = new ArrayList<Coupon>();
		list = couponService.show(email);
		
		for(int i = 0 ; i < list.size() ; i++ ) {
			Assert.assertTrue( list.get(i).getEmail().equals(email) ) ;
		}
		
	}

	
	
	/*
	 * use 쿠폰 사용 테스트
	*/
	@Test
	public void useTest() {
		String code = "";
//		날짜 만료된 코드여서 오류
//		code = "Pmo9bnNsz1V3NIwz";
//		Assert.assertTrue( (boolean) couponService.use(code).get("result") );
		
		code = "V6u56QtD8MJDj5ye";
		Assert.assertTrue( (boolean) couponService.use(code).get("result") );
		
		code = "5WhuFkHfyb26cXXK";
		Assert.assertTrue( (boolean) couponService.use(code).get("result") );
		
	}

	
	
	/*
	 * cancel 쿠폰 사용 취소 테스트
	*/
	@Test
	public void cancelTest() {
		
		String code = "";
		
		code = "V6u56QtD8MJDj5ye";
		Assert.assertTrue( (boolean) couponService.cancel(code).get("result") );
		
		code = "5WhuFkHfyb26cXXK";
		Assert.assertTrue( (boolean) couponService.cancel(code).get("result") );
		
	}

	
	
	/*
	 * expire 만료된 쿠폰 조회 테스트 (오늘 날짜와 비교)
	*/
	@Test
	public void expireTest() {
		

		List<Coupon> list = new ArrayList<Coupon>(); 
		list = couponService.expire();
		
		if(!list.isEmpty()) {		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			TimeZone jst = TimeZone.getTimeZone("Asia/Seoul");
			Calendar cal = Calendar.getInstance(jst);

			//오늘 날짜
			String today = sdf.format(cal.getTime());
			
			System.out.println(today);
			System.out.println("-----------------------------");
			for(int i = 0 ; i < list.size() ; i++ ) {
				System.out.println(list.get(i).getExpirationDate());
				System.out.println("expire------");
				Assert.assertTrue( list.get(i).getExpirationDate().equals(today) ) ;
			}
						
		}
		
	}



}
