package com.kakao.pay.coupon.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.kakao.pay.coupon.lib.CouponLib;

public class CouponLibTest {
	//생성된 code의 정규식 검사
	@Test
	public void getCodeTest() {
		
		String code = "";
		String pattern = "^[a-zA-Z0-9]*$";
		
		for(int i = 0 ; i < 100 ; i++ ) {
			code = CouponLib.getCode();
			Assert.assertTrue(code.matches(pattern));
		}
		
	}
	
	//쿠폰 중복 생성 테스트
	@Test
	public void codeDuplicationTest() {
		String code = "";
		List<String> list = new ArrayList<String>();
		int num = 10;
		int count = 0;
		for(int i = 0 ; i< num ; i++) {
			code = CouponLib.getCode();
			if(!list.contains(code)) {
				list.add(code);
				count++;
			}
		}
//		System.out.println(list);
//		System.out.println(count);
		Assert.assertEquals(num, count);
	}
	

}
